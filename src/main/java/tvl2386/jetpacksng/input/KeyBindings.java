package tvl2386.jetpacksng.input;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import tvl2386.jetpacksng.JetpacksNG;

@SideOnly(Side.CLIENT)
public class KeyBindings {

    public static KeyBinding toggleHoverModeKey;

    public static void init() {
        toggleHoverModeKey = new KeyBinding("Hover Mode", Keyboard.KEY_H, JetpacksNG.MODNAME);
        ClientRegistry.registerKeyBinding(toggleHoverModeKey);
    }
}
