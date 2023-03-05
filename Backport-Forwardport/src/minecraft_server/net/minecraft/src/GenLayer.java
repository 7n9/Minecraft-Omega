package net.minecraft.src;

public abstract class GenLayer {
	private long field_35021_b;
	protected GenLayer field_35023_a;
	private long field_35022_c;
	private long field_35020_d;

	public static GenLayer[] func_35019_a(long j0) {
		LayerIsland layerIsland2 = new LayerIsland(1L);
		GenLayerZoomFuzzy genLayerZoomFuzzy9 = new GenLayerZoomFuzzy(2000L, layerIsland2);
		GenLayerIsland genLayerIsland10 = new GenLayerIsland(1L, genLayerZoomFuzzy9);
		GenLayerZoom genLayerZoom11 = new GenLayerZoom(2001L, genLayerIsland10);
		genLayerIsland10 = new GenLayerIsland(2L, genLayerZoom11);
		genLayerZoom11 = new GenLayerZoom(2002L, genLayerIsland10);
		genLayerIsland10 = new GenLayerIsland(3L, genLayerZoom11);
		genLayerZoom11 = new GenLayerZoom(2003L, genLayerIsland10);
		genLayerIsland10 = new GenLayerIsland(3L, genLayerZoom11);
		genLayerZoom11 = new GenLayerZoom(2004L, genLayerIsland10);
		genLayerIsland10 = new GenLayerIsland(3L, genLayerZoom11);
		byte b3 = 4;
		GenLayer genLayer4 = GenLayerZoom.func_35025_a(1000L, genLayerIsland10, 0);
		GenLayerRiverInit genLayerRiverInit12 = new GenLayerRiverInit(100L, genLayer4);
		genLayer4 = GenLayerZoom.func_35025_a(1000L, genLayerRiverInit12, b3 + 2);
		GenLayerRiver genLayerRiver13 = new GenLayerRiver(1L, genLayer4);
		GenLayerSmooth genLayerSmooth14 = new GenLayerSmooth(1000L, genLayerRiver13);
		GenLayer genLayer5 = GenLayerZoom.func_35025_a(1000L, genLayerIsland10, 0);
		GenLayerVillageLandscape genLayerVillageLandscape15 = new GenLayerVillageLandscape(200L, genLayer5);
		Object object16 = GenLayerZoom.func_35025_a(1000L, genLayerVillageLandscape15, 2);
		Object object6 = new GenLayerTemperature((GenLayer)object16);
		Object object7 = new GenLayerDownfall((GenLayer)object16);

		for(int i8 = 0; i8 < b3; ++i8) {
			object16 = new GenLayerZoom((long)(1000 + i8), (GenLayer)object16);
			if(i8 == 0) {
				object16 = new GenLayerIsland(3L, (GenLayer)object16);
			}

			GenLayerSmoothZoom genLayerSmoothZoom17 = new GenLayerSmoothZoom((long)(1000 + i8), (GenLayer)object6);
			object6 = new GenLayerTemperatureMix(genLayerSmoothZoom17, (GenLayer)object16, i8);
			GenLayerSmoothZoom genLayerSmoothZoom21 = new GenLayerSmoothZoom((long)(1000 + i8), (GenLayer)object7);
			object7 = new GenLayerDownfallMix(genLayerSmoothZoom21, (GenLayer)object16, i8);
		}

		GenLayerSmooth genLayerSmooth18 = new GenLayerSmooth(1000L, (GenLayer)object16);
		GenLayerRiverMix genLayerRiverMix20 = new GenLayerRiverMix(100L, genLayerSmooth18, genLayerSmooth14);
		GenLayer genLayer19 = GenLayerSmoothZoom.func_35030_a(1000L, (GenLayer)object6, 2);
		GenLayer genLayer22 = GenLayerSmoothZoom.func_35030_a(1000L, (GenLayer)object7, 2);
		GenLayerZoomVoronoi genLayerZoomVoronoi23 = new GenLayerZoomVoronoi(10L, genLayerRiverMix20);
		genLayerRiverMix20.func_35015_b(j0);
		genLayer19.func_35015_b(j0);
		genLayer22.func_35015_b(j0);
		genLayerZoomVoronoi23.func_35015_b(j0);
		return new GenLayer[]{genLayerRiverMix20, genLayerZoomVoronoi23, genLayer19, genLayer22};
	}

	public GenLayer(long j1) {
		this.field_35020_d = j1;
		this.field_35020_d *= this.field_35020_d * 6364136223846793005L + 1442695040888963407L;
		this.field_35020_d += j1;
		this.field_35020_d *= this.field_35020_d * 6364136223846793005L + 1442695040888963407L;
		this.field_35020_d += j1;
		this.field_35020_d *= this.field_35020_d * 6364136223846793005L + 1442695040888963407L;
		this.field_35020_d += j1;
	}

	public void func_35015_b(long j1) {
		this.field_35021_b = j1;
		if(this.field_35023_a != null) {
			this.field_35023_a.func_35015_b(j1);
		}

		this.field_35021_b *= this.field_35021_b * 6364136223846793005L + 1442695040888963407L;
		this.field_35021_b += this.field_35020_d;
		this.field_35021_b *= this.field_35021_b * 6364136223846793005L + 1442695040888963407L;
		this.field_35021_b += this.field_35020_d;
		this.field_35021_b *= this.field_35021_b * 6364136223846793005L + 1442695040888963407L;
		this.field_35021_b += this.field_35020_d;
	}

	public void func_35017_a(long j1, long j3) {
		this.field_35022_c = this.field_35021_b;
		this.field_35022_c *= this.field_35022_c * 6364136223846793005L + 1442695040888963407L;
		this.field_35022_c += j1;
		this.field_35022_c *= this.field_35022_c * 6364136223846793005L + 1442695040888963407L;
		this.field_35022_c += j3;
		this.field_35022_c *= this.field_35022_c * 6364136223846793005L + 1442695040888963407L;
		this.field_35022_c += j1;
		this.field_35022_c *= this.field_35022_c * 6364136223846793005L + 1442695040888963407L;
		this.field_35022_c += j3;
	}

	protected int func_35016_a(int i1) {
		int i2 = (int)((this.field_35022_c >> 24) % (long)i1);
		if(i2 < 0) {
			i2 += i1;
		}

		this.field_35022_c *= this.field_35022_c * 6364136223846793005L + 1442695040888963407L;
		this.field_35022_c += this.field_35021_b;
		return i2;
	}

	public abstract int[] func_35018_a(int i1, int i2, int i3, int i4);
}
