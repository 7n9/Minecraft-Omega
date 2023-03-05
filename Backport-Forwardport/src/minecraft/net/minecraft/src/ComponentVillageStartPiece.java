package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;

public class ComponentVillageStartPiece extends ComponentVillageWell {
	public WorldChunkManager field_35111_a;
	public int field_35109_b;
	public StructureVillagePieceWeight field_35110_c;
	public ArrayList field_35107_d;
	public ArrayList field_35108_e = new ArrayList();
	public ArrayList field_35106_f = new ArrayList();

	public ComponentVillageStartPiece(WorldChunkManager worldChunkManager1, int i2, Random random3, int i4, int i5, ArrayList arrayList6, int i7) {
		super(0, random3, i4, i5);
		this.field_35111_a = worldChunkManager1;
		this.field_35107_d = arrayList6;
		this.field_35109_b = i7;
	}

	public WorldChunkManager func_35105_a() {
		return this.field_35111_a;
	}
}
