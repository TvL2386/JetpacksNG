package tvl2386.jetpacksng.network;


import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
    private static int packetId = 0;

    public static SimpleNetworkWrapper INSTANCE = null;

    public PacketHandler() {
    }

    public static int nextID() {
        return packetId++;
    }

    public static void registerMessages(String channelName) {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
        registerMessages();
    }

    public static void registerMessages() {
        // Register messages which are sent from the client to the server here:
//        INSTANCE.registerMessage(PacketSendKey.Handler.class, PacketSendKey.class, nextID(), Side.SERVER);
//        INSTANCE.registerMessage(PacketSendFlyKeyPress.Handler.class, PacketSendFlyKeyPress.class, nextID(), Side.SERVER);
//        INSTANCE.registerMessage(PacketSendFlyKeyRelease.Handler.class, PacketSendFlyKeyRelease.class, nextID(), Side.SERVER);
        INSTANCE.registerMessage(PacketSendMovementKeys.Handler.class, PacketSendMovementKeys.class, nextID(), Side.SERVER);
        INSTANCE.registerMessage(PacketSendHoverKeyPress.Handler.class, PacketSendHoverKeyPress.class, nextID(), Side.SERVER);
    }
}
