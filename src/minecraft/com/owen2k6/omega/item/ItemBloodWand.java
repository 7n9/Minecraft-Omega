package com.owen2k6.omega.item;

import com.owen2k6.omega.Omega;
import net.minecraft.src.*;

public class ItemBloodWand extends Item {
	private int weaponDamage;

	public ItemBloodWand(int i1) {
		super(i1);
		this.maxStackSize = 1;
	}

	//public float getStrVsBlock(ItemStack itemStack1, Block block2) {
		//return block2.blockID == Block.web.blockID ? 15.0F : 1.5F;
	//}

	public boolean hitEntity(ItemStack itemStack1, EntityLiving entityLiving2, EntityLiving entityLiving3) {
		if(Omega.INSTANCE.manaSystem.get() < 20){
			Omega.INSTANCE.mc.thePlayer.addChatMessage("You need full mana to do this.");
			Omega.INSTANCE.mc.thePlayer.addChatMessage("Try again when you have full mana.");
			return false;
		}
		Omega.INSTANCE.manaSystem.set(0);
		Omega.INSTANCE.staminaSystem.set(0);
		Omega.INSTANCE.mc.thePlayer.heal(10000);
		Omega.INSTANCE.mc.thePlayer.addChatMessage("\u00A7cYou killed a living thing to heal yourself...");
		Omega.INSTANCE.mc.thePlayer.addChatMessage("\u00A7cThe healing process has made you tired and drained your mana");
		Omega.INSTANCE.mc.thePlayer.addChatMessage("\u00A7cYou'll need to rest for a few seconds.");
		Omega.INSTANCE.staminaSystem.hold(200);
		return true;
	}

	public boolean onBlockDestroyed(ItemStack itemStack1, int i2, int i3, int i4, int i5, EntityLiving entityLiving6) {
		itemStack1.damageItem(2, entityLiving6);
		return false;
	}

	public int getDamageVsEntity(Entity entity1) {
		if(Omega.INSTANCE.manaSystem.get() < 20 && Omega.INSTANCE.staminaSystem.get() < 10){
			Omega.INSTANCE.mc.thePlayer.addChatMessage("You like killing things with me dont you?");
			Omega.INSTANCE.mc.thePlayer.addChatMessage("Im afraid that you can't use me again until you are rested.");
			Omega.INSTANCE.mc.thePlayer.addChatMessage("Since you awoke me, i will take some of your life.");
			Omega.INSTANCE.mc.thePlayer.attackEntityFrom((Entity)null, 1);
			return 0;
		}
		if(Omega.INSTANCE.manaSystem.get() < 20){
			Omega.INSTANCE.mc.thePlayer.addChatMessage("You need full mana to do this.");
			Omega.INSTANCE.mc.thePlayer.addChatMessage("Try again when you have full mana.");
			return 0;
		}
		return 10000;
	}

	public boolean onItemUse(ItemStack itemStack1, EntityPlayer entityPlayer2, World world3, int i4, int i5, int i6, int i7) {

//		if(Omega.INSTANCE.manaSystem.get() < 9){
//			Omega.INSTANCE.mc.thePlayer.addChatMessage("You don't have enough mana to do that!");
//			return false;
//		}





		return true;
	}

	public boolean isFull3D() {
		return true;
	}

	public boolean canHarvestBlock(Block block1) {
		return false;
	}
}
