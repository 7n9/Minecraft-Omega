package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet42RemoveEntityEffect extends Packet {
	public int field_35128_a;
	public byte field_35127_b;

	public Packet42RemoveEntityEffect() {
	}

	public Packet42RemoveEntityEffect(int i1, PotionEffect potionEffect2) {
		this.field_35128_a = i1;
		this.field_35127_b = (byte)(potionEffect2.func_35649_a() & 255);
	}

	public void readPacketData(DataInputStream dataInputStream1) throws IOException {
		this.field_35128_a = dataInputStream1.readInt();
		this.field_35127_b = dataInputStream1.readByte();
	}

	public void writePacketData(DataOutputStream dataOutputStream1) throws IOException {
		dataOutputStream1.writeInt(this.field_35128_a);
		dataOutputStream1.writeByte(this.field_35127_b);
	}

	public void processPacket(NetHandler netHandler1) {
		netHandler1.func_35003_a(this);
	}

	public int getPacketSize() {
		return 5;
	}
}
