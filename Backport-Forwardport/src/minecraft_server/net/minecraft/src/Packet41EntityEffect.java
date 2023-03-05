package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet41EntityEffect extends Packet {
	public int field_35116_a;
	public byte field_35114_b;
	public byte field_35115_c;
	public short field_35113_d;

	public Packet41EntityEffect() {
	}

	public Packet41EntityEffect(int i1, PotionEffect potionEffect2) {
		this.field_35116_a = i1;
		this.field_35114_b = (byte)(potionEffect2.func_35649_a() & 255);
		this.field_35115_c = (byte)(potionEffect2.func_35652_c() & 255);
		this.field_35113_d = (short)potionEffect2.func_35653_b();
	}

	public void readPacketData(DataInputStream dataInputStream1) throws IOException {
		this.field_35116_a = dataInputStream1.readInt();
		this.field_35114_b = dataInputStream1.readByte();
		this.field_35115_c = dataInputStream1.readByte();
		this.field_35113_d = dataInputStream1.readShort();
	}

	public void writePacketData(DataOutputStream dataOutputStream1) throws IOException {
		dataOutputStream1.writeInt(this.field_35116_a);
		dataOutputStream1.writeByte(this.field_35114_b);
		dataOutputStream1.writeByte(this.field_35115_c);
		dataOutputStream1.writeShort(this.field_35113_d);
	}

	public void processPacket(NetHandler netHandler1) {
		netHandler1.func_35002_a(this);
	}

	public int getPacketSize() {
		return 8;
	}
}
