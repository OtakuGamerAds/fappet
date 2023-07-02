package otaku.fappet.mixins.api.scripts.code;

import mchorse.mappet.api.scripts.code.ScriptFactory;
import mchorse.mappet.api.scripts.user.IScriptFactory;
import org.spongepowered.asm.mixin.Mixin;
import otaku.fappet.utils.Arabic;

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
}
