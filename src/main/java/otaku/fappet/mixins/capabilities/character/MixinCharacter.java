package otaku.fappet.mixins.capabilities.character;

import mchorse.mappet.api.huds.HUDMorph;
import mchorse.mappet.api.huds.HUDScene;
import mchorse.mappet.api.scripts.user.data.ScriptVector;
import mchorse.mappet.network.Dispatcher;
import mchorse.mappet.network.common.huds.PacketHUDScene;
import mchorse.metamorph.api.Morph;
import mchorse.metamorph.api.morphs.AbstractMorph;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import mchorse.mappet.capabilities.character.Character;
import org.spongepowered.asm.mixin.Shadow;
import otaku.fappet.modules.main.triggers.CharacterAccessor;

import javax.vecmath.Vector3f;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Mixin(Character.class)
public abstract class MixinCharacter implements CharacterAccessor
{
    @Shadow private EntityPlayer player;
    @Shadow private Map<String, List<HUDScene>> displayedHUDs;

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
            ScriptVector rotate)
    {
        // Create a new HUDScene and HUDMorph for the temporary HUD
        HUDScene tempScene = new HUDScene();
        tempScene.hide = hideInF1;
        tempScene.fov = fov;
        tempScene.global = global;

        HUDMorph tempMorph = new HUDMorph();
        tempMorph.morph = new Morph(morph);
        tempMorph.ortho = shouldOrtho;
        tempMorph.orthoX = (float) ortho.x;
        tempMorph.orthoY = (float) ortho.y;
        tempMorph.expire = duration;
        tempMorph.translate = new Vector3f((float) translate.x, (float) translate.y, (float) translate.z);
        tempMorph.scale = new Vector3f((float) scale.x, (float) scale.y, (float) scale.z);
        tempMorph.rotate = new Vector3f((float) rotate.x, (float) rotate.y, (float) rotate.z);

        tempScene.morphs.add(tempMorph);

        // Serialize and send the HUDScene to the player
        Dispatcher.sendTo(new PacketHUDScene(tempId, tempScene.serializeNBT()), (EntityPlayerMP) this.player);

        // Add the temp HUD to the displayed HUDs list, but with a special flag or in a separate map if needed
        this.displayedHUDs.put(tempId, Arrays.asList(tempScene));

        return true;
    }
}
