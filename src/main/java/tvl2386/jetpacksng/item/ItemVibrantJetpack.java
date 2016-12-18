package tvl2386.jetpacksng.item;

public class ItemVibrantJetpack extends ItemJetpack {

    public ItemVibrantJetpack() {
        super("jetpack.4.eio", ArmorMaterial.IRON);

        this.fuelCapacity = 20000000;
        this.fuelUsage = 450;
        this.fuelPerTickIn = 50000;

        this.armorReduction = 8;
        this.armorFuelPerHit = 160;
        this.enchantability = 17;
        this.speedVertical = 0.8D;
        this.accelVertical = 0.14D;
        this.speedVerticalHover = 0.4D;
        this.speedVerticalHoverSlow = 0.005D;
        this.speedSideways = 0.19D;
        this.sprintSpeedModifier = 1.8D;
        this.sprintFuelModifier = 4.0D;
        this.emergencyHoverMode = true;
    }
}
