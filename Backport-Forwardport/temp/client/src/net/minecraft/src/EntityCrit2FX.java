package net.minecraft.src;

public class EntityCrit2FX extends EntityFX {
	private Entity field_35134_a;
	private int field_35133_ay = 0;
	private int field_35132_az = 0;

	public EntityCrit2FX(World world1, Entity entity2) {
		super(world1, entity2.posX, entity2.boundingBox.minY + (double)(entity2.height / 2.0F), entity2.posZ, entity2.motionX, entity2.motionY, entity2.motionZ);
		this.field_35134_a = entity2;
		this.field_35132_az = 3;
		this.onUpdate();
	}

	public void renderParticle(Tessellator tessellator1, float f2, float f3, float f4, float f5, float f6, float f7) {
	}

	public void onUpdate() {
		for(int i1 = 0; i1 < 16; ++i1) {
			double d2 = (double)(this.rand.nextFloat() * 2.0F - 1.0F);
			double d4 = (double)(this.rand.nextFloat() * 2.0F - 1.0F);
			double d6 = (double)(this.rand.nextFloat() * 2.0F - 1.0F);
			if(d2 * d2 + d4 * d4 + d6 * d6 <= 1.0D) {
				double d8 = this.field_35134_a.posX + d2 * (double)this.field_35134_a.width / 4.0D;
				double d10 = this.field_35134_a.boundingBox.minY + (double)(this.field_35134_a.height / 2.0F) + d4 * (double)this.field_35134_a.height / 4.0D;
				double d12 = this.field_35134_a.posZ + d6 * (double)this.field_35134_a.width / 4.0D;
				this.worldObj.spawnParticle("crit", d8, d10, d12, d2, d4 + 0.2D, d6);
			}
		}

		++this.field_35133_ay;
		if(this.field_35133_ay >= this.field_35132_az) {
			this.setEntityDead();
		}

	}

	public int getFXLayer() {
		return 3;
	}
}
