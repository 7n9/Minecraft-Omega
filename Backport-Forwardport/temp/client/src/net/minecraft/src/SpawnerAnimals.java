package net.minecraft.src;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public final class SpawnerAnimals {
	private static Set eligibleChunksForSpawning = new HashSet();
	protected static final Class[] nightSpawnEntities = new Class[]{EntitySpider.class, EntityZombie.class, EntitySkeleton.class};

	protected static ChunkPosition getRandomSpawningPointInChunk(World world0, int i1, int i2) {
		int i3 = i1 + world0.rand.nextInt(16);
		Random random10000 = world0.rand;
		world0.getClass();
		int i4 = random10000.nextInt(128);
		int i5 = i2 + world0.rand.nextInt(16);
		return new ChunkPosition(i3, i4, i5);
	}

	public static final int performSpawning(World world0, boolean z1, boolean z2) {
		if(!z1 && !z2) {
			return 0;
		} else {
			eligibleChunksForSpawning.clear();

			int i3;
			int i6;
			for(i3 = 0; i3 < world0.playerEntities.size(); ++i3) {
				EntityPlayer entityPlayer4 = (EntityPlayer)world0.playerEntities.get(i3);
				int i5 = MathHelper.floor_double(entityPlayer4.posX / 16.0D);
				i6 = MathHelper.floor_double(entityPlayer4.posZ / 16.0D);
				byte b7 = 8;

				for(int i8 = -b7; i8 <= b7; ++i8) {
					for(int i9 = -b7; i9 <= b7; ++i9) {
						eligibleChunksForSpawning.add(new ChunkCoordIntPair(i8 + i5, i9 + i6));
					}
				}
			}

			i3 = 0;
			ChunkCoordinates chunkCoordinates33 = world0.getSpawnPoint();
			EnumCreatureType[] enumCreatureType34 = EnumCreatureType.values();
			i6 = enumCreatureType34.length;

			label112:
			for(int i35 = 0; i35 < i6; ++i35) {
				EnumCreatureType enumCreatureType36 = enumCreatureType34[i35];
				if((!enumCreatureType36.getPeacefulCreature() || z2) && (enumCreatureType36.getPeacefulCreature() || z1) && world0.countEntities(enumCreatureType36.getCreatureClass()) <= enumCreatureType36.getMaxNumberOfCreature() * eligibleChunksForSpawning.size() / 256) {
					Iterator iterator37 = eligibleChunksForSpawning.iterator();

					label109:
					while(true) {
						SpawnListEntry spawnListEntry13;
						int i15;
						int i16;
						int i17;
						do {
							do {
								ChunkCoordIntPair chunkCoordIntPair10;
								List list12;
								do {
									do {
										if(!iterator37.hasNext()) {
											continue label112;
										}

										chunkCoordIntPair10 = (ChunkCoordIntPair)iterator37.next();
										BiomeGenBase biomeGenBase11 = world0.getWorldChunkManager().getBiomeGenAtChunkCoord(chunkCoordIntPair10);
										list12 = biomeGenBase11.getSpawnableList(enumCreatureType36);
									} while(list12 == null);
								} while(list12.isEmpty());

								spawnListEntry13 = (SpawnListEntry)WeightedRandom.func_35733_a(world0.rand, list12);
								ChunkPosition chunkPosition14 = getRandomSpawningPointInChunk(world0, chunkCoordIntPair10.chunkXPos * 16, chunkCoordIntPair10.chunkZPos * 16);
								i15 = chunkPosition14.x;
								i16 = chunkPosition14.y;
								i17 = chunkPosition14.z;
							} while(world0.isBlockNormalCube(i15, i16, i17));
						} while(world0.getBlockMaterial(i15, i16, i17) != enumCreatureType36.getCreatureMaterial());

						int i18 = 0;

						for(int i19 = 0; i19 < 3; ++i19) {
							int i20 = i15;
							int i21 = i16;
							int i22 = i17;
							byte b23 = 6;

							for(int i24 = 0; i24 < 4; ++i24) {
								i20 += world0.rand.nextInt(b23) - world0.rand.nextInt(b23);
								i21 += world0.rand.nextInt(1) - world0.rand.nextInt(1);
								i22 += world0.rand.nextInt(b23) - world0.rand.nextInt(b23);
								if(canCreatureTypeSpawnAtLocation(enumCreatureType36, world0, i20, i21, i22)) {
									float f25 = (float)i20 + 0.5F;
									float f26 = (float)i21;
									float f27 = (float)i22 + 0.5F;
									if(world0.getClosestPlayer((double)f25, (double)f26, (double)f27, 24.0D) == null) {
										float f28 = f25 - (float)chunkCoordinates33.posX;
										float f29 = f26 - (float)chunkCoordinates33.posY;
										float f30 = f27 - (float)chunkCoordinates33.posZ;
										float f31 = f28 * f28 + f29 * f29 + f30 * f30;
										if(f31 >= 576.0F) {
											EntityLiving entityLiving38;
											try {
												entityLiving38 = (EntityLiving)spawnListEntry13.entityClass.getConstructor(new Class[]{World.class}).newInstance(new Object[]{world0});
											} catch (Exception exception32) {
												exception32.printStackTrace();
												return i3;
											}

											entityLiving38.setLocationAndAngles((double)f25, (double)f26, (double)f27, world0.rand.nextFloat() * 360.0F, 0.0F);
											if(entityLiving38.getCanSpawnHere()) {
												++i18;
												world0.entityJoinedWorld(entityLiving38);
												creatureSpecificInit(entityLiving38, world0, f25, f26, f27);
												if(i18 >= entityLiving38.getMaxSpawnedInChunk()) {
													continue label109;
												}
											}

											i3 += i18;
										}
									}
								}
							}
						}
					}
				}
			}

			return i3;
		}
	}

	private static boolean canCreatureTypeSpawnAtLocation(EnumCreatureType enumCreatureType0, World world1, int i2, int i3, int i4) {
		return enumCreatureType0.getCreatureMaterial() == Material.water ? world1.getBlockMaterial(i2, i3, i4).getIsLiquid() && !world1.isBlockNormalCube(i2, i3 + 1, i4) : world1.isBlockNormalCube(i2, i3 - 1, i4) && !world1.isBlockNormalCube(i2, i3, i4) && !world1.getBlockMaterial(i2, i3, i4).getIsLiquid() && !world1.isBlockNormalCube(i2, i3 + 1, i4);
	}

	private static void creatureSpecificInit(EntityLiving entityLiving0, World world1, float f2, float f3, float f4) {
		if(entityLiving0 instanceof EntitySpider && world1.rand.nextInt(100) == 0) {
			EntitySkeleton entitySkeleton5 = new EntitySkeleton(world1);
			entitySkeleton5.setLocationAndAngles((double)f2, (double)f3, (double)f4, entityLiving0.rotationYaw, 0.0F);
			world1.entityJoinedWorld(entitySkeleton5);
			entitySkeleton5.mountEntity(entityLiving0);
		} else if(entityLiving0 instanceof EntitySheep) {
			((EntitySheep)entityLiving0).setFleeceColor(EntitySheep.getRandomFleeceColor(world1.rand));
		}

	}

	public static boolean performSleepSpawning(World world0, List list1) {
		boolean z2 = false;
		Pathfinder pathfinder3 = new Pathfinder(world0);
		Iterator iterator4 = list1.iterator();

		while(true) {
			EntityPlayer entityPlayer5;
			Class[] class6;
			do {
				do {
					if(!iterator4.hasNext()) {
						return z2;
					}

					entityPlayer5 = (EntityPlayer)iterator4.next();
					class6 = nightSpawnEntities;
				} while(class6 == null);
			} while(class6.length == 0);

			boolean z7 = false;

			for(int i8 = 0; i8 < 20 && !z7; ++i8) {
				int i9 = MathHelper.floor_double(entityPlayer5.posX) + world0.rand.nextInt(32) - world0.rand.nextInt(32);
				int i10 = MathHelper.floor_double(entityPlayer5.posZ) + world0.rand.nextInt(32) - world0.rand.nextInt(32);
				int i11 = MathHelper.floor_double(entityPlayer5.posY) + world0.rand.nextInt(16) - world0.rand.nextInt(16);
				if(i11 < 1) {
					i11 = 1;
				} else {
					world0.getClass();
					if(i11 > 128) {
						world0.getClass();
						i11 = 128;
					}
				}

				int i12 = world0.rand.nextInt(class6.length);

				int i13;
				for(i13 = i11; i13 > 2 && !world0.isBlockNormalCube(i9, i13 - 1, i10); --i13) {
				}

				while(!canCreatureTypeSpawnAtLocation(EnumCreatureType.monster, world0, i9, i13, i10) && i13 < i11 + 16) {
					world0.getClass();
					if(i13 >= 128) {
						break;
					}

					++i13;
				}

				if(i13 < i11 + 16) {
					world0.getClass();
					if(i13 < 128) {
						float f14 = (float)i9 + 0.5F;
						float f15 = (float)i13;
						float f16 = (float)i10 + 0.5F;

						EntityLiving entityLiving17;
						try {
							entityLiving17 = (EntityLiving)class6[i12].getConstructor(new Class[]{World.class}).newInstance(new Object[]{world0});
						} catch (Exception exception21) {
							exception21.printStackTrace();
							return z2;
						}

						entityLiving17.setLocationAndAngles((double)f14, (double)f15, (double)f16, world0.rand.nextFloat() * 360.0F, 0.0F);
						if(entityLiving17.getCanSpawnHere()) {
							PathEntity pathEntity18 = pathfinder3.createEntityPathTo(entityLiving17, entityPlayer5, 32.0F);
							if(pathEntity18 != null && pathEntity18.pathLength > 1) {
								PathPoint pathPoint19 = pathEntity18.getPathEnd();
								if(Math.abs((double)pathPoint19.xCoord - entityPlayer5.posX) < 1.5D && Math.abs((double)pathPoint19.zCoord - entityPlayer5.posZ) < 1.5D && Math.abs((double)pathPoint19.yCoord - entityPlayer5.posY) < 1.5D) {
									ChunkCoordinates chunkCoordinates20 = BlockBed.getNearestEmptyChunkCoordinates(world0, MathHelper.floor_double(entityPlayer5.posX), MathHelper.floor_double(entityPlayer5.posY), MathHelper.floor_double(entityPlayer5.posZ), 1);
									if(chunkCoordinates20 == null) {
										chunkCoordinates20 = new ChunkCoordinates(i9, i13 + 1, i10);
									}

									entityLiving17.setLocationAndAngles((double)((float)chunkCoordinates20.posX + 0.5F), (double)chunkCoordinates20.posY, (double)((float)chunkCoordinates20.posZ + 0.5F), 0.0F, 0.0F);
									world0.entityJoinedWorld(entityLiving17);
									creatureSpecificInit(entityLiving17, world0, (float)chunkCoordinates20.posX + 0.5F, (float)chunkCoordinates20.posY, (float)chunkCoordinates20.posZ + 0.5F);
									entityPlayer5.wakeUpPlayer(true, false, false);
									entityLiving17.playLivingSound();
									z2 = true;
									z7 = true;
								}
							}
						}
					}
				}
			}
		}
	}

	public static void func_35957_a(World world0, BiomeGenBase biomeGenBase1, int i2, int i3, int i4, int i5, Random random6) {
		List list7 = biomeGenBase1.getSpawnableList(EnumCreatureType.creature);
		if(!list7.isEmpty()) {
			while(random6.nextFloat() < biomeGenBase1.getBiome()) {
				SpawnListEntry spawnListEntry8 = (SpawnListEntry)WeightedRandom.func_35733_a(world0.rand, list7);
				int i9 = spawnListEntry8.field_35591_b + random6.nextInt(1 + spawnListEntry8.field_35592_c - spawnListEntry8.field_35591_b);
				int i10 = i2 + random6.nextInt(i4);
				int i11 = i3 + random6.nextInt(i5);
				int i12 = i10;
				int i13 = i11;

				for(int i14 = 0; i14 < i9; ++i14) {
					boolean z15 = false;

					for(int i16 = 0; !z15 && i16 < 4; ++i16) {
						int i17 = world0.getTopSolidOrLiquidBlock(i10, i11);
						if(canCreatureTypeSpawnAtLocation(EnumCreatureType.creature, world0, i10, i17, i11)) {
							float f18 = (float)i10 + 0.5F;
							float f19 = (float)i17;
							float f20 = (float)i11 + 0.5F;

							EntityLiving entityLiving21;
							try {
								entityLiving21 = (EntityLiving)spawnListEntry8.entityClass.getConstructor(new Class[]{World.class}).newInstance(new Object[]{world0});
							} catch (Exception exception23) {
								exception23.printStackTrace();
								continue;
							}

							entityLiving21.setLocationAndAngles((double)f18, (double)f19, (double)f20, random6.nextFloat() * 360.0F, 0.0F);
							world0.entityJoinedWorld(entityLiving21);
							creatureSpecificInit(entityLiving21, world0, f18, f19, f20);
							z15 = true;
						}

						i10 += random6.nextInt(5) - random6.nextInt(5);

						for(i11 += random6.nextInt(5) - random6.nextInt(5); i10 < i2 || i10 >= i2 + i4 || i11 < i3 || i11 >= i3 + i4; i11 = i13 + random6.nextInt(5) - random6.nextInt(5)) {
							i10 = i12 + random6.nextInt(5) - random6.nextInt(5);
						}
					}
				}
			}

		}
	}
}
