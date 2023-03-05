package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet9Respawn extends Packet {
	public long field_35246_a;
	public int field_35244_b;
	public int field_35245_c;
	public int field_35242_d;
	public int field_35243_e;

	public Packet9Respawn() {
	}

	public Packet9Respawn(byte b1, byte b2, long j3, int i5, int i6) {
		this.field_35244_b = b1;
		this.field_35245_c = b2;
		this.field_35246_a = j3;
		this.field_35242_d = i5;
		this.field_35243_e = i6;
	}

	public void processPacket(NetHandler netHandler1) {
		netHandler1.handleRespawn(this);
	}

	public void readPacketData(DataInputStream dataInputStream1) throws IOException {
		this.field_35244_b = dataInputStream1.readByte();
		this.field_35245_c = dataInputStream1.readByte();
		this.field_35243_e = dataInputStream1.readByte();
		this.field_35242_d = dataInputStream1.readShort();
		this.field_35246_a = dataInputStream1.readLong();
	}

	public void writePacketData(DataOutputStream dataOutputStream1) throws IOException {
		dataOutputStream1.writeByte(this.field_35244_b);
		dataOutputStream1.writeByte(this.field_35245_c);
		dataOutputStream1.writeByte(this.field_35243_e);
		dataOutputStream1.writeShort(this.field_35242_d);
		dataOutputStream1.writeLong(this.field_35246_a);
	}

	public int getPacketSize() {
		return 13;
	}
}
