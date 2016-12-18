package tvl2386.jetpacksng.item;

public class ItemEnergeticJetpack extends ItemJetpack {

    public ItemEnergeticJetpack() {
        super("jetpack.3.eio", ArmorMaterial.IRON);

        this.fuelCapacity = 4000000;
        this.fuelUsage = 200;
        this.fuelPerTickIn = 20000;

        this.armorReduction = 7;
        this.armorFuelPerHit = 120;
        this.enchantability = 13;
        this.speedVertical = 0.48D;
        this.accelVertical = 0.13D;
        this.speedVerticalHover = 0.34D;
        this.speedVerticalHoverSlow = 0.03D;
        this.speedSideways = 0.14D;
        this.sprintSpeedModifier = 1.3D;
        this.sprintFuelModifier = 2.5D;
        this.emergencyHoverMode = true;
    }
}
