package otaku.fappet.mixins.api.scripts.code;

import mchorse.mappet.api.scripts.code.ScriptFactory;
import mchorse.mappet.api.scripts.code.nbt.ScriptNBTCompound;
import mchorse.mappet.api.scripts.user.IScriptFactory;
import mchorse.mappet.api.scripts.user.data.ScriptVector;
import mchorse.mappet.api.scripts.user.nbt.INBTCompound;
import mchorse.metamorph.api.Morph;
import mchorse.metamorph.api.morphs.AbstractMorph;
import mchorse.metamorph.bodypart.BodyPart;
import mchorse.metamorph.bodypart.BodyPartManager;
import mchorse.metamorph.bodypart.IBodyPartProvider;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import otaku.fappet.utils.Arabic;

import javax.vecmath.Vector3f;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Mixin(ScriptFactory.class)
public abstract class MixinScriptFactory implements IScriptFactory
{
    public String arabic(String text)
    {
        String translated = Arabic.translateSentence(text);
        if (translated.matches(".*\\p{InArabic}.*")) {
            String[] words = translated.split("\\s");
            List<String> wordList = Arrays.asList(words);
            Collections.reverse(wordList);
            return String.join(" ", wordList);
        } else {
            return translated;
        }
    }

    public boolean containsArabic(String text)
    {
        return text.matches(".*\\p{InArabic}.*");
    }

    public AbstractMorph addBodyPart(AbstractMorph baseMorph, AbstractMorph partMorph, String limbName, ScriptVector translate, ScriptVector scale, ScriptVector rotate, boolean animate, boolean enabled, boolean useTarget)
    {
        if (baseMorph instanceof IBodyPartProvider)
        {
            BodyPartManager manager = ((IBodyPartProvider) baseMorph).getBodyPart();
            BodyPart newPart = new BodyPart();

            // Assuming partMorph is directly usable as a morph for a body part
            newPart.morph = new Morph(partMorph);
            newPart.limb = limbName;

            // Set transformations and settings
            newPart.translate = new Vector3f((float) translate.x, (float) translate.y, (float) translate.z);
            newPart.scale = new Vector3f((float) scale.x, (float) scale.y, (float) scale.z);
            newPart.rotate = new Vector3f((float) rotate.x, (float) rotate.y, (float) rotate.z);
            newPart.animate = animate;
            newPart.enabled = enabled;
            newPart.useTarget = useTarget;

            // Initialize with default properties or based on partMorph specifics
            newPart.init();

            manager.parts.add(newPart);

            return baseMorph;
        }

        return null;
    }

    public INBTCompound getMorphData(AbstractMorph morph)
    {
        NBTTagCompound tag = new NBTTagCompound();

        if (morph != null)
        {
            morph.toNBT(tag);
        }

        return new ScriptNBTCompound(tag);
    }
}
