package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet0KeepAlive extends Packet {
	public int field_35126_a;

	public Packet0KeepAlive() {
	}

	public Packet0KeepAlive(int i1) {
		this.field_35126_a = i1;
	}

	public void processPacket(NetHandler netHandler1) {
		netHandler1.func_35005_a(this);
	}

	public void readPacketData(DataInputStream dataInputStream1) throws IOException {
		this.field_35126_a = dataInputStream1.readInt();
	}

	public void writePacketData(DataOutputStream dataOutputStream1) throws IOException {
		dataOutputStream1.writeInt(this.field_35126_a);
	}

	public int getPacketSize() {
		return 4;
	}
}