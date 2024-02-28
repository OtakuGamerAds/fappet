package otaku.fappet.mixins.api.scripts.user;

import mchorse.mappet.api.scripts.user.data.ScriptVector;
import mchorse.mappet.api.scripts.user.mappet.IMappetStates;
import mchorse.mappet.api.scripts.user.nbt.INBTCompound;
import mchorse.metamorph.api.morphs.AbstractMorph;
import otaku.fappet.api.scripts.code.fappet.FappetArrayStates;

public interface IFappetScriptFactory
{
    /**
     * If you want to write in Arabic in Minecraft, you will see that the Arabic letters appear
     * in the wrong direction. With this API the text will be converted and will appear in the right way.
     *
     * <pre>{@code
     *  var message = mappet.arabic("ARABIC");
     *  c.send(message);
     * }</pre>
     *
     * @param text Arabic text
     * @return Arabic text in the right direction
     */
    public String arabic(String text);

    /**
     * Check if the text contains Arabic letters
     *
     * <pre>{@code
     *  if (mappet.containsArabic("ARABIC")) {
     *      c.send("The text contains Arabic letters");
     *  }
     * }</pre>
     *
     * @param text Text to check
     * @return True if the text contains Arabic letters
     */
    public boolean containsArabic(String text);

    /**
     * adds a body part to a morph
     *
     * <pre>{@code
     *  function main(c)
     * {
     *     var emptyMorph = mappet.createMorph('{Settings:{Hands:1b},Name:"blockbuster.empty"}');
     *     var blockMorph = mappet.createMorph("{Meta:0b,Block:\"minecraft:stone\",Name:\"block\"}");
     *
     *     var emptyMorphWithBlockMorphBodyPart = mappet.addBodyPart(
     *         emptyMorph, //baseMorph
     *         blockMorph, //partMorph
     *         "head", //limbName
     *         mappet.vector(0, -2, 0), //translate
     *         mappet.vector(1, 2, 1), //scale
     *         mappet.vector(0, 0, 0), //rotate
     *         true, //enabled
     *         true, //animated
     *         false //use target
     *     )
     *
     *     c.getSubject().setMorph(emptyMorphWithBlockMorphBodyPart)
     * }
     * }</pre>
     *
     * @return the base morph with the body part
     */
    public AbstractMorph addBodyPart(AbstractMorph baseMorph, AbstractMorph partMorph, String limbName, ScriptVector translate, ScriptVector scale, ScriptVector rotate, boolean animate, boolean enabled, boolean useTarget);

    /**
     * removes all body parts from a morph that have a certain display name
     *
     * <pre>{@code
     *  function main(c)
     * {
     *     var player = c.getSubject();
     *     var emptyMorph = mappet.createMorph('{Settings:{Hands:1b},Name:"blockbuster.empty"}');
     *     var blockMorph = mappet.createMorph("{Meta:0b,DisplayName:"a_stone_block",Block:"minecraft:stone",Name:"block",ForcedSettings:0b}");
     *
     *     var emptyMorphWithBlockMorphBodyPart = mappet.addBodyPart(
     *         emptyMorph, //baseMorph
     *         blockMorph, //partMorph
     *         "head", //limbName
     *         mappet.vector(0, -2, 0), //translate
     *         mappet.vector(1, 2, 1), //scale
     *         mappet.vector(0, 0, 0), //rotate
     *         true, //enabled
     *         true, //animated
     *         false //use target
     *     )
     *
     *     player.setMorph(emptyMorphWithBlockMorphBodyPart)
     *
     *     c.scheduleScript(60, function (c){
     *         var emptyMorphWithoutBlockMorphBodyPart = mappet.removeBodyPart(emptyMorphWithBlockMorphBodyPart, "a_stone_block");
     *         player.setMorph(emptyMorphWithoutBlockMorphBodyPart)
     *     });
     * }
     * }</pre>
     *
     * @return the base morph with the body part
     */
    public AbstractMorph removeBodyPart(AbstractMorph morph, String displayName);
    /**
     * adds a body part to a morph
     *
     * <pre>{@code
     *  var currentMorph = c.getSubject().getMorph();
     *  c.send(mappet.getMorphData(currentMorph));
     * }</pre>
     *
     * @return the base morph with the body part
     */
    public INBTCompound getMorphData(AbstractMorph morph);

    /**
     * Retrieves a {@link FappetArrayStates} instance for managing array states within scripts. This enables scripts to directly manipulate arrays stored in the game's states, offering methods to modify and query array contents dynamically.
     *
     * <pre>{@code
     *  var states = c.getSubject().getStates();
     *  var nameArrayState = mappet.getArrayState(states, "state_name");
     *  nameArrayState.push("element");
     * }</pre>
     *
     * @param states The IMappetStates instance from a server, player, or NPC.
     * @param stateName The name of the state to manage as an array.
     * @return A {@link FappetArrayStates} object for array manipulation tied to the specified state name.
     */
    public FappetArrayStates getArrayState(IMappetStates states, String stateName);
}
