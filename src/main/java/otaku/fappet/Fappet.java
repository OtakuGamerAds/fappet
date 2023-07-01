package otaku.fappet;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = Fappet.MODID,
        name = Fappet.NAME,
        version = Fappet.VERSION,
        dependencies =
            "required-after:mclib;"+
            "required-after:metamorph;"+
            "required-after:blockbuster;"+
            "required-after:mappet;"+
            "after:aperture;",
        updateJSON = "https://raw.githubusercontent.com/mchorse/mappet/master/version.json"
)
public class Fappet
{
    public static final String MODID = Tags.MOD_ID;
    public static final String NAME = Tags.MOD_NAME;
    public static final String VERSION = "@MOD_VERSION@";

    @SidedProxy(clientSide = "otaku.fappet.ClientProxy", serverSide = "otaku.fappet.ServerProxy")
    public static IProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new otaku.fappet.EventHandler());
    }

    public interface IProxy {
        void preInit();
    }
}
