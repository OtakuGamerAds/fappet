package otaku.fappet.mixins.api.scripts.user;

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
}
