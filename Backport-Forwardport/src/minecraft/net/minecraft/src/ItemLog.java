package net.minecraft.src;

public class ItemLog extends ItemBlock {
	private Block field_35437_a;

	public ItemLog(int i1, Block block2) {
		super(i1);
		this.field_35437_a = block2;
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	public int getIconFromDamage(int i1) {
		return this.field_35437_a.getBlockTextureFromSideAndMetadata(2, i1);
	}

	public int getPlacedBlockMetadata(int i1) {
		return i1;
	}
}
