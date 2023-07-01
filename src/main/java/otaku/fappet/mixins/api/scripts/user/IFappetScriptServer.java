package otaku.fappet.mixins.api.scripts.user;

import otaku.fappet.api.scripts.user.IScriptFancyWorld;

public interface IFappetScriptServer
{
    /**
     * Get fancy world at dimension ID.
     *
     * <pre>{@code
     *    var overworld = c.getServer().getFancyWorld(0);
     *
     *    // Do something with the world...
     * }</pre>
     */
    public IScriptFancyWorld getFancyWorld(int dimension);
}
