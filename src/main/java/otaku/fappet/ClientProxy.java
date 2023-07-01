package otaku.fappet;

public class ClientProxy implements Fappet.IProxy
{
    @Override
    public void preInit()
    {
        ClientHandler.registerMessages();
    }
}
