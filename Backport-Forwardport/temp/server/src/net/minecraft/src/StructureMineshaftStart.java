package net.minecraft.src;

import java.util.Random;

public class StructureMineshaftStart extends StructureStart {
	public StructureMineshaftStart(World world1, Random random2, int i3, int i4) {
		ComponentMineshaftRoom componentMineshaftRoom5 = new ComponentMineshaftRoom(0, random2, (i3 << 4) + 2, (i4 << 4) + 2);
		this.field_35547_a.add(componentMineshaftRoom5);
		componentMineshaftRoom5.func_35308_a(componentMineshaftRoom5, this.field_35547_a, random2);
		this.func_35544_c();
		this.func_35545_a(world1, random2, 10);
	}
}
