package otaku.fappet.mixins.api.scripts.user;

import mchorse.mappet.api.scripts.user.data.ScriptVector;

import java.util.List;

public interface IFappetScriptWorld
{
    /**
     * Returns a list of all loaded command blocks in the world.
     *
     * <pre>{@code
     * function main(c){
     *    var world = c.getWorld();
     *    var commandBlocks = world.getLoadedCommandBlocks();
     *    commandBlocks.forEach(function(block){
     *       world.removeBlock(block.x, block.y, block.z);
     *    }
     * }
     * }</pre>
     *
     * @return A list of all loaded command blocks in the world.
     */
    List<ScriptVector> getLoadedCommandBlocks();
}
