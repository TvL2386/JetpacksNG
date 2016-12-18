package tvl2386.jetpacksng.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public abstract class NBTHelper {

    public static NBTTagCompound getNBT(ItemStack itemStack) {
        if (!itemStack.hasTagCompound())
            itemStack.setTagCompound(new NBTTagCompound());

        return itemStack.getTagCompound();
    }

    public static boolean getNBTBoolean(ItemStack itemStack, String tag, boolean fallback) {
        NBTTagCompound tagCompound = getNBT(itemStack);
        if (!tagCompound.hasKey(tag))
            tagCompound.setBoolean(tag, fallback);

        return tagCompound.getBoolean(tag);
    }

    public static void setNBTBoolean(ItemStack itemStack, String tag, boolean value) {
        NBTTagCompound tagCompound = getNBT(itemStack);
        tagCompound.setBoolean(tag, value);
    }

    public static int getNBTInteger(ItemStack itemStack, String tag, int fallback) {
        NBTTagCompound tagCompound = getNBT(itemStack);
        if (!tagCompound.hasKey(tag))
            tagCompound.setInteger(tag, fallback);

        return tagCompound.getInteger(tag);
    }

    public static void setNBTInteger(ItemStack itemStack, String tag, int value) {
        NBTTagCompound tagCompound = getNBT(itemStack);
        tagCompound.setInteger(tag, value);
    }
}
