package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

public class IntCache {
	private static int field_35555_a = 256;
	private static List field_35553_b = new ArrayList();
	private static List field_35554_c = new ArrayList();
	private static List field_35551_d = new ArrayList();
	private static List field_35552_e = new ArrayList();

	public static int[] func_35549_a(int i0) {
		int[] i1;
		if(i0 <= 256) {
			if(field_35553_b.size() == 0) {
				i1 = new int[256];
				field_35554_c.add(i1);
				return i1;
			} else {
				i1 = (int[])field_35553_b.remove(field_35553_b.size() - 1);
				field_35554_c.add(i1);
				return i1;
			}
		} else if(i0 > field_35555_a) {
			System.out.println("New max size: " + i0);
			field_35555_a = i0;
			field_35551_d.clear();
			field_35552_e.clear();
			i1 = new int[field_35555_a];
			field_35552_e.add(i1);
			return i1;
		} else if(field_35551_d.size() == 0) {
			i1 = new int[field_35555_a];
			field_35552_e.add(i1);
			return i1;
		} else {
			i1 = (int[])field_35551_d.remove(field_35551_d.size() - 1);
			field_35552_e.add(i1);
			return i1;
		}
	}

	public static void func_35550_a() {
		if(field_35551_d.size() > 0) {
			field_35551_d.remove(field_35551_d.size() - 1);
		}

		if(field_35553_b.size() > 0) {
			field_35553_b.remove(field_35553_b.size() - 1);
		}

		field_35551_d.addAll(field_35552_e);
		field_35553_b.addAll(field_35554_c);
		field_35552_e.clear();
		field_35554_c.clear();
	}
}
