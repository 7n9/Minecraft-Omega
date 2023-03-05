package net.minecraft.src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class KeyBinding {
	public static List field_35967_a = new ArrayList();
	public static MCHash field_35966_b = new MCHash();
	public String keyDescription;
	public int keyCode;
	public boolean field_35965_e;
	public int field_35964_f = 0;

	public static void func_35960_a(int i0) {
		KeyBinding keyBinding1 = (KeyBinding)field_35966_b.lookup(i0);
		if(keyBinding1 != null) {
			++keyBinding1.field_35964_f;
		}

	}

	public static void func_35963_a(int i0, boolean z1) {
		KeyBinding keyBinding2 = (KeyBinding)field_35966_b.lookup(i0);
		if(keyBinding2 != null) {
			keyBinding2.field_35965_e = z1;
		}

	}

	public static void func_35959_a() {
		Iterator iterator0 = field_35967_a.iterator();

		while(iterator0.hasNext()) {
			KeyBinding keyBinding1 = (KeyBinding)iterator0.next();
			keyBinding1.func_35958_d();
		}

	}

	public static void func_35961_b() {
		field_35966_b.clearMap();
		Iterator iterator0 = field_35967_a.iterator();

		while(iterator0.hasNext()) {
			KeyBinding keyBinding1 = (KeyBinding)iterator0.next();
			field_35966_b.addKey(keyBinding1.keyCode, keyBinding1);
		}

	}

	public KeyBinding(String string1, int i2) {
		this.keyDescription = string1;
		this.keyCode = i2;
		field_35967_a.add(this);
		field_35966_b.addKey(i2, this);
	}

	public boolean func_35962_c() {
		if(this.field_35964_f == 0) {
			return false;
		} else {
			--this.field_35964_f;
			return true;
		}
	}

	private void func_35958_d() {
		this.field_35964_f = 0;
		this.field_35965_e = false;
	}
}
