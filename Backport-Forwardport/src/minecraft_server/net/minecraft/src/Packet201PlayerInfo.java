package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet201PlayerInfo extends Packet {
	public String field_35111_a;
	public boolean field_35109_b;
	public int field_35110_c;

	public Packet201PlayerInfo() {
	}

	public Packet201PlayerInfo(String string1, boolean z2, int i3) {
		this.field_35111_a = string1;
		this.field_35109_b = z2;
		this.field_35110_c = i3;
	}

	public void readPacketData(DataInputStream dataInputStream1) throws IOException {
		this.field_35111_a = readString(dataInputStream1, 16);
		this.field_35109_b = dataInputStream1.readByte() != 0;
		this.field_35110_c = dataInputStream1.readShort();
	}

	public void writePacketData(DataOutputStream dataOutputStream1) throws IOException {
		writeString(this.field_35111_a, dataOutputStream1);
		dataOutputStream1.writeByte(this.field_35109_b ? 1 : 0);
		dataOutputStream1.writeShort(this.field_35110_c);
	}

	public void processPacket(NetHandler netHandler1) {
		netHandler1.func_35006_a(this);
	}

	public int getPacketSize() {
		return this.field_35111_a.length() + 2 + 1 + 2;
	}
}
