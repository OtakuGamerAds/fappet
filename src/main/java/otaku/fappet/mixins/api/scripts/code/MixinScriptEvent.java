package otaku.fappet.mixins.api.scripts.code;

import mchorse.mappet.api.scripts.code.ScriptEvent;
import mchorse.mappet.api.scripts.user.IScriptEvent;
import mchorse.mappet.api.utils.DataContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import otaku.fappet.api.scripts.code.ScriptFancyWorld;
import otaku.fappet.api.scripts.user.IScriptFancyWorld;

@Mixin(ScriptEvent.class)
public abstract class MixinScriptEvent implements IScriptEvent
{
    @Shadow private DataContext context;
    public IScriptFancyWorld fancyWorld;

    public IScriptFancyWorld getFancyWorld()
    {
        if (this.fancyWorld == null && this.context.world != null)
        {
            this.fancyWorld = new ScriptFancyWorld(this.context.world);
        }

        return this.fancyWorld;
    }
}
