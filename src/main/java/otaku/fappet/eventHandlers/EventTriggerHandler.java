package otaku.fappet.eventHandlers;

import mchorse.mappet.Mappet;
import mchorse.mappet.api.triggers.Trigger;
import mchorse.mappet.api.utils.DataContext;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import otaku.fappet.accessors.TriggerAccessor;

public class EventTriggerHandler
{
    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event)
    {
        if (event.phase == TickEvent.Phase.START)
        {
            return;
        }
    }

    @SubscribeEvent
    public void onEntityEffect(PotionEvent.PotionAddedEvent event)
    {
        if (event.getEntityLiving().world.isRemote)
        {
            return;
        }

        Trigger trigger = ((TriggerAccessor) Mappet.settings).getLivingEffect();
        if (!trigger.isEmpty())
        {
            EntityLivingBase entity = event.getEntityLiving();
            PotionEffect effect = event.getPotionEffect();

            DataContext context = new DataContext(entity);
            context.set("effect", effect.getPotion().getRegistryName().toString());
            context.set("duration", effect.getDuration());
            context.set("amplifier", effect.getAmplifier());
            trigger.trigger(context);
        }
    }
}