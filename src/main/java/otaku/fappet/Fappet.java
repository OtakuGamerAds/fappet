package otaku.fappet;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import otaku.fappet.eventHandlers.ClickEventHandler;
import otaku.fappet.eventHandlers.EventTriggerHandler;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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
        updateJSON = "https://raw.githubusercontent.com/OtakuGamerAds/fappet/master/version.json"
)
public class Fappet
{
    public static final String MODID = Tags.MOD_ID;
    public static final String NAME = Tags.MOD_NAME;
    public static final String VERSION = Tags.MOD_VERSION;
    public static EventTriggerHandler eventTriggerHandler;
    public static ClickEventHandler clickEventHandler;

    @SidedProxy(
            clientSide = "otaku.fappet.ClientProxy",
            serverSide = "otaku.fappet.ServerProxy"
    )
    public static IProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(eventTriggerHandler = new EventTriggerHandler());
        MinecraftForge.EVENT_BUS.register(clickEventHandler = new ClickEventHandler());
        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        /* Copy docs.json resources to Mappet's documentations config folder */
        try
        {
            Path destination = Paths.get("config", "mappet", "documentation", "extra", "fappet_docs.json");
            InputStream sourceStream = this.getClass().getResourceAsStream("/assets/fappet/docs.json");

            Files.createDirectories(destination.getParent()); // Ensure directories exist
            Files.copy(sourceStream, destination, StandardCopyOption.REPLACE_EXISTING);

            sourceStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public interface IProxy
    {
        void preInit();
    }
}
