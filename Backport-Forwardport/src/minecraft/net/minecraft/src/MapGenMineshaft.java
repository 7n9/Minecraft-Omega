package net.minecraft.src;

public class MapGenMineshaft extends MapGenStructure {
	protected boolean func_35628_a(int i1, int i2) {
		return this.rand.nextInt(80) == 0 && this.rand.nextInt(80) < Math.max(Math.abs(i1), Math.abs(i2));
	}

	protected StructureStart func_35630_b(int i1, int i2) {
		return new StructureMineshaftStart(this.field_35625_d, this.rand, i1, i2);
	}
}
