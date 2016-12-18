package tvl2386.jetpacksng.server;

import tvl2386.jetpacksng.JetpacksNG;
import tvl2386.jetpacksng.network.PacketHandler;

public class ServerProxy {
    public void preInit() {
        PacketHandler.registerMessages(JetpacksNG.MODID);
    }

    public void init() {
    }

    public void postInit() {
    }
}

