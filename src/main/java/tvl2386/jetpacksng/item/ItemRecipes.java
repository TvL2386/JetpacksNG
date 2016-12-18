package tvl2386.jetpacksng.item;

import crazypants.enderio.material.Alloy;
import crazypants.enderio.material.Material;
import net.minecraft.item.ItemStack;
import tvl2386.jetpacksng.init.ModItems;
import tvl2386.jetpacksng.util.RecipeUtil;

//import cpw.mods.fml.common.registry.GameRegistry;
//import crazypants.enderio.EnderIO;
//import crazypants.enderio.item.darksteel.DarkSteelItems;
//import crazypants.enderio.material.MachinePart;
//import net.minecraft.init.Blocks;
//import net.minecraft.init.Items;
//import net.minecraft.item.ItemStack;
//import net.minecraftforge.oredict.ShapedOreRecipe;
//
//import static crazypants.enderio.material.Alloy.*;
//import static crazypants.enderio.material.Material.VIBRANT_CYSTAL;
//import static crazypants.util.RecipeUtil.addShaped;
//

public class ItemRecipes {
    public static void addRecipes() {
        String electricalSteel = Alloy.ELECTRICAL_STEEL.getOreIngot();
        String conductiveIron = Alloy.CONDUCTIVE_IRON.getOreIngot();
        String vibCry = Material.VIBRANT_CYSTAL.oreDict;
        String enAlloy = Alloy.ENERGETIC_ALLOY.getOreIngot();
//        String darkSteel = Alloy.DARK_STEEL.getOreIngot();
        String soularium = Alloy.SOULARIUM.getOreIngot();

        ItemStack conductiveIronJetpack = new ItemStack(ModItems.CONDUCTIVE_IRON_JETPACK, 1, 0);
        RecipeUtil.addShaped(conductiveIronJetpack, "x x", "xxx", "xxx", 'x', conductiveIron);

        ItemStack hardenedJetpack = new ItemStack(ModItems.HARDENED_JETPACK, 1, 0);
        RecipeUtil.addShaped(hardenedJetpack, "x x", "xpx", "xxx", 'x', electricalSteel, 'p', conductiveIronJetpack);

        ItemStack energeticJetpack = new ItemStack(ModItems.ENERGETIC_JETPACK, 1, 0);
        RecipeUtil.addShaped(energeticJetpack, "x x", "xpx", "xxx", 'x', enAlloy, 'p', hardenedJetpack);

        ItemStack vibrantJetpack = new ItemStack(ModItems.VIBRANT_JETPACK, 1, 0);
        RecipeUtil.addShaped(vibrantJetpack, "x x", "xpx", "xxx", 'x', vibCry, 'p', energeticJetpack);

        ItemStack darkSoulariumJetpack = new ItemStack(ModItems.DARK_SOULARIUM_JETPACK, 1, 0);
        RecipeUtil.addShaped(darkSoulariumJetpack, "x x", "xpx", "xxx", 'x', soularium, 'p', vibrantJetpack);
    }
//
//  public static void addRecipes() {
//    ItemStack basicGear = new ItemStack(EnderIO.itemMachinePart, 1, MachinePart.BASIC_GEAR.ordinal());
//    String electricalSteel = ELECTRICAL_STEEL.getOreIngot();
//    String conductiveIron = CONDUCTIVE_IRON.getOreIngot();
//    String vibCry = VIBRANT_CYSTAL.oreDict;
//    String enAlloy = ENERGETIC_ALLOY.getOreIngot();
//    String darkSteel = DARK_STEEL.getOreIngot();
//    String soularium = SOULARIUM.getOreIngot();
//
//    // Wrench
//    ItemStack wrench = new ItemStack(EnderIO.itemYetaWench, 1, 0);
//    addShaped(wrench, "s s", " b ", " s ", 's', electricalSteel, 'b', basicGear);
//
//    //Magnet
//    ItemStack magnet = new ItemStack(DarkSteelItems.itemMagnet, 1, 0);
//    DarkSteelItems.itemMagnet.setEnergy(magnet, 0);
//    addShaped(magnet, "scc", "  v", "scc", 's', electricalSteel, 'c', conductiveIron, 'v', vibCry);
//
//    //Dark Steel
//    addShaped(DarkSteelItems.itemDarkSteelHelmet.createItemStack(), "sss", "s s", 's', darkSteel);
//    addShaped(DarkSteelItems.itemDarkSteelChestplate.createItemStack(), "s s", "sss", "sss", 's', darkSteel);
//    addShaped(DarkSteelItems.itemDarkSteelLeggings.createItemStack(), "sss", "s s", "s s", 's', darkSteel);
//    addShaped(DarkSteelItems.itemDarkSteelBoots.createItemStack(), "s s", "s s", 's', darkSteel);
//
//    ItemStack wing = new ItemStack(DarkSteelItems.itemGliderWing, 1, 0);
//    addShaped(wing, "  s", " sl", "sll", 's', darkSteel, 'l', Items.leather);
//    addShaped(new ItemStack(DarkSteelItems.itemGliderWing, 1, 1), " s ", "wsw", "   ", 's', darkSteel, 'w', wing);
//
//    addShaped(DarkSteelItems.itemDarkSteelShears.createItemStack(), " s", "s ", 's', darkSteel);
//
//    ItemStack dspp = new ItemStack(EnderIO.blockDarkSteelPressurePlate);
//    addShaped(dspp, "ss", 's', darkSteel);
//
//    ItemStack dsppSilent = new ItemStack(EnderIO.blockDarkSteelPressurePlate, 1, 1);
//    GameRegistry.addShapedRecipe(dsppSilent, "p", "w", 'p', dspp, 'w', Blocks.wool);
//
//    //Soul Vessel
//    GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(EnderIO.itemSoulVessel), " s ", "q q", " q ", 's', soularium, 'q', new ItemStack(
//        EnderIO.blockFusedQuartz, 1, 0)));
//
//    //XP Rod
//    addShaped(new ItemStack(EnderIO.itemXpTransfer), "  s", " v ", "s  ", 's', soularium, 'v', enAlloy);
//
//    // DS Tools
//    addShaped(DarkSteelItems.itemDarkSteelSword.createItemStack(), " s ", " s ", " w ", 's', darkSteel, 'w', "stickWood");
//    addShaped(DarkSteelItems.itemDarkSteelSword.createItemStack(), " s ", " s ", " w ", 's', darkSteel, 'w', "woodStick");
//    addShaped(DarkSteelItems.itemDarkSteelPickaxe.createItemStack(), "sss", " w ", " w ", 's', darkSteel, 'w', "stickWood");
//    addShaped(DarkSteelItems.itemDarkSteelPickaxe.createItemStack(), "sss", " w ", " w ", 's', darkSteel, 'w', "woodStick");
//    addShaped(DarkSteelItems.itemDarkSteelAxe.createItemStack(), "ss ", "sw ", " w ", 's', darkSteel, 'w', "woodStick");
//    addShaped(DarkSteelItems.itemDarkSteelAxe.createItemStack(), "ss ", "sw ", " w ", 's', darkSteel, 'w', "stickWood");
//  }
}
