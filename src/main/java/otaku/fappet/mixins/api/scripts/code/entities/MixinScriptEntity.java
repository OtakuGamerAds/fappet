package otaku.fappet.mixins.api.scripts.code.entities;

import mchorse.mappet.api.scripts.code.entities.ScriptEntity;
import mchorse.mappet.api.scripts.user.entities.IScriptEntity;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import otaku.fappet.api.scripts.code.ScriptFancyWorld;
import otaku.fappet.api.scripts.user.IScriptFancyWorld;

@Mixin(ScriptEntity.class)
public abstract class MixinScriptEntity <T extends Entity> implements IScriptEntity
{
    @Shadow(remap = false) protected T entity;
    public IScriptFancyWorld getFancyWorld()
    {
        return new ScriptFancyWorld(this.entity.world);
    }
}
