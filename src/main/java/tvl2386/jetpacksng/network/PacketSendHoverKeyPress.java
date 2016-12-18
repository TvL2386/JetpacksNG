package tvl2386.jetpacksng.network;


import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tvl2386.jetpacksng.item.ItemJetpack;

public class PacketSendHoverKeyPress implements IMessage {
    @Override
    public void fromBytes(ByteBuf buf) {
    }

    @Override
    public void toBytes(ByteBuf buf) {
    }

    public PacketSendHoverKeyPress() {
    }

    public static class Handler implements IMessageHandler<PacketSendHoverKeyPress, IMessage> {
        @Override
        public IMessage onMessage(PacketSendHoverKeyPress message, MessageContext ctx) {
            // Always use a construct like this to actually handle your message. This ensures that
            // youre 'handle' code is run on the main Minecraft thread. 'onMessage' itself
            // is called on the networking thread so it is not safe to do a lot of things
            // here.
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketSendHoverKeyPress message, MessageContext ctx) {
            // This code is run on the server side. So you can do server-side calculations here
            EntityPlayerMP playerEntity = ctx.getServerHandler().playerEntity;

            ItemStack chest = playerEntity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);

            if(chest != null && chest.getItem() instanceof ItemJetpack) {

//                System.out.println(chest.toString());
//                System.out.println(chest.getTagCompound().toString());
//                System.out.println(chest.getItem().toString());

                boolean hoverModeOn = ItemJetpack.toggleHoverMode(chest);
                if(hoverModeOn) {
                    playerEntity.addChatComponentMessage(new TextComponentString(TextFormatting.GOLD + "Hover mode: " + TextFormatting.GREEN + "on"));
                }
                else {
                    playerEntity.addChatComponentMessage(new TextComponentString(TextFormatting.GOLD + "Hover mode: " + TextFormatting.RED + "off"));
                }
            }
        }
    }
}
