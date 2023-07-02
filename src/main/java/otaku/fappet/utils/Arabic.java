package otaku.fappet.utils;

import java.util.*;
import java.util.regex.*;

public class Arabic {
    static final List<TranslationRule> RULES = new ArrayList<>();

    static final String nonJoinerLetters = "ﺬآداﺇﺁﺃﺎﺈﺂﺄرﺮزﺰژﮋذﺲﺶﺺﺾوﭗﺚﺖﺞﭻﺢﺦﺐﻂﻆﻊﻎﻒﻖﮏﮓﻚﻞﻢﻦﻮﻪﻰﺊﻲﺪﻼﻻﻺﻹﻶﻵﻸﻷﷺﷲﺔﻪﺅ";
    static final String nospaceAfter = "(?!\\s|$|^)";
    static final String spaceAfter = "(?=\\s|^|$)";
    static final String nonJoinerRegex = "(?<!\\s|^|$|[" + nonJoinerLetters + "])";
    static final String isnonJoinerRegex = "(?<=\\s|^|$|[" + nonJoinerLetters + "])";
    static final String JoinerRegex = "(?<!\\s\\w[^" + nonJoinerLetters + "])";

    static {
        initializeRules();
    }

    private static void initializeRules() {
        RULES.add(new TranslationRule(spaceAfter + "الله", "ﷲ"));
        RULES.add(new TranslationRule(spaceAfter + "الله" + spaceAfter, "ﷲ"));
        RULES.add(new TranslationRule(spaceAfter + "صلى", "ﷺ"));
        RULES.add(new TranslationRule("لإ", "ﻹ"));
        RULES.add(new TranslationRule(JoinerRegex + "ﻹ", "ﻺ"));
        RULES.add(new TranslationRule("لآ", "ﻵ"));
        RULES.add(new TranslationRule(JoinerRegex + "ﻵ", "ﻶ"));
        RULES.add(new TranslationRule("لأ", "ﻷ"));
        RULES.add(new TranslationRule(JoinerRegex + "ﻷ", "ﻸ"));
        RULES.add(new TranslationRule("لا", "ﻻ"));
        RULES.add(new TranslationRule(JoinerRegex + "ﻼ" + spaceAfter, "ﻼ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ا", "ﺎ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ب" + nospaceAfter, "ﺒ"));
        RULES.add(new TranslationRule(JoinerRegex + "ب" + nospaceAfter, "ﺑ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ب" + spaceAfter, "ﺐ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "پ" + nospaceAfter, "ﭙ"));
        RULES.add(new TranslationRule(JoinerRegex + "پ" + nospaceAfter, "ﭘ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "پ" + spaceAfter, "ﭗ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ت" + nospaceAfter, "ﺘ"));
        RULES.add(new TranslationRule(JoinerRegex + "ت" + nospaceAfter, "ﺗ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ت" + spaceAfter, "ﺖ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ث" + nospaceAfter, "ﺜ"));
        RULES.add(new TranslationRule(JoinerRegex + "ث" + nospaceAfter, "ﺛ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ث" + spaceAfter, "ﺚ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ج" + nospaceAfter, "ﺠ"));
        RULES.add(new TranslationRule(JoinerRegex + "ج" + nospaceAfter, "ﺟ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ج" + spaceAfter, "ﺞ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "چ" + nospaceAfter, "ﭽ"));
        RULES.add(new TranslationRule(JoinerRegex + "چ" + nospaceAfter, "ﭼ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "چ" + spaceAfter, "ﭻ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ح" + nospaceAfter, "ﺤ"));
        RULES.add(new TranslationRule(JoinerRegex + "ح" + nospaceAfter, "ﺣ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ح" + spaceAfter, "ﺢ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "خ" + nospaceAfter, "ﺨ"));
        RULES.add(new TranslationRule(JoinerRegex + "خ" + nospaceAfter, "ﺧ"));
        RULES.add(new TranslationRule(JoinerRegex + "خ" + spaceAfter, "ﺦ"));
        RULES.add(new TranslationRule(JoinerRegex + "د" + spaceAfter, "ﺪ"));
        RULES.add(new TranslationRule(JoinerRegex + "ذ" + spaceAfter, "ﺬ"));
        RULES.add(new TranslationRule(JoinerRegex + "ر" + spaceAfter, "ﺮ"));
        RULES.add(new TranslationRule(JoinerRegex + "ز" + spaceAfter, "ﺰ"));
        RULES.add(new TranslationRule(JoinerRegex + "ژ" + spaceAfter, "ﮋ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "س" + nospaceAfter, "ﺴ"));
        RULES.add(new TranslationRule(JoinerRegex + "س" + nospaceAfter, "ﺳ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "س" + spaceAfter, "ﺲ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ش" + nospaceAfter, "ﺸ"));
        RULES.add(new TranslationRule(JoinerRegex + "ش" + nospaceAfter, "ﺷ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ش" + spaceAfter, "ﺶ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ص" + nospaceAfter, "ﺼ"));
        RULES.add(new TranslationRule(JoinerRegex + "ص" + nospaceAfter, "ﺻ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ص" + spaceAfter, "ﺺ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ض" + nospaceAfter, "ﻀ"));
        RULES.add(new TranslationRule(JoinerRegex + "ض" + nospaceAfter, "ﺿ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ض" + spaceAfter, "ﺾ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ط" + nospaceAfter, "ﻄ"));
        RULES.add(new TranslationRule(JoinerRegex + "ط" + nospaceAfter, "ﻃ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ط" + spaceAfter, "ﻂ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ظ" + nospaceAfter, "ﻈ"));
        RULES.add(new TranslationRule(JoinerRegex + "ظ" + nospaceAfter, "ﻇ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ظ" + spaceAfter, "ﻆ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ع" + nospaceAfter, "ﻌ"));
        RULES.add(new TranslationRule(JoinerRegex + "ع" + nospaceAfter, "ﻋ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ع" + spaceAfter, "ﻊ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "غ" + nospaceAfter, "ﻐ"));
        RULES.add(new TranslationRule(JoinerRegex + "غ" + nospaceAfter, "ﻏ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "غ" + spaceAfter, "ﻎ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ف" + nospaceAfter, "ﻔ"));
        RULES.add(new TranslationRule(JoinerRegex + "ف" + nospaceAfter, "ﻓ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ف" + spaceAfter, "ﻒ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ق" + nospaceAfter, "ﻘ"));
        RULES.add(new TranslationRule(JoinerRegex + "ق" + nospaceAfter, "ﻗ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ق" + spaceAfter, "ﻖ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ک" + nospaceAfter, "ﮑ"));
        RULES.add(new TranslationRule(JoinerRegex + "ک" + nospaceAfter, "ﮐ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ک" + spaceAfter, "ﮏ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ك" + nospaceAfter, "ﻜ"));
        RULES.add(new TranslationRule(JoinerRegex + "ك" + nospaceAfter, "ﻛ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ك" + spaceAfter, "ﻚ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "گ" + nospaceAfter, "ﮕ"));
        RULES.add(new TranslationRule(JoinerRegex + "گ" + nospaceAfter, "ﮔ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "گ" + spaceAfter, "ﮓ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ل" + nospaceAfter, "ﻠ"));
        RULES.add(new TranslationRule(JoinerRegex + "ل" + nospaceAfter, "ﻟ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ل" + spaceAfter, "ﻞ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "م" + nospaceAfter, "ﻤ"));
        RULES.add(new TranslationRule(JoinerRegex + "م" + nospaceAfter, "ﻣ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "م" + spaceAfter, "ﻢ"));
        RULES.add(new TranslationRule(JoinerRegex + "ن" + nospaceAfter, "ﻧ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ن" + nospaceAfter, "ﻨ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ن" + spaceAfter, "ﻦ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "و" + spaceAfter, "ﻮ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ه" + nospaceAfter, "ﻬ"));
        RULES.add(new TranslationRule(JoinerRegex + "ه" + nospaceAfter, "ﻫ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ه" + spaceAfter, "ﻪ"));
        RULES.add(new TranslationRule(isnonJoinerRegex + "ه" + spaceAfter, "ﮦ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ی" + nospaceAfter, "ﻴ"));
        RULES.add(new TranslationRule(JoinerRegex + "ی" + nospaceAfter, "ﻳ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ی" + spaceAfter, "ﻰ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ي" + nospaceAfter, "ﻴ"));
        RULES.add(new TranslationRule(JoinerRegex + "ي" + nospaceAfter, "ﻳ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ي" + spaceAfter, "ﻲ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ي" + nospaceAfter, "ﺌ"));
        RULES.add(new TranslationRule(JoinerRegex + "ي" + nospaceAfter, "ﺋ"));
        RULES.add(new TranslationRule(nonJoinerRegex + "ي" + spaceAfter, "ﺊ"));
    }

    public static String translateWord(String word) {
        if (word == null || word.isEmpty() || !word.matches(".*\\p{InArabic}.*")) return word;

        for (TranslationRule rule : RULES) {
            word = rule.getPattern().matcher(word).replaceAll(rule.getReplacement());
        }

        return new StringBuilder(word).reverse().toString();
    }

    public static String translateSentence(String sentence) {
        if (sentence == null || sentence.isEmpty() || !sentence.matches(".*\\p{InArabic}.*")) return sentence;

        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (!words[i].matches("\\d+")) {
                words[i] = translateWord(words[i]);
            }
        }
        return String.join(" ", words);
    }

    static class TranslationRule {
        private final String regex;
        private final String replacement;
        private final Pattern pattern;

        public TranslationRule(String regex, String replacement) {
            this.regex = regex;
            this.replacement = replacement;
            this.pattern = Pattern.compile(regex);
        }

        public Pattern getPattern() {
            return pattern;
        }

        public String getReplacement() {
            return replacement;
        }
    }
}