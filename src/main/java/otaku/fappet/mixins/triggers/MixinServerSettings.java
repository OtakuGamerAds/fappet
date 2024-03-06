package otaku.fappet.mixins.triggers;

import mchorse.mappet.Mappet;
import mchorse.mappet.api.misc.ServerSettings;
import mchorse.mappet.api.triggers.Trigger;
import mchorse.mappet.events.RegisterServerTriggerEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import otaku.fappet.accessors.TriggerAccessor;

import java.io.File;

@Mixin(value = ServerSettings.class, remap = false)
public abstract class MixinServerSettings implements TriggerAccessor
{
    @Shadow
    public abstract Trigger register(String key, String alias, Trigger trigger);

    public Trigger livingEffect;
    public Trigger playerMouseAction;

    public Trigger getLivingEffect()
    {
        return this.livingEffect;
    }
    public Trigger getPlayerMouseAction()
    {
        return this.playerMouseAction;
    }

    @Inject(method = "<init>", at = @At("TAIL"), remap = false)
    public void constructor(File file, CallbackInfo ci)
    {
        this.livingEffect = this.register("living_effect", "living_effect", new Trigger());

        this.playerMouseAction = this.register("player_mouse_action", "player_mouse_action", new Trigger());

        Mappet.EVENT_BUS.post(new RegisterServerTriggerEvent((ServerSettings) (Object) this));
    }
}