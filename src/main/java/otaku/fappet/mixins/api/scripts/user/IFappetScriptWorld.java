package otaku.fappet.mixins.api.scripts.user;

import mchorse.mappet.api.scripts.user.blocks.IScriptBlockState;
import mchorse.mappet.api.scripts.user.data.ScriptVector;
import mchorse.mappet.api.scripts.user.nbt.INBTCompound;

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

    /* Moved Methods from Mappet */

    /**
     * Fill a 3D area with a block with a delay (for huge areas).
     *
     * <pre>{@code
     *     var coarse_dirt = mappet.createBlockState("minecraft:dirt", 1);
     *
     *     c.getWorld().fill(coarse_dirt, -3, 100, -3, 3, 100, 3, 16, 5);
     * }</pre>
     *
     * @param state The block to fill the area with.
     * @param x1 The first x coordinate.
     * @param y1 The first y coordinate.
     * @param z1 The first z coordinate.
     * @param x2 The second x coordinate.
     * @param y2 The second y coordinate.
     * @param z2 The second z coordinate.
     */
    public void fill(IScriptBlockState state, int x1, int y1, int z1, int x2, int y2, int z2, int chunkSize, int delayTicks);

    /* schematics */

    /**
     * Saves a schematic file in the world's schematics folder in world/mappet/schematics.
     *
     * <pre>{@code
     * c.getWorld().saveSchematic("my_schematic", 0, 100, 0, 3, 100, 3);
     * }</pre>
     *
     * @param x1 The first x coordinate.
     * @param y1 The first y coordinate.
     * @param z1 The first z coordinate.
     * @param x2 The second x coordinate.
     * @param y2 The second y coordinate.
     * @param z2 The second z coordinate.
     * @param name The name of the schematic.
     */
    @Deprecated
    public void saveSchematic(String name, int x1, int y1, int z1, int x2, int y2, int z2);

    /**
     * Loads a schematic file from the world's schematics folder in world/mappet/schematics.
     *
     * <pre>{@code
     * c.getWorld().loadSchematic("my_schematic", 0, 100, 0);
     * }</pre>
     *
     * @param name The name of the schematic.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param z The z coordinate.
     */
    @Deprecated
    public void loadSchematic(String name, int x, int y, int z);

    /**
     * Serialize a schematic into a NBT compound.
     *
     * <pre>{@code
     *   var nbt = c.getWorld().serializeSchematic(schematic);
     *   }</pre>
     *
     * @param name Schematic name
     * @return NBT compound
     */
    @Deprecated
    public INBTCompound serializeSchematic(String name);

    /* BlockBuster stuff */

    /**
     * Set morph (from NBT) to a model block at given position in this world.
     * It only works when Blockbuster mod is installed.
     *
     * <pre>{@code
     *     function main(c)
     *     {
     *         var pos = c.getSubject().getPosition()
     *         var nbt = '{Settings:{Hands:1b},Name:"blockbuster.fred"}';
     *
     *         c.getWorld().setModelBlock(nbt, pos.x, pos.y, pos.z, true);
     *     }
     * }</pre>
     *
     * @param nbt NBT of a morph to be replaced for given model block.
     * @param x X coordinate of a model block.
     * @param y Y coordinate of a model block.
     * @param z Z coordinate of a model block.
     * @param force Force update model block to update the morph even if the morph is same.
     */
    @Deprecated
    public void setModelBlockMorph(String nbt, int x, int y, int z, boolean force);

    /**
     * Enable or disable a model block at a given position in this world.
     * It only works when Blockbuster mod is installed.
     *
     * <pre>{@code
     *     function main(c)
     *     {
     *         var pos = c.getSubject().getPosition()
     *         var enabled = true;
     *
     *         c.getWorld().setModelBlockEnabled(pos.x, pos.y, pos.z, enabled);
     *     }
     * }</pre>
     *
     * @param x X coordinate of a model block.
     * @param y Y coordinate of a model block.
     * @param z Z coordinate of a model block.
     * @param enabled Whether to enable or disable the model block.
     */
    @Deprecated
    public void setModelBlockEnabled(int x, int y, int z, boolean enabled);

    /**
     * Check whether a model block at a given position in this world is enabled or not.
     * It only works when Blockbuster mod is installed.
     *
     * <pre>{@code
     *     c.send(c.getWorld().isModelBlockEnabled(0, 4, 0));
     * }</pre>
     *
     * @param x X coordinate of a model block.
     * @param y Y coordinate of a model block.
     * @param z Z coordinate of a model block.
     */
    @Deprecated
    public boolean isModelBlockEnabled(int x, int y, int z);
}
