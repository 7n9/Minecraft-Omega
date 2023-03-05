package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet8UpdateHealth extends Packet {
	public int healthMP;
	public int field_35231_b;
	public float field_35232_c;

	public void readPacketData(DataInputStream dataInputStream1) throws IOException {
		this.healthMP = dataInputStream1.readShort();
		this.field_35231_b = dataInputStream1.readShort();
		this.field_35232_c = dataInputStream1.readFloat();
	}

	public void writePacketData(DataOutputStream dataOutputStream1) throws IOException {
		dataOutputStream1.writeShort(this.healthMP);
		dataOutputStream1.writeShort(this.field_35231_b);
		dataOutputStream1.writeFloat(this.field_35232_c);
	}

	public void processPacket(NetHandler netHandler1) {
		netHandler1.handleHealth(this);
	}

	public int getPacketSize() {
		return 8;
	}
}
