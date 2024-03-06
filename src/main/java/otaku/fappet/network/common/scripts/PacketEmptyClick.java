package otaku.fappet.network.common.scripts;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketEmptyClick implements IMessage
{
    private String key;
    private String state;

    public PacketEmptyClick() {}

    public PacketEmptyClick(String key, String state)
    {
        this.key = key;
        this.state = state;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        int keyLength = buf.readInt();
        this.key = buf.readCharSequence(keyLength, java.nio.charset.StandardCharsets.UTF_8).toString();
        int stateLength = buf.readInt();
        this.state = buf.readCharSequence(stateLength, java.nio.charset.StandardCharsets.UTF_8).toString();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.key.length());
        buf.writeCharSequence(this.key, java.nio.charset.StandardCharsets.UTF_8);
        buf.writeInt(this.state.length());
        buf.writeCharSequence(this.state, java.nio.charset.StandardCharsets.UTF_8);
    }

    public String getKey()
    {
        return this.key;
    }

    public String getState()
    {
        return this.state;
    }
}