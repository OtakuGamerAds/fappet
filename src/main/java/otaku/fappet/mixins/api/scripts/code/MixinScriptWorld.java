package otaku.fappet.mixins.api.scripts.code;

import mchorse.blockbuster.common.tileentity.TileEntityModel;
import mchorse.blockbuster.common.tileentity.TileEntityModelSettings;
import mchorse.blockbuster.network.common.PacketModifyModelBlock;
import mchorse.mappet.CommonProxy;
import mchorse.mappet.api.scripts.code.ScriptFactory;
import mchorse.mappet.api.scripts.code.ScriptWorld;
import mchorse.mappet.api.scripts.code.nbt.ScriptNBTCompound;
import mchorse.mappet.api.scripts.user.IScriptWorld;
import mchorse.mappet.api.scripts.user.blocks.IScriptBlockState;
import mchorse.mappet.api.scripts.user.data.ScriptVector;
import mchorse.mappet.api.scripts.user.nbt.INBTCompound;
import mchorse.mappet.utils.RunnableExecutionFork;
import mchorse.metamorph.api.MorphManager;
import mchorse.metamorph.api.morphs.AbstractMorph;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import otaku.fappet.mixins.api.scripts.user.IFappetScriptWorld;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**{@link IFappetScriptWorld}*/
@Mixin(ScriptWorld.class)
public abstract class MixinScriptWorld implements IScriptWorld
{
    @Shadow(remap = false) private World world;
    @Shadow(remap = false) private BlockPos.MutableBlockPos pos;
    private final ScriptFactory factory = new ScriptFactory();

    public List<ScriptVector> getLoadedCommandBlocks()
    {
        World world = this.getMinecraftWorld();
        List<BlockPos> commandBlockLocations = new ArrayList<>();

        for (TileEntity tileEntity : world.loadedTileEntityList)
        {
            if (tileEntity instanceof TileEntityCommandBlock)
            {
                commandBlockLocations.add(tileEntity.getPos());
            }
        }

        List<ScriptVector> commandBlockVectors = new ArrayList<>();

        for (BlockPos pos : commandBlockLocations)
        {
            commandBlockVectors.add(new ScriptVector(pos.getX(), pos.getY(), pos.getZ()));
        }

        return commandBlockVectors;
    }


    public void fill(IScriptBlockState state, int x1, int y1, int z1, int x2, int y2, int z2, int chunkSize, int delayTicks)
    {
        int xMin = Math.min(x1, x2);
        int xMax = Math.max(x1, x2);
        int yMin = Math.min(y1, y2);
        int yMax = Math.max(y1, y2);
        int zMin = Math.min(z1, z2);
        int zMax = Math.max(z1, z2);

        for (int xChunk = xMin; xChunk <= xMax; xChunk += chunkSize)
        {
            for (int yChunk = yMin; yChunk <= yMax; yChunk += chunkSize)
            {
                for (int zChunk = zMin; zChunk <= zMax; zChunk += chunkSize)
                {
                    int finalXChunk = xChunk;
                    int finalYChunk = yChunk;
                    int finalZChunk = zChunk;
                    CommonProxy.eventHandler.addExecutable(new RunnableExecutionFork(delayTicks, () ->
                    {
                        fillChunk(state, xMin, yMin, zMin, xMax, yMax, zMax, finalXChunk, finalYChunk, finalZChunk);
                    }));

                    delayTicks += 2;
                }
            }
        }
    }

    private void fillChunk(IScriptBlockState state, int xMin, int yMin, int zMin, int xMax, int yMax, int zMax, int xChunk, int yChunk, int zChunk)
    {
        int xChunkMax = Math.min(xChunk + 16, xMax + 1);
        int yChunkMax = Math.min(yChunk + 16, yMax + 1);
        int zChunkMax = Math.min(zChunk + 16, zMax + 1);

        for (int x = xChunk; x < xChunkMax; x++)
        {
            for (int y = yChunk; y < yChunkMax; y++)
            {
                for (int z = zChunk; z < zChunkMax; z++)
                {
                    setBlock(state, x, y, z);
                }
            }
        }
    }

    /* schematics */

    @Deprecated
    public void saveSchematic(String name, int x1, int y1, int z1, int x2, int y2, int z2)
    {
        int xMin = Math.min(x1, x2);
        int xMax = Math.max(x1, x2);
        int yMin = Math.min(y1, y2);
        int yMax = Math.max(y1, y2);
        int zMin = Math.min(z1, z2);
        int zMax = Math.max(z1, z2);
        int xCentre = (xMin + xMax) / 2;
        int yCentre = (yMin + yMax) / 2;
        int zCentre = (zMin + zMax) / 2;

        File worldDirectory = this.world.getSaveHandler().getWorldDirectory();
        File path = new File(worldDirectory, "mappet/schematics");

        if (!path.exists())
        {
            path.mkdirs();
        }

        File file = new File(path, name + ".nbt");

        try
        {
            file.createNewFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        NBTTagCompound tag = new NBTTagCompound();
        NBTTagList blocks = new NBTTagList();

        for (int x = xMin; x <= xMax; x++)
        {
            for (int y = yMin; y <= yMax; y++)
            {
                for (int z = zMin; z <= zMax; z++)
                {
                    IScriptBlockState state = getBlock(x, y, z);

                    if (!state.getBlockId().equals("minecraft:air"))
                    {
                        NBTTagCompound block = new NBTTagCompound();
                        block.setInteger("x", x);
                        block.setInteger("y", y);
                        block.setInteger("z", z);
                        block.setString("block", state.getBlockId());
                        block.setInteger("meta", state.getMeta());

                        if (getTileEntity(x, y, z) != null)
                        {
                            block.setTag("tile", getTileEntity(x, y, z).getData().getNBTTagCompound());
                        }

                        blocks.appendTag(block);
                    }
                }
            }
        }

        //change the coordinates to be relative to the centre of the schematic
        tag.setInteger("xMin", xMin - xCentre);
        tag.setInteger("yMin", yMin - yCentre);
        tag.setInteger("zMin", zMin - zCentre);
        tag.setInteger("xMax", xMax - xCentre);
        tag.setInteger("yMax", yMax - yCentre);
        tag.setInteger("zMax", zMax - zCentre);
        tag.setInteger("xCentre", 0);
        tag.setInteger("yCentre", 0);
        tag.setInteger("zCentre", 0);

        for (int i = 0; i < blocks.tagCount(); i++)
        {
            NBTTagCompound block = blocks.getCompoundTagAt(i);
            block.setInteger("x", block.getInteger("x") - xCentre);
            block.setInteger("y", block.getInteger("y") - yCentre);
            block.setInteger("z", block.getInteger("z") - zCentre);
        }

        tag.setTag("blocks", blocks);

        try
        {
            CompressedStreamTools.write(tag, file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Deprecated
    public void loadSchematic(String name, int x, int y, int z)
    {
        File worldDirectory = this.world.getSaveHandler().getWorldDirectory();
        File path = new File(worldDirectory, "mappet/schematics");
        File file = new File(path, name + ".nbt");

        if (file.exists())
        {
            try
            {
                NBTTagCompound tag = CompressedStreamTools.read(file);
                int xMin = tag.getInteger("xMin");
                int yMin = tag.getInteger("yMin");
                int zMin = tag.getInteger("zMin");
                NBTTagList blocks = tag.getTagList("blocks", 10);

                for (int i = 0; i < blocks.tagCount(); i++)
                {
                    NBTTagCompound block = blocks.getCompoundTagAt(i);
                    int x1 = block.getInteger("x");
                    int y1 = block.getInteger("y");
                    int z1 = block.getInteger("z");
                    IScriptBlockState state = factory.createBlockState(block.getString("block"), block.getInteger("meta"));

                    setBlock(state, x + x1 - xMin, y + y1 - yMin, z + z1 - zMin);

                    if (block.hasKey("tile"))
                    {
                        INBTCompound tile = factory.createCompound(block.getCompoundTag("tile").toString());

                        tile.setInt("x", x + x1 - xMin);
                        tile.setInt("y", y + y1 - yMin);
                        tile.setInt("z", z + z1 - zMin);
                        getTileEntity(x + x1 - xMin, y + y1 - yMin, z + z1 - zMin).setData(tile);
                    }
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Deprecated
    public INBTCompound serializeSchematic(String name)
    {
        File worldDirectory = this.world.getSaveHandler().getWorldDirectory();
        File path = new File(worldDirectory, "mappet/schematics");
        File file = new File(path, name + ".nbt");

        if (file.exists())
        {
            try
            {
                NBTTagCompound tag = CompressedStreamTools.read(file);

                return new ScriptNBTCompound(tag);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    /* BlockBuster mod */

    @Deprecated //see `getBBModelBlock(x, y, z)`
    public void setModelBlockMorph(String nbt, int x, int y, int z, boolean force)
    {
        if (!this.world.isBlockLoaded(this.pos.setPos(x, y, z)))
        {
            return;
        }

        if (Loader.isModLoaded("blockbuster"))
        {
            this.setModelBlockMorphBlockbuster(nbt, x, y, z, force);
        }
    }

    @Deprecated //see `getBBModelBlock(x, y, z)`
    @net.minecraftforge.fml.common.Optional.Method(modid = "blockbuster")
    private void setModelBlockMorphBlockbuster(String nbt, int x, int y, int z, boolean force)
    {
        try
        {
            AbstractMorph morph = MorphManager.INSTANCE.morphFromNBT(JsonToNBT.getTagFromJson(nbt));
            TileEntity tile = this.world.getTileEntity(new BlockPos(x, y, z));

            if (tile instanceof TileEntityModel)
            {
                TileEntityModel oldModel = (TileEntityModel) this.world.getTileEntity(new BlockPos(x, y, z));
                TileEntityModelSettings oldSettings = oldModel.getSettings().copy();
                TileEntityModel model = (TileEntityModel) tile;
                model.getSettings().copy(oldSettings);

                if (Objects.equals(model.morph.get(), morph) && !force)
                {
                    return;
                }

                model.setMorph(morph);

                PacketModifyModelBlock message = new PacketModifyModelBlock(model.getPos(), model, true);

                mchorse.blockbuster.network.Dispatcher.DISPATCHER.get().sendToAll(message);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Deprecated //see `getBBModelBlock(x, y, z)`
    public void setModelBlockEnabled(int x, int y, int z, boolean enabled)
    {
        if (!this.world.isBlockLoaded(this.pos.setPos(x, y, z)))
        {
            return;
        }

        if (Loader.isModLoaded("blockbuster"))
        {
            this.setModelBlockEnabledBlockbuster(x, y, z, enabled);
        }
    }

    @Deprecated //see `getBBModelBlock(x, y, z)`
    @net.minecraftforge.fml.common.Optional.Method(modid = "blockbuster")
    private void setModelBlockEnabledBlockbuster(int x, int y, int z, boolean enabled)
    {
        try
        {
            TileEntity tile = this.world.getTileEntity(new BlockPos(x, y, z));

            if (tile instanceof TileEntityModel)
            {
                TileEntityModel model = (TileEntityModel) tile;
                model.getSettings().setEnabled(enabled);

                PacketModifyModelBlock message = new PacketModifyModelBlock(model.getPos(), model, true);

                mchorse.blockbuster.network.Dispatcher.DISPATCHER.get().sendToAll(message);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Deprecated //see `getBBModelBlock(x, y, z)`
    public boolean isModelBlockEnabled(int x, int y, int z)
    {
        if (!this.world.isBlockLoaded(this.pos.setPos(x, y, z)))
        {
            return false;
        }

        if (Loader.isModLoaded("blockbuster"))
        {
            return this.isModelBlockEnabledBlockbuster(x, y, z);
        }

        return false;
    }

    @Deprecated //see `getBBModelBlock(x, y, z)`
    @Optional.Method(modid = "blockbuster")
    private boolean isModelBlockEnabledBlockbuster(int x, int y, int z)
    {
        try
        {
            TileEntity tile = this.world.getTileEntity(new BlockPos(x, y, z));

            if (tile instanceof TileEntityModel)
            {
                TileEntityModel model = (TileEntityModel) tile;
                return model.getSettings().isEnabled();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}