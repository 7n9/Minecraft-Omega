package net.minecraft.src;

public class SpawnListEntry extends WeightedRandomChoice {
	public Class entityClass;
	public int field_35484_b;
	public int field_35485_c;

	public SpawnListEntry(Class class1, int i2, int i3, int i4) {
		super(i2);
		this.entityClass = class1;
		this.field_35484_b = i3;
		this.field_35485_c = i4;
	}
}
