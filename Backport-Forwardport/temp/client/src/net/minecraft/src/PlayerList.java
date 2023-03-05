package net.minecraft.src;

public class PlayerList {
	private transient PlayerListEntry[] field_35584_a = new PlayerListEntry[16];
	private transient int field_35582_b;
	private int field_35583_c = 12;
	private final float field_35580_d = 0.75F;
	private transient volatile int field_35581_e;

	private static int func_35568_g(long j0) {
		return func_35571_a((int)(j0 ^ j0 >>> 32));
	}

	private static int func_35571_a(int i0) {
		i0 ^= i0 >>> 20 ^ i0 >>> 12;
		return i0 ^ i0 >>> 7 ^ i0 >>> 4;
	}

	private static int func_35573_a(int i0, int i1) {
		return i0 & i1 - 1;
	}

	public int func_35576_a() {
		return this.field_35582_b;
	}

	public Object func_35578_a(long j1) {
		int i3 = func_35568_g(j1);

		for(PlayerListEntry playerListEntry4 = this.field_35584_a[func_35573_a(i3, this.field_35584_a.length)]; playerListEntry4 != null; playerListEntry4 = playerListEntry4.field_35833_c) {
			if(playerListEntry4.field_35834_a == j1) {
				return playerListEntry4.field_35832_b;
			}
		}

		return null;
	}

	public boolean func_35575_b(long j1) {
		return this.func_35569_c(j1) != null;
	}

	final PlayerListEntry func_35569_c(long j1) {
		int i3 = func_35568_g(j1);

		for(PlayerListEntry playerListEntry4 = this.field_35584_a[func_35573_a(i3, this.field_35584_a.length)]; playerListEntry4 != null; playerListEntry4 = playerListEntry4.field_35833_c) {
			if(playerListEntry4.field_35834_a == j1) {
				return playerListEntry4;
			}
		}

		return null;
	}

	public void func_35577_a(long j1, Object object3) {
		int i4 = func_35568_g(j1);
		int i5 = func_35573_a(i4, this.field_35584_a.length);

		for(PlayerListEntry playerListEntry6 = this.field_35584_a[i5]; playerListEntry6 != null; playerListEntry6 = playerListEntry6.field_35833_c) {
			if(playerListEntry6.field_35834_a == j1) {
				playerListEntry6.field_35832_b = object3;
			}
		}

		++this.field_35581_e;
		this.func_35570_a(i4, j1, object3, i5);
	}

	private void func_35567_b(int i1) {
		PlayerListEntry[] playerListEntry2 = this.field_35584_a;
		int i3 = playerListEntry2.length;
		if(i3 == 1073741824) {
			this.field_35583_c = Integer.MAX_VALUE;
		} else {
			PlayerListEntry[] playerListEntry4 = new PlayerListEntry[i1];
			this.func_35579_a(playerListEntry4);
			this.field_35584_a = playerListEntry4;
			this.field_35583_c = (int)((float)i1 * this.field_35580_d);
		}
	}

	private void func_35579_a(PlayerListEntry[] playerListEntry1) {
		PlayerListEntry[] playerListEntry2 = this.field_35584_a;
		int i3 = playerListEntry1.length;

		for(int i4 = 0; i4 < playerListEntry2.length; ++i4) {
			PlayerListEntry playerListEntry5 = playerListEntry2[i4];
			if(playerListEntry5 != null) {
				playerListEntry2[i4] = null;

				PlayerListEntry playerListEntry6;
				do {
					playerListEntry6 = playerListEntry5.field_35833_c;
					int i7 = func_35573_a(playerListEntry5.field_35831_d, i3);
					playerListEntry5.field_35833_c = playerListEntry1[i7];
					playerListEntry1[i7] = playerListEntry5;
					playerListEntry5 = playerListEntry6;
				} while(playerListEntry6 != null);
			}
		}

	}

	public Object func_35574_d(long j1) {
		PlayerListEntry playerListEntry3 = this.func_35572_e(j1);
		return playerListEntry3 == null ? null : playerListEntry3.field_35832_b;
	}

	final PlayerListEntry func_35572_e(long j1) {
		int i3 = func_35568_g(j1);
		int i4 = func_35573_a(i3, this.field_35584_a.length);
		PlayerListEntry playerListEntry5 = this.field_35584_a[i4];

		PlayerListEntry playerListEntry6;
		PlayerListEntry playerListEntry7;
		for(playerListEntry6 = playerListEntry5; playerListEntry6 != null; playerListEntry6 = playerListEntry7) {
			playerListEntry7 = playerListEntry6.field_35833_c;
			if(playerListEntry6.field_35834_a == j1) {
				++this.field_35581_e;
				--this.field_35582_b;
				if(playerListEntry5 == playerListEntry6) {
					this.field_35584_a[i4] = playerListEntry7;
				} else {
					playerListEntry5.field_35833_c = playerListEntry7;
				}

				return playerListEntry6;
			}

			playerListEntry5 = playerListEntry6;
		}

		return playerListEntry6;
	}

	private void func_35570_a(int i1, long j2, Object object4, int i5) {
		PlayerListEntry playerListEntry6 = this.field_35584_a[i5];
		this.field_35584_a[i5] = new PlayerListEntry(i1, j2, object4, playerListEntry6);
		if(this.field_35582_b++ >= this.field_35583_c) {
			this.func_35567_b(2 * this.field_35584_a.length);
		}

	}

	static int func_35566_f(long j0) {
		return func_35568_g(j0);
	}
}
