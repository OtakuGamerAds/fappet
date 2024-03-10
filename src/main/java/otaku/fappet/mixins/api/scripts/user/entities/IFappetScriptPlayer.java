package otaku.fappet.mixins.api.scripts.user.entities;

import mchorse.mappet.api.scripts.user.data.ScriptVector;
import mchorse.metamorph.api.morphs.AbstractMorph;

public interface IFappetScriptPlayer
{

    /**
     * Adds/removes a hunger point from the player.
     *
     * <pre>{@code
     * c.getSubject().addHungerPoints(-2); //removes a whole hunger piece
     * }</pre>
     */
    public void addHungerPoints(int points);

    /**
     * Displays a temporary HUD to the player.
     *
     * <pre>{@code
     * function main(c){
     *     var player = c.getSubject();
     *     player.setupTempHUD(
     *         "temp_hud_0", //tempId
     *         mappet.createMorph('{Texture:"b.a:image/skins/default.png",Name:"blockbuster.image"}'),
     *         true, //shouldOrtho
     *         mappet.vector(0.5, 0.5, null), //ortho
     *         100, //duration
     *         70, //fov
     *         true, //hideInF1
     *         false, //global
     *         mappet.vector(0, 0, 0), //translate
     *         mappet.vector(200, 200, 200), //scale
     *         mappet.vector(0, 0, 0) //rotate
     *     )
     * }
     * }</pre>
     */
    public boolean setupTempHUD(
            String tempId,
            AbstractMorph morph,
            boolean shouldOrtho,
            ScriptVector ortho,
            int duration,
            float fov,
            boolean hideInF1,
            boolean global,
            ScriptVector translate,
            ScriptVector scale,
            ScriptVector rotate
    );
}
