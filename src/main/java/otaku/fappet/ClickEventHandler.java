package otaku.fappet;

import mchorse.mappet.network.Dispatcher;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import otaku.fappet.network.common.scripts.PacketEmptyClick;


public class ClickEventHandler
{

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onMouseEvent(MouseEvent event)
    {
        // Handle mouse click events
        if (event.getButton() != -1)
        { // Check if the event is a mouse click
            String key;
            switch (event.getButton())
            {
                case 0:
                    key = "left";
                    break;
                case 1:
                    key = "right";
                    break;
                case 2:
                    key = "middle";
                    break;
                default:
                    return;
            }
            String state = event.isButtonstate() ? "down" : "up";
            Dispatcher.sendToServer(new PacketEmptyClick(key, state));
        }

        // Handle mouse scroll events
        if (event.getDwheel() != 0)
        {
            String key = "scroll";
            String state = event.getDwheel() > 0 ? "up" : "down";
            Dispatcher.sendToServer(new PacketEmptyClick(key, state));
        }
    }
}
