package otaku.fappet.api.scripts;

import mchorse.mappet.api.scripts.code.ScriptWorld;
import mchorse.mappet.api.scripts.user.IScriptWorld;
import mchorse.mappet.api.scripts.user.data.ScriptVector;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;

import java.util.*;

@Mixin(ScriptWorld.class)
public abstract class MixinScriptWorld implements IScriptWorld {
    /**
     * Returns a list of all loaded command blocks in the world.
     *
     * <pre>@code
     * function main(c){
     *    var world = c.getWorld();
     *    var commandBlocks = world.getLoadedCommandBlocks();
     *    commandBlocks.forEach(function(block){
     *       world.removeBlock(block.x, block.y, block.z);
     *    }
     * }
     * </pre>
     */
    public List<ScriptVector> getLoadedCommandBlocks(){
        net.minecraft.world.World world = this.getMinecraftWorld();
        List<BlockPos> commandBlockLocations = new ArrayList<>();

        for (TileEntity tileEntity : world.loadedTileEntityList) {
            if (tileEntity instanceof TileEntityCommandBlock) {
                commandBlockLocations.add(tileEntity.getPos());
            }
        }

        List<ScriptVector> commandBlockVectors = new ArrayList<>();

        for (BlockPos pos : commandBlockLocations) {
            commandBlockVectors.add(new ScriptVector(pos.getX(), pos.getY(), pos.getZ()));
        }

        return commandBlockVectors;
    }
}