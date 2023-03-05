package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet43Experience extends Packet {
	public int field_35136_a;
	public int field_35134_b;
	public int field_35135_c;

	public Packet43Experience() {
	}

	public Packet43Experience(int i1, int i2, int i3) {
		this.field_35136_a = i1;
		this.field_35134_b = i2;
		this.field_35135_c = i3;
	}

	public void readPacketData(DataInputStream dataInputStream1) throws IOException {
		this.field_35136_a = dataInputStream1.readByte();
		this.field_35135_c = dataInputStream1.readByte();
		this.field_35134_b = dataInputStream1.readShort();
	}

	public void writePacketData(DataOutputStream dataOutputStream1) throws IOException {
		dataOutputStream1.writeByte(this.field_35136_a);
		dataOutputStream1.writeByte(this.field_35135_c);
		dataOutputStream1.writeShort(this.field_35134_b);
	}

	public void processPacket(NetHandler netHandler1) {
		netHandler1.func_35001_a(this);
	}

	public int getPacketSize() {
		return 4;
	}
}
