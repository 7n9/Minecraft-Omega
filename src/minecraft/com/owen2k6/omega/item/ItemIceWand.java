package com.owen2k6.omega.item;

import com.owen2k6.omega.Omega;
import net.minecraft.src.*;

public class ItemIceWand extends Item {
	private int weaponDamage;

	public ItemIceWand(int i1) {
		super(i1);
		this.maxStackSize = 1;
	}

	//public float getStrVsBlock(ItemStack itemStack1, Block block2) {
		//return block2.blockID == Block.web.blockID ? 15.0F : 1.5F;
	//}

	public boolean hitEntity(ItemStack itemStack1, EntityLiving entityLiving2, EntityLiving entityLiving3) {
		itemStack1.damageItem(1, entityLiving3);
		return true;
	}

	public boolean onBlockDestroyed(ItemStack itemStack1, int i2, int i3, int i4, int i5, EntityLiving entityLiving6) {
		itemStack1.damageItem(2, entityLiving6);
		return false;
	}

	public int getDamageVsEntity(Entity entity1) {
		return 0;
	}


	public ItemStack onItemRightClick(ItemStack itemStack1, World world2, EntityPlayer entityPlayer3) {
		Omega.INSTANCE.mc.thePlayer.addChatMessage("I make ice when you right click water.");
		Omega.INSTANCE.mc.thePlayer.addChatMessage("Im not really useful for anything else.");

		return itemStack1;
	}

	public boolean onItemUse(ItemStack itemStack1, EntityPlayer entityPlayer2, World world3, int i4, int i5, int i6, int i7) {
		if(Omega.INSTANCE.manaSystem.get() < 5){
			Omega.INSTANCE.mc.thePlayer.addChatMessage("You don't have enough mana to do that!");
			return false;
		}

		if (world3.getBlockId(i4, i5, i6) == Block.waterStill.blockID) {
			Omega.INSTANCE.manaSystem.remove(5);
			world3.setBlockWithNotify(i4, i5+1, i6, Block.ice.blockID);
			if(world3.getBlockId(i4, i5+1, i6+1) == Block.waterStill.blockID)
				world3.setBlockWithNotify(i4, i5+1, i6+1, Block.ice.blockID);
			return true;
		} else if (world3.getBlockId(i4, i5, i6) == Block.grass.blockID) {
			Omega.INSTANCE.manaSystem.remove(5);
			world3.setBlockWithNotify(i4, i5+1, i6, Block.snow.blockID);
			return true;
		}
		else {
			Omega.INSTANCE.mc.thePlayer.addChatMessage("I can't really freeze this block.");
			return false;
		}
	}

	public boolean isFull3D() {
		return true;
	}

	public boolean canHarvestBlock(Block block1) {
		return false;
	}
}
