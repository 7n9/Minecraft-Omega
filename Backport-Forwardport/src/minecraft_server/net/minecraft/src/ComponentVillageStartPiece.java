package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;

public class ComponentVillageStartPiece extends ComponentVillageWell {
	public WorldChunkManager field_35392_a;
	public int field_35390_b;
	public StructureVillagePieceWeight field_35391_c;
	public ArrayList field_35388_d;
	public ArrayList field_35389_e = new ArrayList();
	public ArrayList field_35387_f = new ArrayList();

	public ComponentVillageStartPiece(WorldChunkManager worldChunkManager1, int i2, Random random3, int i4, int i5, ArrayList arrayList6, int i7) {
		super(0, random3, i4, i5);
		this.field_35392_a = worldChunkManager1;
		this.field_35388_d = arrayList6;
		this.field_35390_b = i7;
	}

	public WorldChunkManager func_35386_a() {
		return this.field_35392_a;
	}
}
