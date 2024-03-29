package net.minecraft.src;

import java.util.Objects;

class MCHashEntry {
	final int hashEntry;
	Object valueEntry;
	MCHashEntry nextEntry;
	final int slotHash;

	MCHashEntry(int i1, int i2, Object object3, MCHashEntry mCHashEntry4) {
		this.valueEntry = object3;
		this.nextEntry = mCHashEntry4;
		this.hashEntry = i2;
		this.slotHash = i1;
	}

	public final int getHash() {
		return this.hashEntry;
	}

	public final Object getValue() {
		return this.valueEntry;
	}

	public final boolean equals(Object object1) {
		if(!(object1 instanceof MCHashEntry)) {
			return false;
		} else {
			MCHashEntry mCHashEntry2 = (MCHashEntry)object1;
			Integer integer3 = this.getHash();
			Integer integer4 = mCHashEntry2.getHash();
			if(Objects.equals(integer3, integer4)) {
				Object object5 = this.getValue();
				Object object6 = mCHashEntry2.getValue();
                return Objects.equals(object5, object6);
			}

			return false;
		}
	}

	public final int hashCode() {
		return MCHash.getHash(this.hashEntry);
	}

	public final String toString() {
		return this.getHash() + "=" + this.getValue();
	}
}
