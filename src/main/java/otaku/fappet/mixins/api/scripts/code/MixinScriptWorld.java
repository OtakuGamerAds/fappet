package otaku.fappet.mixins.api.scripts.code;

import mchorse.mappet.api.scripts.code.ScriptWorld;
import mchorse.mappet.api.scripts.user.IScriptWorld;
import mchorse.mappet.api.scripts.user.data.ScriptVector;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;

import java.util.*;

@Mixin(ScriptWorld.class)
public abstract class MixinScriptWorld implements IScriptWorld
{
    public List<ScriptVector> getLoadedCommandBlocks()
    {
        net.minecraft.world.World world = this.getMinecraftWorld();
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
}