package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet107CreativeSetSlot extends Packet {
	public int field_35236_a;
	public int field_35234_b;
	public int field_35235_c;
	public int field_35233_d;

	public Packet107CreativeSetSlot() {
	}

	public Packet107CreativeSetSlot(int i1, int i2, int i3, int i4) {
		this.field_35236_a = i1;
		this.field_35234_b = i2;
		this.field_35235_c = i3;
		this.field_35233_d = i4;
	}

	public void processPacket(NetHandler netHandler1) {
		netHandler1.func_35781_a(this);
	}

	public void readPacketData(DataInputStream dataInputStream1) throws IOException {
		this.field_35236_a = dataInputStream1.readShort();
		this.field_35234_b = dataInputStream1.readShort();
		this.field_35235_c = dataInputStream1.readShort();
		this.field_35233_d = dataInputStream1.readShort();
	}

	public void writePacketData(DataOutputStream dataOutputStream1) throws IOException {
		dataOutputStream1.writeShort(this.field_35236_a);
		dataOutputStream1.writeShort(this.field_35234_b);
		dataOutputStream1.writeShort(this.field_35235_c);
		dataOutputStream1.writeShort(this.field_35233_d);
	}

	public int getPacketSize() {
		return 8;
	}
}
