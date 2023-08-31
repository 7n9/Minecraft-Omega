package net.minecraft.src;

public enum EnumToolMaterial {
	WOOD(0, 2.0F, 0),
	STONE(1, 4.0F, 1),
	IRON(2, 6.0F, 2),
	EMERALD(3, 8.0F, 3),
	GOLD(0, 12.0F, 0);

	private final int harvestLevel;
	//private final int maxUses;
	private final float efficiencyOnProperMaterial;
	private final int damageVsEntity;

	EnumToolMaterial(int i3, float f5, int i6) {
		this.harvestLevel = i3;
		//this.maxUses = i4;
		this.efficiencyOnProperMaterial = f5;
		this.damageVsEntity = i6;
	}

	//public int getMaxUses() {
	//	return this.maxUses;
	//}

	public float getEfficiencyOnProperMaterial() {
		return this.efficiencyOnProperMaterial;
	}

	public int getDamageVsEntity() {
		return this.damageVsEntity;
	}

	public int getHarvestLevel() {
		return this.harvestLevel;
	}
}
