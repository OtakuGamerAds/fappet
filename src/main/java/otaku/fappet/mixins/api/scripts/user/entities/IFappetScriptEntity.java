package otaku.fappet.mixins.api.scripts.user.entities;

import otaku.fappet.api.scripts.user.IScriptFancyWorld;

public interface IFappetScriptEntity {

    /**
     * Get entity's fancy world.
     *
     * <pre>{@code
     *    var s = c.getSubject();
     *    var fancyWorld = s.getFancyWorld();
     * }</pre>
     */
    public IScriptFancyWorld getFancyWorld();
}
