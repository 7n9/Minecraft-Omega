package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet9Respawn extends Packet {
	public long field_35120_a;
	public int respawnDimension;
	public int field_35119_c;
	public int field_35117_d;
	public int field_35118_e;

	public Packet9Respawn() {
	}

	public Packet9Respawn(byte b1, byte b2, long j3, int i5, int i6) {
		this.respawnDimension = b1;
		this.field_35119_c = b2;
		this.field_35120_a = j3;
		this.field_35117_d = i5;
		this.field_35118_e = i6;
	}

	public void processPacket(NetHandler netHandler1) {
		netHandler1.handleRespawn(this);
	}

	public void readPacketData(DataInputStream dataInputStream1) throws IOException {
		this.respawnDimension = dataInputStream1.readByte();
		this.field_35119_c = dataInputStream1.readByte();
		this.field_35118_e = dataInputStream1.readByte();
		this.field_35117_d = dataInputStream1.readShort();
		this.field_35120_a = dataInputStream1.readLong();
	}

	public void writePacketData(DataOutputStream dataOutputStream1) throws IOException {
		dataOutputStream1.writeByte(this.respawnDimension);
		dataOutputStream1.writeByte(this.field_35119_c);
		dataOutputStream1.writeByte(this.field_35118_e);
		dataOutputStream1.writeShort(this.field_35117_d);
		dataOutputStream1.writeLong(this.field_35120_a);
	}

	public int getPacketSize() {
		return 13;
	}
}
