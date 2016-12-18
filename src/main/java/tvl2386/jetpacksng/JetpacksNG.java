package tvl2386.jetpacksng;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import tvl2386.jetpacksng.item.ItemRecipes;
import tvl2386.jetpacksng.server.ServerProxy;

@Mod(modid = JetpacksNG.MODID, name = JetpacksNG.MODNAME, version = JetpacksNG.MODVERSION, dependencies = "after:enderio;required-after:Forge@[11.16.0.1865,)", useMetadata = true)
public class JetpacksNG {
    public static final String MODID = "jetpacksng";
    public static final String MODNAME = "JetpacksNG";
    public static final String MODVERSION = "1.10.2-0.0.0.2";
    public static final String RESOURCE_PREFIX = MODID + ":";

    public static final CreativeTabs CREATIVE_TAB = new CreativeTabs(MODNAME) {
        @Override
        public Item getTabIconItem() {
            return Items.DIAMOND;
        }
    };

    @SidedProxy(clientSide = "tvl2386.jetpacksng.client.ClientProxy", serverSide = "tvl2386.jetpacksng.server.ServerProxy")
    public static ServerProxy proxy;

    @Mod.Instance
    public static JetpacksNG instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        ItemRecipes.addRecipes();

        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit();
    }

    public static ResourceLocation location(String path) {
        return new ResourceLocation(MODID, path);
    }

}