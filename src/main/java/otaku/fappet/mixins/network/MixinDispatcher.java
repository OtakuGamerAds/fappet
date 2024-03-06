package otaku.fappet.mixins.network;

import mchorse.mappet.network.Dispatcher;
import net.minecraftforge.fml.relauncher.Side;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import otaku.fappet.network.common.scripts.PacketEmptyClick;
import otaku.fappet.network.server.scripts.ServerHandlerEmptyClick;

@Mixin(value = Dispatcher.class, remap = false)
public abstract class MixinDispatcher
{
    @Inject(method = "register", at = @At("RETURN"))
    private static void onRegister(CallbackInfo ci)
    {
        Dispatcher.DISPATCHER.register(PacketEmptyClick.class, ServerHandlerEmptyClick.class, Side.SERVER);
    }
}
