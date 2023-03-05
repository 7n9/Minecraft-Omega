package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet41EntityEffect extends Packet {
	public int field_35261_a;
	public byte field_35259_b;
	public byte field_35260_c;
	public short field_35258_d;

	public void readPacketData(DataInputStream dataInputStream1) throws IOException {
		this.field_35261_a = dataInputStream1.readInt();
		this.field_35259_b = dataInputStream1.readByte();
		this.field_35260_c = dataInputStream1.readByte();
		this.field_35258_d = dataInputStream1.readShort();
	}

	public void writePacketData(DataOutputStream dataOutputStream1) throws IOException {
		dataOutputStream1.writeInt(this.field_35261_a);
		dataOutputStream1.writeByte(this.field_35259_b);
		dataOutputStream1.writeByte(this.field_35260_c);
		dataOutputStream1.writeShort(this.field_35258_d);
	}

	public void processPacket(NetHandler netHandler1) {
		netHandler1.func_35780_a(this);
	}

	public int getPacketSize() {
		return 8;
	}
}
