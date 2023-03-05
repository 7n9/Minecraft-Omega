package net.minecraft.src;

import java.io.File;
import java.util.regex.Matcher;

class ChunkFile implements Comparable {
	private final File chunkFile;
	private final int xChunk;
	private final int yChunk;

	public ChunkFile(File file1) {
		this.chunkFile = file1;
		Matcher matcher2 = ChunkFilePattern.dataFilenamePattern.matcher(file1.getName());
		if(matcher2.matches()) {
			this.xChunk = Integer.parseInt(matcher2.group(1), 36);
			this.yChunk = Integer.parseInt(matcher2.group(2), 36);
		} else {
			this.xChunk = 0;
			this.yChunk = 0;
		}

	}

	public int compareChunks(ChunkFile chunkFile1) {
		int i2 = this.xChunk >> 5;
		int i3 = chunkFile1.xChunk >> 5;
		if(i2 == i3) {
			int i4 = this.yChunk >> 5;
			int i5 = chunkFile1.yChunk >> 5;
			return i4 - i5;
		} else {
			return i2 - i3;
		}
	}

	public File getChunkFile() {
		return this.chunkFile;
	}

	public int getXChunk() {
		return this.xChunk;
	}

	public int getYChunk() {
		return this.yChunk;
	}

	public int compareTo(Object object1) {
		return this.compareChunks((ChunkFile)object1);
	}
}
