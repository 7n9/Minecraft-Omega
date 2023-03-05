package net.minecraft.src;

public class ItemVine extends ItemBlock {
	private final Block field_35436_a = Block.blocksList[this.func_35435_b()];

	public ItemVine(int i1, boolean z2) {
		super(i1);
		if(z2) {
			this.setMaxDamage(0);
			this.setHasSubtypes(true);
		}

	}

	public int getColorFromDamage(int i1) {
		return this.field_35436_a.func_35274_i();
	}

	public int getIconFromDamage(int i1) {
		return this.field_35436_a.getBlockTextureFromSideAndMetadata(0, i1);
	}

	public int getPlacedBlockMetadata(int i1) {
		return i1;
	}
}
