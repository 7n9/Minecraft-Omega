package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet42RemoveEntityEffect extends Packet {
	public int field_35253_a;
	public byte field_35252_b;

	public void readPacketData(DataInputStream dataInputStream1) throws IOException {
		this.field_35253_a = dataInputStream1.readInt();
		this.field_35252_b = dataInputStream1.readByte();
	}

	public void writePacketData(DataOutputStream dataOutputStream1) throws IOException {
		dataOutputStream1.writeInt(this.field_35253_a);
		dataOutputStream1.writeByte(this.field_35252_b);
	}

	public void processPacket(NetHandler netHandler1) {
		netHandler1.func_35783_a(this);
	}

	public int getPacketSize() {
		return 5;
	}
}
