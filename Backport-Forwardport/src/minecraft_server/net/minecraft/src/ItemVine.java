package net.minecraft.src;

public class ItemVine extends ItemBlock {
	private final Block field_35421_a = Block.blocksList[this.func_35419_a()];

	public ItemVine(int i1, boolean z2) {
		super(i1);
		if(z2) {
			this.setMaxDamage(0);
			this.setHasSubtypes(true);
		}

	}

	public int getMetadata(int i1) {
		return i1;
	}
}
