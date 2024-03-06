package otaku.fappet.mixins.api.scripts.code.entities;

import mchorse.mappet.api.scripts.code.entities.ScriptPlayer;
import mchorse.mappet.api.scripts.user.data.ScriptVector;
import mchorse.mappet.api.scripts.user.entities.IScriptPlayer;
import mchorse.mappet.capabilities.character.Character;
import mchorse.metamorph.api.morphs.AbstractMorph;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import otaku.fappet.accessors.CharacterAccessor;

@Mixin(ScriptPlayer.class)
public abstract class MixinScriptPlayer implements IScriptPlayer
{
    protected EntityPlayerMP entity = getMinecraftPlayer();

    /* HUDs */
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
    )
    {
        Character character = Character.get(this.entity);

        if (character instanceof CharacterAccessor)
        {
            return ((CharacterAccessor) character).setupTempHUD(
                    tempId, morph, shouldOrtho, ortho, duration, fov, hideInF1, global, translate, scale, rotate
            );
        }
        return false;
    }
}