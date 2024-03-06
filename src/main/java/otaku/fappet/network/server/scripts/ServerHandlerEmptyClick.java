package otaku.fappet.network.server.scripts;

import mchorse.mappet.Mappet;
import mchorse.mappet.api.triggers.Trigger;
import mchorse.mappet.api.utils.DataContext;
import otaku.fappet.accessors.TriggerAccessor;
import otaku.fappet.network.common.scripts.PacketEmptyClick;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ServerHandlerEmptyClick implements IMessageHandler<PacketEmptyClick, IMessage>
{
    @Override
    public IMessage onMessage(PacketEmptyClick message, MessageContext ctx)
    {
        EntityPlayerMP player = ctx.getServerHandler().player;
        player.getServerWorld().addScheduledTask(() -> {
            DataContext context = new DataContext(player)
                    .set("key", message.getKey())
                    .set("state", message.getState());

            Trigger trigger = ((TriggerAccessor) Mappet.settings).getPlayerMouseAction();
            if (!trigger.isEmpty())
            {
                trigger.trigger(context);
            }
        });
        return null; // No response packet
    }
}
