package net.minecraft.src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChunkProvider implements IChunkProvider {
	private Set droppedChunksSet = new HashSet();
	private Chunk field_28064_b;
	private IChunkProvider chunkProvider;
	private IChunkLoader chunkLoader;
	private PlayerList chunkMap = new PlayerList();
	private List chunkList = new ArrayList();
	private World worldObj;
	private int field_35392_h;

	public ChunkProvider(World world1, IChunkLoader iChunkLoader2, IChunkProvider iChunkProvider3) {
		world1.getClass();
		this.field_28064_b = new EmptyChunk(world1, new byte[256 * 128], 0, 0);
		this.worldObj = world1;
		this.chunkLoader = iChunkLoader2;
		this.chunkProvider = iChunkProvider3;
	}

	public boolean chunkExists(int i1, int i2) {
		return this.chunkMap.func_35575_b(ChunkCoordIntPair.chunkXZ2Int(i1, i2));
	}

	public void func_35391_d(int i1, int i2) {
		ChunkCoordinates chunkCoordinates3 = this.worldObj.getSpawnPoint();
		int i4 = i1 * 16 + 8 - chunkCoordinates3.posX;
		int i5 = i2 * 16 + 8 - chunkCoordinates3.posZ;
		short s6 = 128;
		if(i4 < -s6 || i4 > s6 || i5 < -s6 || i5 > s6) {
			this.droppedChunksSet.add(ChunkCoordIntPair.chunkXZ2Int(i1, i2));
		}

	}

	public Chunk loadChunk(int i1, int i2) {
		long j3 = ChunkCoordIntPair.chunkXZ2Int(i1, i2);
		this.droppedChunksSet.remove(j3);
		Chunk chunk5 = (Chunk)this.chunkMap.func_35578_a(j3);
		if(chunk5 == null) {
			int i6 = 1875004;
			if(i1 < -i6 || i2 < -i6 || i1 >= i6 || i2 >= i6) {
				return this.field_28064_b;
			}

			chunk5 = this.loadChunkFromFile(i1, i2);
			if(chunk5 == null) {
				if(this.chunkProvider == null) {
					chunk5 = this.field_28064_b;
				} else {
					chunk5 = this.chunkProvider.provideChunk(i1, i2);
				}
			}

			this.chunkMap.func_35577_a(j3, chunk5);
			this.chunkList.add(chunk5);
			if(chunk5 != null) {
				chunk5.func_4143_d();
				chunk5.onChunkLoad();
			}

			chunk5.func_35843_a(this, this, i1, i2);
		}

		return chunk5;
	}

	public Chunk provideChunk(int i1, int i2) {
		Chunk chunk3 = (Chunk)this.chunkMap.func_35578_a(ChunkCoordIntPair.chunkXZ2Int(i1, i2));
		return chunk3 == null ? this.loadChunk(i1, i2) : chunk3;
	}

	private Chunk loadChunkFromFile(int i1, int i2) {
		if(this.chunkLoader == null) {
			return null;
		} else {
			try {
				Chunk chunk3 = this.chunkLoader.loadChunk(this.worldObj, i1, i2);
				if(chunk3 != null) {
					chunk3.lastSaveTime = this.worldObj.getWorldTime();
				}

				return chunk3;
			} catch (Exception exception4) {
				exception4.printStackTrace();
				return null;
			}
		}
	}

	private void func_28063_a(Chunk chunk1) {
		if(this.chunkLoader != null) {
			try {
				this.chunkLoader.saveExtraChunkData(this.worldObj, chunk1);
			} catch (Exception exception3) {
				exception3.printStackTrace();
			}

		}
	}

	private void func_28062_b(Chunk chunk1) {
		if(this.chunkLoader != null) {
			try {
				chunk1.lastSaveTime = this.worldObj.getWorldTime();
				this.chunkLoader.saveChunk(this.worldObj, chunk1);
			} catch (IOException iOException3) {
				iOException3.printStackTrace();
			}

		}
	}

	public void populate(IChunkProvider iChunkProvider1, int i2, int i3) {
		Chunk chunk4 = this.provideChunk(i2, i3);
		if(!chunk4.isTerrainPopulated) {
			chunk4.isTerrainPopulated = true;
			if(this.chunkProvider != null) {
				this.chunkProvider.populate(iChunkProvider1, i2, i3);
				chunk4.setChunkModified();
			}
		}

	}

	public boolean saveChunks(boolean z1, IProgressUpdate iProgressUpdate2) {
		int i3 = 0;

		for(int i4 = 0; i4 < this.chunkList.size(); ++i4) {
			Chunk chunk5 = (Chunk)this.chunkList.get(i4);
			if(z1 && !chunk5.neverSave) {
				this.func_28063_a(chunk5);
			}

			if(chunk5.needsSaving(z1)) {
				this.func_28062_b(chunk5);
				chunk5.isModified = false;
				++i3;
				if(i3 == 24 && !z1) {
					return false;
				}
			}
		}

		if(z1) {
			if(this.chunkLoader == null) {
				return true;
			}

			this.chunkLoader.saveExtraData();
		}

		return true;
	}

	public boolean unload100OldestChunks() {
		int i1;
		for(i1 = 0; i1 < 100; ++i1) {
			if(!this.droppedChunksSet.isEmpty()) {
				Long long2 = (Long)this.droppedChunksSet.iterator().next();
				Chunk chunk3 = (Chunk)this.chunkMap.func_35578_a(long2.longValue());
				chunk3.onChunkUnload();
				this.func_28062_b(chunk3);
				this.func_28063_a(chunk3);
				this.droppedChunksSet.remove(long2);
				this.chunkMap.func_35574_d(long2.longValue());
				this.chunkList.remove(chunk3);
			}
		}

		for(i1 = 0; i1 < 10; ++i1) {
			if(this.field_35392_h >= this.chunkList.size()) {
				this.field_35392_h = 0;
				break;
			}

			Chunk chunk4 = (Chunk)this.chunkList.get(this.field_35392_h++);
			EntityPlayer entityPlayer5 = this.worldObj.getClosestPlayer((double)(chunk4.xPosition << 4) + 8.0D, 64.0D, (double)(chunk4.zPosition << 4) + 8.0D, 288.0D);
			if(entityPlayer5 == null) {
				this.func_35391_d(chunk4.xPosition, chunk4.zPosition);
			}
		}

		if(this.chunkLoader != null) {
			this.chunkLoader.func_814_a();
		}

		return this.chunkProvider.unload100OldestChunks();
	}

	public boolean canSave() {
		return true;
	}

	public String makeString() {
		return "ServerChunkCache: " + this.chunkMap.func_35576_a() + " Drop: " + this.droppedChunksSet.size();
	}
}
