package tvl2386.jetpacksng.init;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.IForgeRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tvl2386.jetpacksng.JetpacksNG;
import tvl2386.jetpacksng.item.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tom on 19-11-2016.
 */
public class ModItems {
    public static final ItemConductiveIronJetpack CONDUCTIVE_IRON_JETPACK = new ItemConductiveIronJetpack();
    public static final ItemHardenedJetpack HARDENED_JETPACK = new ItemHardenedJetpack();
    public static final ItemEnergeticJetpack ENERGETIC_JETPACK = new ItemEnergeticJetpack();
    public static final ItemVibrantJetpack VIBRANT_JETPACK = new ItemVibrantJetpack();
    public static final ItemDarkSoulariumJetpack DARK_SOULARIUM_JETPACK = new ItemDarkSoulariumJetpack();

    public static final ItemJetpack[] JETPACKS = {
            CONDUCTIVE_IRON_JETPACK,
            HARDENED_JETPACK,
            ENERGETIC_JETPACK,
            VIBRANT_JETPACK,
            DARK_SOULARIUM_JETPACK,
    };

    @Mod.EventBusSubscriber
    public static class RegistrationHandler {
        public static final Set<Item> ITEMS = new HashSet<>();

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
//            final Item[] items = {
//                    HARDENED_JETPACK,
//                    DARK_SOULARIUM_JETPACK
//            };

            final IForgeRegistry<Item> registry = event.getRegistry();

            for (final Item item : ModItems.JETPACKS) {
                registry.register(item);

                ITEMS.add(item);
            }
        }

        @SubscribeEvent
        @SideOnly(Side.CLIENT)
        public static void registerModels(ModelRegistryEvent event) {
            OBJLoader.INSTANCE.addDomain(JetpacksNG.MODID);

            for (final ItemJetpack jetpack : ModItems.JETPACKS) {
                System.out.println("unlocalizedName: "+jetpack.getUnlocalizedName());
                ModelLoader.setCustomModelResourceLocation(jetpack, 0, new ModelResourceLocation(JetpacksNG.location(jetpack.getName()), "inventory"));
            }
        }
    }
}
