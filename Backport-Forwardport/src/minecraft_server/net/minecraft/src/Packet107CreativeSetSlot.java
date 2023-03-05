package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet107CreativeSetSlot extends Packet {
	public int field_35108_a;
	public int field_35106_b;
	public int field_35107_c;
	public int field_35105_d;

	public void processPacket(NetHandler netHandler1) {
		netHandler1.func_35004_a(this);
	}

	public void readPacketData(DataInputStream dataInputStream1) throws IOException {
		this.field_35108_a = dataInputStream1.readShort();
		this.field_35106_b = dataInputStream1.readShort();
		this.field_35107_c = dataInputStream1.readShort();
		this.field_35105_d = dataInputStream1.readShort();
	}

	public void writePacketData(DataOutputStream dataOutputStream1) throws IOException {
		dataOutputStream1.writeShort(this.field_35108_a);
		dataOutputStream1.writeShort(this.field_35106_b);
		dataOutputStream1.writeShort(this.field_35107_c);
		dataOutputStream1.writeShort(this.field_35105_d);
	}

	public int getPacketSize() {
		return 8;
	}
}
