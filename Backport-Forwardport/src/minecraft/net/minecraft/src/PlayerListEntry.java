package net.minecraft.src;

class PlayerListEntry {
	final long field_35834_a;
	Object field_35832_b;
	PlayerListEntry field_35833_c;
	final int field_35831_d;

	PlayerListEntry(int i1, long j2, Object object4, PlayerListEntry playerListEntry5) {
		this.field_35832_b = object4;
		this.field_35833_c = playerListEntry5;
		this.field_35834_a = j2;
		this.field_35831_d = i1;
	}

	public final long func_35830_a() {
		return this.field_35834_a;
	}

	public final Object func_35829_b() {
		return this.field_35832_b;
	}

	public final boolean equals(Object object1) {
		if(!(object1 instanceof PlayerListEntry)) {
			return false;
		} else {
			PlayerListEntry playerListEntry2 = (PlayerListEntry)object1;
			Long long3 = this.func_35830_a();
			Long long4 = playerListEntry2.func_35830_a();
			if(long3 == long4 || long3 != null && long3.equals(long4)) {
				Object object5 = this.func_35829_b();
				Object object6 = playerListEntry2.func_35829_b();
				if(object5 == object6 || object5 != null && object5.equals(object6)) {
					return true;
				}
			}

			return false;
		}
	}

	public final int hashCode() {
		return PlayerList.func_35566_f(this.field_35834_a);
	}

	public final String toString() {
		return this.func_35830_a() + "=" + this.func_35829_b();
	}
}
