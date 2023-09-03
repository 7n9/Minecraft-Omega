package net.minecraft.src;

public class ChunkCoordIntPair {
	public final int chunkXPos;
	public final int chunkZPos;

	public ChunkCoordIntPair(int i1, int i2) {
		this.chunkXPos = i1;
		this.chunkZPos = i2;
	}

	public static long chunkXZ2Int(int i0, int i1) {
		long j2 = (long)i0;
		long j4 = (long)i1;
		return j2 & 4294967295L | (j4 & 4294967295L) << 32;
	}

	public int hashCode() {
		long j1 = chunkXZ2Int(this.chunkXPos, this.chunkZPos);
		int i3 = (int)j1;
		int i4 = (int)(j1 >> 32);
		return i3 ^ i4;
	}

	public boolean equals(Object object1) {
		ChunkCoordIntPair chunkCoordIntPair2 = (ChunkCoordIntPair)object1;
		return chunkCoordIntPair2.chunkXPos == this.chunkXPos && chunkCoordIntPair2.chunkZPos == this.chunkZPos;
	}
}