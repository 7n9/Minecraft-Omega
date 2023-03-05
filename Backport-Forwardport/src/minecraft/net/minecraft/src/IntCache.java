package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

public class IntCache {
	private static int field_35273_a = 256;
	private static List field_35271_b = new ArrayList();
	private static List field_35272_c = new ArrayList();
	private static List field_35269_d = new ArrayList();
	private static List field_35270_e = new ArrayList();

	public static int[] func_35267_a(int i0) {
		int[] i1;
		if(i0 <= 256) {
			if(field_35271_b.size() == 0) {
				i1 = new int[256];
				field_35272_c.add(i1);
				return i1;
			} else {
				i1 = (int[])field_35271_b.remove(field_35271_b.size() - 1);
				field_35272_c.add(i1);
				return i1;
			}
		} else if(i0 > field_35273_a) {
			System.out.println("New max size: " + i0);
			field_35273_a = i0;
			field_35269_d.clear();
			field_35270_e.clear();
			i1 = new int[field_35273_a];
			field_35270_e.add(i1);
			return i1;
		} else if(field_35269_d.size() == 0) {
			i1 = new int[field_35273_a];
			field_35270_e.add(i1);
			return i1;
		} else {
			i1 = (int[])field_35269_d.remove(field_35269_d.size() - 1);
			field_35270_e.add(i1);
			return i1;
		}
	}

	public static void func_35268_a() {
		if(field_35269_d.size() > 0) {
			field_35269_d.remove(field_35269_d.size() - 1);
		}

		if(field_35271_b.size() > 0) {
			field_35271_b.remove(field_35271_b.size() - 1);
		}

		field_35269_d.addAll(field_35270_e);
		field_35271_b.addAll(field_35272_c);
		field_35270_e.clear();
		field_35272_c.clear();
	}
}
