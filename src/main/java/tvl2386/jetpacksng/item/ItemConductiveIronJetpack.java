package tvl2386.jetpacksng.item;

public class ItemConductiveIronJetpack extends ItemJetpack {

    public ItemConductiveIronJetpack() {
        super("jetpack.1.eio", ArmorMaterial.IRON);
        
        this.fuelCapacity = 80000;
        this.fuelUsage = 32;
        this.fuelPerTickIn = 400;

        this.armorReduction = 5;
        this.armorFuelPerHit = 80;
        this.enchantability = 4;
        this.speedVertical = 0.22D;
        this.accelVertical = 0.1D;
        this.speedVerticalHover = 0.18D;
        this.speedVerticalHoverSlow = 0.14D;
        this.speedSideways = 0.0D;
        this.sprintSpeedModifier = 1.0D;
        this.sprintFuelModifier = 1.0D;
        this.emergencyHoverMode = false;
    }
}
