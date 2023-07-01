package otaku.fappet;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientHandler {
    public static SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(Fappet.MODID);

    public static void registerMessages() {
        int packetId = 0;

        //Example:
        //network.registerMessage(ClientHandlerScreenShake.class, PacketScreenShake.class, packetId++, Side.CLIENT);
    }
}
