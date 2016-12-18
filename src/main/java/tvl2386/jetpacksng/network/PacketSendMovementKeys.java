package tvl2386.jetpacksng.network;


import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tvl2386.jetpacksng.handler.SyncHandler;

public class PacketSendMovementKeys implements IMessage {
    private boolean flyState;
    private boolean descendState;

    private boolean forwardState;
    private boolean backwardState;
    private boolean leftState;
    private boolean rightState;

    @Override
    public void fromBytes(ByteBuf buf) {
        this.flyState = buf.readBoolean();
        this.descendState = buf.readBoolean();

        this.forwardState = buf.readBoolean();
        this.backwardState = buf.readBoolean();
        this.leftState = buf.readBoolean();
        this.rightState = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.flyState);
        buf.writeBoolean(this.descendState);

        buf.writeBoolean(this.forwardState);
        buf.writeBoolean(this.backwardState);
        buf.writeBoolean(this.leftState);
        buf.writeBoolean(this.rightState);
    }

    public PacketSendMovementKeys(boolean flyState, boolean descendState, boolean forwardState, boolean backwardState, boolean leftState, boolean rightState) {
        this.flyState = flyState;
        this.descendState = descendState;
        this.forwardState = forwardState;
        this.backwardState = backwardState;
        this.leftState = leftState;
        this.rightState = rightState;
    }

    public PacketSendMovementKeys() {

    }

    public static class Handler implements IMessageHandler<PacketSendMovementKeys, IMessage> {
        @Override
        public IMessage onMessage(PacketSendMovementKeys message, MessageContext ctx) {
            // Always use a construct like this to actually handle your message. This ensures that
            // youre 'handle' code is run on the main Minecraft thread. 'onMessage' itself
            // is called on the networking thread so it is not safe to do a lot of things
            // here.
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketSendMovementKeys msg, MessageContext ctx) {
            // This code is run on the server side. So you can do server-side calculations here
            EntityPlayerMP playerEntity = ctx.getServerHandler().playerEntity;
//            playerEntity.addChatComponentMessage(new TextComponentString(TextFormatting.GREEN + "Pressed fly key"));

            SyncHandler.processKeyUpdate(playerEntity, msg.flyState, msg.descendState, msg.forwardState, msg.backwardState, msg.leftState, msg.rightState);
        }
    }
}
