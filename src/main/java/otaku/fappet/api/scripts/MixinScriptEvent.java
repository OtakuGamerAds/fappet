package otaku.fappet.api.scripts;

import mchorse.mappet.api.scripts.code.ScriptEvent;
import mchorse.mappet.api.scripts.user.IScriptWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ScriptEvent.class)
public class MixinScriptEvent {
    @Inject(method = "getWorld()Lmchorse/mappet/api/scripts/user/IScriptWorld;", at = @At("HEAD"), remap = false)
    public void getWorldInject(CallbackInfoReturnable<IScriptWorld> cir) {
        System.out.println("Fappet: getWorld");
    }
}
