package net.minecraft.src;

public class ItemLog extends ItemBlock {
	private Block field_35420_a;

	public ItemLog(int i1, Block block2) {
		super(i1);
		this.field_35420_a = block2;
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	public int getMetadata(int i1) {
		return i1;
	}
}
