package otaku.fappet.mixins.api.scripts.user;

import otaku.fappet.api.scripts.user.IScriptFancyWorld;

public interface IFappetScriptEvent
{

    /**
     * Get the fancy world in which this event happened.
     */
    public IScriptFancyWorld getFancyWorld();
}
