package net.minecraft.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChunkProviderClient implements IChunkProvider {
	private Chunk blankChunk;
	private PlayerList chunkMapping = new PlayerList();
	private List field_889_c = new ArrayList();
	private World worldObj;

	public ChunkProviderClient(World world1) {
		world1.getClass();
		this.blankChunk = new EmptyChunk(world1, new byte[256 * 128], 0, 0);
		this.worldObj = world1;
	}

	public boolean chunkExists(int i1, int i2) {
		return this != null ? true : this.chunkMapping.func_35575_b(ChunkCoordIntPair.chunkXZ2Int(i1, i2));
	}

	public void func_539_c(int i1, int i2) {
		Chunk chunk3 = this.provideChunk(i1, i2);
		if(!chunk3.func_21167_h()) {
			chunk3.onChunkUnload();
		}

		this.chunkMapping.func_35574_d(ChunkCoordIntPair.chunkXZ2Int(i1, i2));
		this.field_889_c.remove(chunk3);
	}

	public Chunk loadChunk(int i1, int i2) {
		this.worldObj.getClass();
		byte[] b3 = new byte[256 * 128];
		Chunk chunk4 = new Chunk(this.worldObj, b3, i1, i2);
		Arrays.fill(chunk4.skylightMap.data, (byte)-1);
		this.chunkMapping.func_35577_a(ChunkCoordIntPair.chunkXZ2Int(i1, i2), chunk4);
		chunk4.isChunkLoaded = true;
		return chunk4;
	}

	public Chunk provideChunk(int i1, int i2) {
		Chunk chunk3 = (Chunk)this.chunkMapping.func_35578_a(ChunkCoordIntPair.chunkXZ2Int(i1, i2));
		return chunk3 == null ? this.blankChunk : chunk3;
	}

	public boolean saveChunks(boolean z1, IProgressUpdate iProgressUpdate2) {
		return true;
	}

	public boolean unload100OldestChunks() {
		return false;
	}

	public boolean canSave() {
		return false;
	}

	public void populate(IChunkProvider iChunkProvider1, int i2, int i3) {
	}

	public String makeString() {
		return "MultiplayerChunkCache: " + this.chunkMapping.func_35576_a();
	}
}
