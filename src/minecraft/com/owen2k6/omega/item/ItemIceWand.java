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
        if (Omega.INSTANCE.manaSystem.get() < 4) {
            Omega.INSTANCE.mc.thePlayer.addChatMessage("You don't have enough mana to do that!");
            return itemStack1;
        }
        float f4 = 1.0F;
        float f5 = entityPlayer3.prevRotationPitch + (entityPlayer3.rotationPitch - entityPlayer3.prevRotationPitch) * f4;
        float f6 = entityPlayer3.prevRotationYaw + (entityPlayer3.rotationYaw - entityPlayer3.prevRotationYaw) * f4;
        double d7 = entityPlayer3.prevPosX + (entityPlayer3.posX - entityPlayer3.prevPosX) * (double) f4;
        double d9 = entityPlayer3.prevPosY + (entityPlayer3.posY - entityPlayer3.prevPosY) * (double) f4 + 1.62D - (double) entityPlayer3.yOffset;
        double d11 = entityPlayer3.prevPosZ + (entityPlayer3.posZ - entityPlayer3.prevPosZ) * (double) f4;
        Vec3D vec3D13 = Vec3D.createVector(d7, d9, d11);
        float f14 = MathHelper.cos(-f6 * 0.017453292F - (float) Math.PI);
        float f15 = MathHelper.sin(-f6 * 0.017453292F - (float) Math.PI);
        float f16 = -MathHelper.cos(-f5 * 0.017453292F);
        float f17 = MathHelper.sin(-f5 * 0.017453292F);
        float f18 = f15 * f16;
        float f20 = f14 * f16;
        double d21 = 5.0D;
        Vec3D vec3D23 = vec3D13.addVector((double) f18 * d21, (double) f17 * d21, (double) f20 * d21);
        MovingObjectPosition movingObjectPosition24 = world2.rayTraceBlocks_do(vec3D13, vec3D23, true);
        if (movingObjectPosition24 == null) {
            return itemStack1;
        } else {
            if (movingObjectPosition24.typeOfHit == EnumMovingObjectType.TILE) {
                int i25 = movingObjectPosition24.blockX;
                int i26 = movingObjectPosition24.blockY;
                int i27 = movingObjectPosition24.blockZ;
                if (!world2.func_6466_a(entityPlayer3, i25, i26, i27)) {
                    return itemStack1;
                }
                if (world2.getBlockMaterial(i25, i26, i27) == Material.water && world2.getBlockMetadata(i25, i26, i27) == 0) {
                    Omega.INSTANCE.manaSystem.remove(3);
                    world2.setBlockWithNotify(i25, i26, i27, Block.ice.blockID);
                    return itemStack1;
                }

                if (world2.getBlockId(i25, i26, i27) == Block.grass.blockID) {
                    if (world2.getBlockId(i25, i26+1, i27) == 0) {
                        Omega.INSTANCE.manaSystem.remove(3);
                        world2.setBlockWithNotify(i25, i26+1, i27, Block.snow.blockID);
                        return itemStack1;
                    }
                    if (world2.getBlockId(i25, i26+1, i27) == Block.tallGrass.blockID) {
                        Omega.INSTANCE.manaSystem.remove(3);
                        world2.setBlockWithNotify(i25, i26+1, i27, Block.snow.blockID);
                        return itemStack1;
                    }

                    return itemStack1;


                }
                if (world2.getBlockId(i25, i26, i27) == Block.tallGrass.blockID) {
                        Omega.INSTANCE.manaSystem.remove(3);
                        world2.setBlockWithNotify(i25, i26, i27, Block.snow.blockID);
                        return itemStack1;
                } else {

                    if (movingObjectPosition24.sideHit == 0) {
                        --i26;
                    }

                    if (movingObjectPosition24.sideHit == 1) {
                        ++i26;
                    }

                    if (movingObjectPosition24.sideHit == 2) {
                        --i27;
                    }

                    if (movingObjectPosition24.sideHit == 3) {
                        ++i27;
                    }

                    if (movingObjectPosition24.sideHit == 4) {
                        --i25;
                    }

                    if (movingObjectPosition24.sideHit == 5) {
                        ++i25;
                    }

                    if (world2.isAirBlock(i25, i26, i27) || !world2.getBlockMaterial(i25, i26, i27).isSolid()) {
                        world2.playSoundEffect(d7 + 0.5D, d9 + 0.5D, d11 + 0.5D, "random.fizz", 0.5F, 2.6F + (world2.rand.nextFloat() - world2.rand.nextFloat()) * 0.8F);
                        world2.spawnParticle("largesmoke", (double) i25 + Math.random(), (double) i26 + Math.random(), (double) i27 + Math.random(), 0.0D, 0.0D, 0.0D);
                    }
                }
            }

            return itemStack1;
        }
    }

    public boolean isFull3D() {
        return true;
    }

    public boolean canHarvestBlock(Block block1) {
        return false;
    }
}
