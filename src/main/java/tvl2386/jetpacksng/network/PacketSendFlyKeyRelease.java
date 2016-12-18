package tvl2386.jetpacksng.network;


import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tvl2386.jetpacksng.handler.SyncHandler;

public class PacketSendFlyKeyRelease implements IMessage {
//    private int keyID;

    @Override
    public void fromBytes(ByteBuf buf) {
//        this.keyID = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
//        buf.writeInt(this.keyID);
    }

//    public PacketSendFlyKeyPress(int keyID) {
//        this.keyID = keyID;
//    }

    public PacketSendFlyKeyRelease() {

    }

    public static class Handler implements IMessageHandler<PacketSendFlyKeyRelease, IMessage> {
        @Override
        public IMessage onMessage(PacketSendFlyKeyRelease message, MessageContext ctx) {
            // Always use a construct like this to actually handle your message. This ensures that
            // youre 'handle' code is run on the main Minecraft thread. 'onMessage' itself
            // is called on the networking thread so it is not safe to do a lot of things
            // here.
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketSendFlyKeyRelease message, MessageContext ctx) {
            // This code is run on the server side. So you can do server-side calculations here
            EntityPlayerMP playerEntity = ctx.getServerHandler().playerEntity;
//            playerEntity.addChatComponentMessage(new TextComponentString(TextFormatting.GREEN + "Released fly key"));

//            SyncHandler.processKeyUpdate(playerEntity, false);
        }
    }
}
