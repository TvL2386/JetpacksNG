package tvl2386.jetpacksng.item;

public class ItemHardenedJetpack extends ItemJetpack {

    public ItemHardenedJetpack() {
        super("jetpack.2.eio", ArmorMaterial.IRON);

        this.fuelCapacity = 400000;
        this.fuelUsage = 50;
        this.fuelPerTickIn = 2000;

        this.armorReduction = 6;
        this.armorFuelPerHit = 80;
        this.enchantability = 8;
        this.speedVertical = 0.3D;
        this.accelVertical = 0.12D;
        this.speedVerticalHover = 0.18D;
        this.speedVerticalHoverSlow = 0.1D;
        this.speedSideways = 0.08D;
        this.sprintSpeedModifier = 1.0D;
        this.sprintFuelModifier = 1.0D;
        this.emergencyHoverMode = false;
    }
}
