package tvl2386.jetpacksng.item;

import cofh.api.energy.IEnergyContainerItem;
import com.enderio.core.api.client.gui.IAdvancedTooltipProvider;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tvl2386.jetpacksng.JetpacksNG;
import tvl2386.jetpacksng.handler.SyncHandler;
import tvl2386.jetpacksng.model.ModelJetpack;
import tvl2386.jetpacksng.util.NBTHelper;

import java.text.NumberFormat;
import java.util.List;

public class ItemJetpack extends ItemArmor implements IEnergyContainerItem, IAdvancedTooltipProvider {
    private static final String TAG_HOVERMODE_ON = "JetpackHoverModeOn";
    private static final String TAG_IS_ON = "JetpackIsOn";
    private static final String TAG_ENERGY = "Energy";

    protected String texture;
    protected String name;

    protected double speedVertical = 0.0D;
    protected double accelVertical = 0.0D;
    protected double speedVerticalHover = 0.0D;
    protected double speedVerticalHoverSlow = 0.0D;
    protected double speedSideways = 0.0D;
    protected double sprintSpeedModifier = 0.0D;
    protected double sprintFuelModifier = 0.0D;
    protected boolean emergencyHoverMode = false;

    protected Integer fuelCapacity = 0;
    protected Integer fuelUsage = 0;
    protected Integer fuelPerTickIn = 0;
    protected Integer fuelPerTickOut = 0;
    protected Integer armorReduction = 0;
    protected Integer armorFuelPerHit = 0;
    protected Integer enchantability = 0;

    public ItemJetpack(String name, ItemArmor.ArmorMaterial material) {
        super(material, 2, EntityEquipmentSlot.CHEST);

        this.texture = "textures/models/armor/" + name + ".png";
        this.name = name;

        this.setRegistryName(JetpacksNG.MODID, name);
        this.setUnlocalizedName(this.getRegistryName().toString());
        this.setCreativeTab(JetpacksNG.CREATIVE_TAB);
    }

    public String getName() {
        return this.name;
    }

    public void useFuel(ItemStack itemStack, int fuelUsage) {
        int energyStored = this.getEnergyStored(itemStack);
//        System.out.println("Energy stored: "+energyStored);

        if(energyStored > fuelUsage) {
            NBTHelper.setNBTInteger(itemStack, TAG_ENERGY, (energyStored - fuelUsage));
        } else {
            NBTHelper.setNBTInteger(itemStack, TAG_ENERGY, 0);
        }
    }

    /**
     * Called to tick armor in the armor slot. Override to do something
     */
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack){
        boolean flyKeyDown = SyncHandler.isFlyKeyDown(player);
        boolean descendKeyDown = SyncHandler.isDescendKeyDown(player);

        boolean hoverMode = isHoverModeOn(itemStack);

        if(flyKeyDown) {
            turnJetpackOn(itemStack);
        } else if(player.onGround) {
            turnJetpackOff(itemStack);
        }

        if (flyKeyDown || hoverMode && !player.onGround && isOn(itemStack)) {
            int fuelUsage = (int) (player.isSprinting() ? Math.round(this.fuelUsage * this.sprintFuelModifier) : this.fuelUsage);

            this.useFuel(itemStack, fuelUsage);

            if(this.getEnergyStored(itemStack) > 0) {
                // reset fall distance
                player.fallDistance = 0.0F;

                double currentAccel = this.accelVertical * (player.motionY < 0.3D ? 2.5D : 1.0D);
                double currentSpeedVertical = this.speedVertical * (player.isInWater() ? 0.4D : 1.0D);

                if (flyKeyDown) {
                    // When pressing both up and down, hang still!
                    // else: shoot up! :-)
                    if(descendKeyDown) {
                        player.motionY = Math.min(player.motionY + currentAccel, 0.0D);
                    } else {
                        player.motionY = Math.min(player.motionY + currentAccel, currentSpeedVertical);
                    }
                } else if (hoverMode) {
                    if (descendKeyDown) {
                        player.motionY = Math.min(player.motionY + currentAccel, -this.speedVerticalHover);
                    } else {
                        player.motionY = Math.min(player.motionY + currentAccel, -this.speedVerticalHoverSlow);
                    }
                } else {
                    System.out.println("path 3: this should not occur");
                }

                float speedSideways = (float) (player.isSneaking() ? this.speedSideways * 0.5F : this.speedSideways);
                float speedForward = (float) (player.isSprinting() ? speedSideways * this.sprintSpeedModifier : speedSideways);

                if (SyncHandler.isForwardKeyDown(player)) {
                    player.moveRelative(0, speedForward, speedForward);
                }
                if (SyncHandler.isBackwardKeyDown(player)) {
                    player.moveRelative(0, -speedSideways, speedSideways * 0.8F);
                }
                if (SyncHandler.isLeftKeyDown(player)) {
                    player.moveRelative(speedSideways, 0, speedSideways);
                }
                if (SyncHandler.isRightKeyDown(player)) {
                    player.moveRelative(-speedSideways, 0, speedSideways);
                }
            }
        }
    }

    private static boolean isHoverModeOn(ItemStack itemStack) {
        return NBTHelper.getNBTBoolean(itemStack, TAG_HOVERMODE_ON, false);
    }

    public static boolean toggleHoverMode(ItemStack itemStack) {
        boolean hoverModeOn = !isHoverModeOn(itemStack);
        NBTHelper.getNBT(itemStack).setBoolean(TAG_HOVERMODE_ON, hoverModeOn);
        return hoverModeOn;
    }

    private static boolean isOn(ItemStack itemStack) {
        return NBTHelper.getNBTBoolean(itemStack, TAG_IS_ON, false);
    }

    private static void turnJetpackOn(ItemStack itemStack) {
        NBTHelper.setNBTBoolean(itemStack, TAG_IS_ON, true);
    }

    private static void turnJetpackOff(ItemStack itemStack) {
        NBTHelper.setNBTBoolean(itemStack, TAG_IS_ON, false);
    }

    /**
     * Called by RenderBiped and RenderPlayer to determine the armor texture that
     * should be use for the currently equipped item.
     * This will only be called on instances of ItemArmor.
     *
     * Returning null from this function will use the default value.
     *
     * @param stack ItemStack for the equipped armor
     * @param entity The entity wearing the armor
     * @param slot The slot the armor is in
     * @param type The subtype, can be null or "overlay"
     * @return Path of texture to bind, or null to use default
     */
    @SideOnly(Side.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
        return JetpacksNG.RESOURCE_PREFIX + this.texture;
    }

    /**
     * Override this method to have an item handle its own armor rendering.
     *
     * @param  entityLiving  The entity wearing the armor
     * @param  itemStack  The itemStack to render the model of
     * @param  armorSlot  The slot the armor is in
     * @param _default Original armor model. Will have attributes set.
     * @return  A ModelBiped to render instead of the default
     */
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default)
    {
        return ModelJetpack.INSTANCE;
    }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        int space = getMaxEnergyStored(container) - getEnergyStored(container);
        if(space < maxReceive) {
            if (!simulate)
                NBTHelper.setNBTInteger(container, TAG_ENERGY, (getEnergyStored(container) + space));
            return space;
        } else {
            if(!simulate)
                NBTHelper.setNBTInteger(container, TAG_ENERGY, (getEnergyStored(container) + maxReceive));
            return maxReceive;
        }
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored(ItemStack container) {
        return NBTHelper.getNBTInteger(container, TAG_ENERGY, 0);
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {
        return this.fuelCapacity;
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack)
    {
        double stored = getMaxEnergyStored(stack) - getEnergyStored(stack);
        double max = getMaxEnergyStored(stack);
        return stored / max;
    }


    @Override
    public void addCommonEntries(ItemStack itemStack, EntityPlayer entityPlayer, List<String> list, boolean b) {
        list.add(INT_NF(getEnergyStored(itemStack))+"/"+INT_NF(getMaxEnergyStored(itemStack))+" RF");
    }

    @Override
    public void addBasicEntries(ItemStack itemStack, EntityPlayer entityPlayer, List<String> list, boolean b) {
    }

    @Override
    public void addDetailedEntries(ItemStack itemStack, EntityPlayer entityPlayer, List<String> list, boolean b) {
        list.add("FuelUsage: "+INT_NF(this.fuelUsage)+" RF/t");
        list.add("FuelIn: "+INT_NF(this.fuelPerTickIn)+" RF/t");
        list.add("SpeedVertical: "+this.speedVertical);
        list.add("SpeedVerticalHover: "+this.speedVerticalHover);
        list.add("SpeedSideways: "+this.speedSideways);
        list.add("sprintSpeedModifier: "+this.sprintSpeedModifier);
    }

    protected static String INT_NF(int value) {
        return NumberFormat.getIntegerInstance().format(value);
    }
}
