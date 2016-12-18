package tvl2386.jetpacksng.network;


import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tvl2386.jetpacksng.handler.SyncHandler;

public class PacketSendFlyKeyPress implements IMessage {
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

    public PacketSendFlyKeyPress() {

    }

    public static class Handler implements IMessageHandler<PacketSendFlyKeyPress, IMessage> {
        @Override
        public IMessage onMessage(PacketSendFlyKeyPress message, MessageContext ctx) {
            // Always use a construct like this to actually handle your message. This ensures that
            // youre 'handle' code is run on the main Minecraft thread. 'onMessage' itself
            // is called on the networking thread so it is not safe to do a lot of things
            // here.
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketSendFlyKeyPress message, MessageContext ctx) {
            // This code is run on the server side. So you can do server-side calculations here
            EntityPlayerMP playerEntity = ctx.getServerHandler().playerEntity;
//            playerEntity.addChatComponentMessage(new TextComponentString(TextFormatting.GREEN + "Pressed fly key"));

//            SyncHandler.processKeyUpdate(playerEntity, true);
        }
    }
}
