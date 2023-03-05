package net.minecraft.src;

public class EntityCaveSpider extends EntitySpider {
	public EntityCaveSpider(World world1) {
		super(world1);
		this.texture = "/mob/cavespider.png";
		this.setSize(0.7F, 0.5F);
	}

	public float func_35188_k_() {
		return 0.7F;
	}

	protected boolean func_35175_b(Entity entity1) {
		if(super.func_35175_b(entity1)) {
			if(entity1 instanceof EntityLiving) {
				byte b2 = 0;
				if(this.worldObj.difficultySetting > 1) {
					if(this.worldObj.difficultySetting == 2) {
						b2 = 7;
					} else if(this.worldObj.difficultySetting == 3) {
						b2 = 15;
					}
				}

				if(b2 > 0) {
					((EntityLiving)entity1).func_35165_a(new PotionEffect(Potion.field_35689_u.field_35670_H, b2 * 20, 0));
				}
			}

			return true;
		} else {
			return false;
		}
	}
}
