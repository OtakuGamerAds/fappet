package otaku.fappet.network.common.scripts;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketEmptyClick implements IMessage {
    private String key;

    public PacketEmptyClick() {}

    public PacketEmptyClick(String key) {
        this.key = key;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        int length = buf.readInt();
        this.key = buf.readCharSequence(length, java.nio.charset.StandardCharsets.UTF_8).toString();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.key.length());
        buf.writeCharSequence(this.key, java.nio.charset.StandardCharsets.UTF_8);
    }

    public String getKey() {
        return this.key;
    }
}