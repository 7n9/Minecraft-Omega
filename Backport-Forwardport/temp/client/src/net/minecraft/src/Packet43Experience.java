package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet43Experience extends Packet {
	public int field_35230_a;
	public int field_35228_b;
	public int field_35229_c;

	public void readPacketData(DataInputStream dataInputStream1) throws IOException {
		this.field_35230_a = dataInputStream1.readByte();
		this.field_35229_c = dataInputStream1.readByte();
		this.field_35228_b = dataInputStream1.readShort();
	}

	public void writePacketData(DataOutputStream dataOutputStream1) throws IOException {
		dataOutputStream1.writeByte(this.field_35230_a);
		dataOutputStream1.writeByte(this.field_35229_c);
		dataOutputStream1.writeShort(this.field_35228_b);
	}

	public void processPacket(NetHandler netHandler1) {
		netHandler1.func_35777_a(this);
	}

	public int getPacketSize() {
		return 4;
	}
}
