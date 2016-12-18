package tvl2386.jetpacksng.client;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import tvl2386.jetpacksng.input.InputHandler;
import tvl2386.jetpacksng.input.KeyBindings;
import tvl2386.jetpacksng.server.ServerProxy;

public class ClientProxy extends ServerProxy {

    @Override
    public void preInit() {
        super.preInit();

        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void init() {
        super.init();

        // Initialize our input handler so we can listen to keys
        MinecraftForge.EVENT_BUS.register(new InputHandler());
        KeyBindings.init();
    }

    @Override
    public void postInit() {
        MinecraftForge.EVENT_BUS.register(new GuiFuelBar(Minecraft.getMinecraft()));
    }
}