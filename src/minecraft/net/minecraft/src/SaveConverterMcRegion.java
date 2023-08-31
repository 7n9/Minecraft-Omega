package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class SaveConverterMcRegion extends SaveFormatOld {
	public SaveConverterMcRegion(File file1) {
		super(file1);
	}

	public String func_22178_a() {
		return "Scaevolus' McRegion";
	}

	public List func_22176_b() {
		ArrayList arrayList1 = new ArrayList();
		File[] file2 = this.field_22180_a.listFiles();
		assert file2 != null;
		int i4 = file2.length;

		for (File file6 : file2) {
			if (file6.isDirectory()) {
				String string7 = file6.getName();
				WorldInfo worldInfo8 = this.func_22173_b(string7);
				if (worldInfo8 != null) {
					boolean z9 = worldInfo8.getSaveVersion() != 19132;
					String string10 = worldInfo8.getWorldName();
					if (string10 == null || MathHelper.stringNullOrLengthZero(string10)) {
						string10 = string7;
					}

					arrayList1.add(new SaveFormatComparator(string7, string10, worldInfo8.getLastTimePlayed(), worldInfo8.getSizeOnDisk(), z9));
				}
			}
		}

		return arrayList1;
	}

	public void flushCache() {
		RegionFileCache.func_22192_a();
	}

	public ISaveHandler getSaveLoader(String string1, boolean z2) {
		return new SaveOldDir(this.field_22180_a, string1, z2);
	}

	public boolean isOldMapFormat(String string1) {
		WorldInfo worldInfo2 = this.func_22173_b(string1);
		return worldInfo2 != null && worldInfo2.getSaveVersion() == 0;
	}

	public boolean convertMapFormat(String string1, IProgressUpdate iProgressUpdate2) {
		iProgressUpdate2.setLoadingProgress(0);
		ArrayList arrayList3 = new ArrayList();
		ArrayList arrayList4 = new ArrayList();
		ArrayList arrayList5 = new ArrayList();
		ArrayList arrayList6 = new ArrayList();
		File file7 = new File(this.field_22180_a, string1);
		File file8 = new File(file7, "DIM-1");
		System.out.println("Scanning folders...");
		this.func_22183_a(file7, arrayList3, arrayList4);
		if(file8.exists()) {
			this.func_22183_a(file8, arrayList5, arrayList6);
		}

		int i9 = arrayList3.size() + arrayList5.size() + arrayList4.size() + arrayList6.size();
		System.out.println("Total conversion count is " + i9);
		this.func_22181_a(file7, arrayList3, 0, i9, iProgressUpdate2);
		this.func_22181_a(file8, arrayList5, arrayList3.size(), i9, iProgressUpdate2);
		WorldInfo worldInfo10 = this.func_22173_b(string1);
		worldInfo10.setSaveVersion(19132);
		ISaveHandler iSaveHandler11 = this.getSaveLoader(string1, false);
		iSaveHandler11.saveWorldInfo(worldInfo10);
		this.func_22182_a(arrayList4, arrayList3.size() + arrayList5.size(), i9, iProgressUpdate2);
		if(file8.exists()) {
			this.func_22182_a(arrayList6, arrayList3.size() + arrayList5.size() + arrayList4.size(), i9, iProgressUpdate2);
		}

		return true;
	}

	private void func_22183_a(File file1, ArrayList arrayList2, ArrayList arrayList3) {
		ChunkFolderPattern chunkFolderPattern4 = new ChunkFolderPattern(null);
		ChunkFilePattern chunkFilePattern5 = new ChunkFilePattern(null);
		File[] file6 = file1.listFiles(chunkFolderPattern4);
		assert file6 != null;
		int i8 = file6.length;

		for (File file10 : file6) {
			arrayList3.add(file10);
			File[] file11 = file10.listFiles(chunkFolderPattern4);
			assert file11 != null;
			int i13 = file11.length;

			for (File file15 : file11) {
				File[] file16 = file15.listFiles(chunkFilePattern5);
				assert file16 != null;
				int i18 = file16.length;

				for (File file20 : file16) {
					arrayList2.add(new ChunkFile(file20));
				}
			}
		}

	}

	private void func_22181_a(File file1, ArrayList arrayList2, int i3, int i4, IProgressUpdate iProgressUpdate5) {
		Collections.sort(arrayList2);
		byte[] b6 = new byte[4096];

		for (Object o : arrayList2) {
			ChunkFile chunkFile8 = (ChunkFile) o;
			int i9 = chunkFile8.func_22323_b();
			int i10 = chunkFile8.func_22321_c();
			RegionFile regionFile11 = RegionFileCache.func_22193_a(file1, i9, i10);
			if (!regionFile11.func_22202_c(i9 & 31, i10 & 31)) {
				try {
					DataInputStream dataInputStream12 = new DataInputStream(new GZIPInputStream(Files.newInputStream(chunkFile8.func_22324_a().toPath())));
					DataOutputStream dataOutputStream13 = regionFile11.getChunkDataOutputStream(i9 & 31, i10 & 31);

					int i17;
					while ((i17 = dataInputStream12.read(b6)) != -1) {
						dataOutputStream13.write(b6, 0, i17);
					}

					dataOutputStream13.close();
					dataInputStream12.close();
				} catch (IOException iOException15) {
					iOException15.printStackTrace();
				}
			}

			++i3;
			int i16 = (int) Math.round(100.0D * (double) i3 / (double) i4);
			iProgressUpdate5.setLoadingProgress(i16);
		}

		RegionFileCache.func_22192_a();
	}

	private void func_22182_a(ArrayList arrayList1, int i2, int i3, IProgressUpdate iProgressUpdate4) {

		for (Object o : arrayList1) {
			File file6 = (File) o;
			File[] file7 = file6.listFiles();
			assert file7 != null;
			func_22179_a(file7);
			file6.delete();
			++i2;
			int i8 = (int) Math.round(100.0D * (double) i2 / (double) i3);
			iProgressUpdate4.setLoadingProgress(i8);
		}

	}
}
