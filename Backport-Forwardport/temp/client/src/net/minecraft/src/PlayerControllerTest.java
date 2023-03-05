package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class PlayerControllerTest extends PlayerController {
	private int field_35647_c;

	public PlayerControllerTest(Minecraft minecraft1) {
		super(minecraft1);
		this.isInTestMode = true;
	}

	public static void func_35646_d(EntityPlayer entityPlayer0) {
		entityPlayer0.field_35212_aW.field_35758_c = true;
		entityPlayer0.field_35212_aW.field_35756_d = true;
		entityPlayer0.field_35212_aW.field_35759_a = true;
	}

	public static void func_35645_e(EntityPlayer entityPlayer0) {
		entityPlayer0.field_35212_aW.field_35758_c = false;
		entityPlayer0.field_35212_aW.field_35757_b = false;
		entityPlayer0.field_35212_aW.field_35756_d = false;
		entityPlayer0.field_35212_aW.field_35759_a = false;
	}

	public void func_6473_b(EntityPlayer entityPlayer1) {
		func_35646_d(entityPlayer1);

		for(int i2 = 0; i2 < 9; ++i2) {
			if(entityPlayer1.inventory.mainInventory[i2] == null) {
				entityPlayer1.inventory.mainInventory[i2] = new ItemStack((Block)Session.registeredBlocksList.get(i2));
			}
		}

	}

	public static void func_35644_a(Minecraft minecraft0, PlayerController playerController1, int i2, int i3, int i4, int i5) {
		minecraft0.theWorld.onBlockHit(minecraft0.thePlayer, i2, i3, i4, i5);
		playerController1.sendBlockRemoved(i2, i3, i4, i5);
	}

	public boolean sendPlaceBlock(EntityPlayer entityPlayer1, World world2, ItemStack itemStack3, int i4, int i5, int i6, int i7) {
		int i8 = world2.getBlockId(i4, i5, i6);
		if(i8 > 0 && Block.blocksList[i8].blockActivated(world2, i4, i5, i6, entityPlayer1)) {
			return true;
		} else if(itemStack3 == null) {
			return false;
		} else {
			int i9 = itemStack3.getItemDamage();
			int i10 = itemStack3.stackSize;
			boolean z11 = itemStack3.useItem(entityPlayer1, world2, i4, i5, i6, i7);
			itemStack3.setItemDamage(i9);
			itemStack3.stackSize = i10;
			return z11;
		}
	}

	public void clickBlock(int i1, int i2, int i3, int i4) {
		func_35644_a(this.mc, this, i1, i2, i3, i4);
		this.field_35647_c = 5;
	}

	public void sendBlockRemoving(int i1, int i2, int i3, int i4) {
		--this.field_35647_c;
		if(this.field_35647_c <= 0) {
			this.field_35647_c = 5;
			func_35644_a(this.mc, this, i1, i2, i3, i4);
		}

	}

	public void resetBlockRemoving() {
	}

	public boolean shouldDrawHUD() {
		return false;
	}

	public void func_717_a(World world1) {
		super.func_717_a(world1);
	}

	public float getBlockReachDistance() {
		return 5.0F;
	}

	public boolean func_35641_g() {
		return false;
	}

	public boolean func_35640_h() {
		return true;
	}

	public boolean func_35636_i() {
		return true;
	}
}
