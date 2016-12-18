package tvl2386.jetpacksng.item;

public class ItemDarkSoulariumJetpack extends ItemJetpack {

    public ItemDarkSoulariumJetpack() {
        super("jetpack.5.eio", ArmorMaterial.DIAMOND);

        this.fuelCapacity = 60000000;
        this.fuelUsage = 850;
        this.fuelPerTickIn = 200000;
        this.fuelPerTickOut = 32000;
        this.armorReduction = 12;
        this.armorFuelPerHit = 240;
        this.enchantability = 20;
        this.speedVertical = 0.9D;
        this.accelVertical = 0.15D;
        this.speedVerticalHover = 0.45D;
        this.speedVerticalHoverSlow = 0.0D;
        this.speedSideways = 0.21D;
        this.sprintSpeedModifier = 2.4D;
        this.sprintFuelModifier = 6.0D;
        this.emergencyHoverMode = true;
    }
}
