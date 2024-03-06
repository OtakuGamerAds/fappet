package otaku.fappet;

import mchorse.mappet.CommonProxy;
import mchorse.mappet.Mappet;
import mchorse.mappet.api.triggers.Trigger;
import mchorse.mappet.api.utils.DataContext;
import mchorse.mappet.network.Dispatcher;
import mchorse.mappet.utils.RunnableExecutionFork;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import otaku.fappet.accessors.TriggerAccessor;
import otaku.fappet.network.common.scripts.PacketEmptyClick;

import java.util.HashSet;
import java.util.Set;

public class ClickEventHandler
{
    private static final Set<String> actionsQueued = new HashSet<>();

    public static void queueRightClickActions(Event event, EntityPlayer player)
    {
        String actionKey = "RightClick:" + player.getName();
        if (actionsQueued.add(actionKey))
        {
            CommonProxy.eventHandler.addExecutable(new RunnableExecutionFork(0, () -> {
                actionsQueued.remove(actionKey);

                DataContext context = new DataContext(player);
                Trigger trigger = ((TriggerAccessor) Mappet.settings).getPlayerClickRight();
                if (!trigger.isEmpty())
                {
                    if (event!=null)
                    {
                        CommonProxy.eventHandler.trigger(event, trigger, context);
                    }
                    else
                    {
                        trigger.trigger(context);
                    }
                }
            }));
        }
    }

    public static void queueLeftClickActions(Event event, EntityPlayer player)
    {
        String actionKey = "LeftClick:" + player.getName();
        if (actionsQueued.add(actionKey))
        {
            CommonProxy.eventHandler.addExecutable(new RunnableExecutionFork(0, () -> {
                actionsQueued.remove(actionKey);

                DataContext context = new DataContext(player);
                Trigger trigger = ((TriggerAccessor) Mappet.settings).getPlayerClickLeft();
                if (!trigger.isEmpty())
                {
                    if (event!=null)
                    {
                        CommonProxy.eventHandler.trigger(event, trigger, context);
                    }
                    else
                    {
                        trigger.trigger(context);
                    }
                }
            }));
        }
    }

    /* Event subscriptions */

    /* Right-click events */

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onPlayerRightClickEmpty(PlayerInteractEvent.RightClickEmpty event)
    {
        Dispatcher.sendToServer(new PacketEmptyClick("right"));
    }

    @SubscribeEvent
    public void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event)
    {
        if (!event.getWorld().isRemote)
        {
            queueRightClickActions(event, event.getEntityPlayer());
        }
    }

    @SubscribeEvent
    public void onPlayerRightClickItem(PlayerInteractEvent.RightClickItem event)
    {
        if (!event.getWorld().isRemote)
        {
            queueRightClickActions(event, event.getEntityPlayer());
        }
    }


    @SubscribeEvent
    public void onPlayerWithScriptedItemInteractWithEntity(PlayerInteractEvent.EntityInteract event)
    {
        if (!event.getWorld().isRemote)
        {
            queueRightClickActions(event, event.getEntityPlayer());
        }
    }

    @SubscribeEvent
    public void onItemUseStart(LivingEntityUseItemEvent.Start event)
    {
        if (!event.getEntityLiving().world.isRemote)
        {
            if (event.getEntityLiving() instanceof EntityPlayer)
            {
                queueRightClickActions(event, (EntityPlayer) event.getEntityLiving());
            }
        }
    }

    /* Left-click events */

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onPlayerLeftClickEmpty(PlayerInteractEvent.LeftClickEmpty event)
    {
        Dispatcher.sendToServer(new PacketEmptyClick("left"));
    }

    @SubscribeEvent
    public void onPlayerLeftClickBlock(PlayerInteractEvent.LeftClickBlock event)
    {
        if (!event.getWorld().isRemote)
        {
            queueLeftClickActions(event, event.getEntityPlayer());
        }
    }

    @SubscribeEvent
    public void onPlayerAttackEntity(LivingAttackEvent event)
    {
        if (event.getSource().getTrueSource() instanceof EntityPlayer)
        {
            if (!event.getSource().getTrueSource().world.isRemote)
            {
                queueLeftClickActions(event, (EntityPlayer) event.getSource().getTrueSource());
            }
        }
    }
}
