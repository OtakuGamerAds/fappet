package otaku.fappet.mixins.api.scripts.code;

import mchorse.mappet.api.scripts.code.ScriptServer;
import mchorse.mappet.api.scripts.user.IScriptServer;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import otaku.fappet.api.scripts.code.ScriptFancyWorld;
import otaku.fappet.api.scripts.user.IScriptFancyWorld;
import otaku.fappet.mixins.api.scripts.user.IFappetScriptServer;

/**{@link IFappetScriptServer}*/
@Mixin(ScriptServer.class)
public abstract class MixinScriptServer implements IScriptServer
{
    @Shadow(remap = false) private MinecraftServer server;

    public IScriptFancyWorld getFancyWorld(int dimension)
    {
        return new ScriptFancyWorld(this.server.getWorld(dimension));
    }
}
