package otaku.fappet.network.server.scripts;

import otaku.fappet.ClickEventHandler;
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
            if (message.getKey().equals("left"))
            {
                ClickEventHandler.queueLeftClickActions(null, player);
            }
            else if (message.getKey().equals("right"))
            {
                ClickEventHandler.queueRightClickActions(null, player);
            }
        });
        return null;
    }
}