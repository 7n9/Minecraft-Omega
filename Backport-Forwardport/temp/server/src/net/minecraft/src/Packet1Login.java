package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet1Login extends Packet {
	public int protocolVersion;
	public String username;
	public long mapSeed;
	public int field_35131_d;
	public byte field_35132_e;
	public byte field_35129_f;
	public byte field_35130_g;
	public byte field_35133_h;

	public Packet1Login() {
	}

	public Packet1Login(String string1, int i2, long j3, int i5, byte b6, byte b7, byte b8, byte b9) {
		this.username = string1;
		this.protocolVersion = i2;
		this.mapSeed = j3;
		this.field_35132_e = b6;
		this.field_35129_f = b7;
		this.field_35131_d = i5;
		this.field_35130_g = b8;
		this.field_35133_h = b9;
	}

	public void readPacketData(DataInputStream dataInputStream1) throws IOException {
		this.protocolVersion = dataInputStream1.readInt();
		this.username = readString(dataInputStream1, 16);
		this.mapSeed = dataInputStream1.readLong();
		this.field_35131_d = dataInputStream1.readInt();
		this.field_35132_e = dataInputStream1.readByte();
		this.field_35129_f = dataInputStream1.readByte();
		this.field_35130_g = dataInputStream1.readByte();
		this.field_35133_h = dataInputStream1.readByte();
	}

	public void writePacketData(DataOutputStream dataOutputStream1) throws IOException {
		dataOutputStream1.writeInt(this.protocolVersion);
		writeString(this.username, dataOutputStream1);
		dataOutputStream1.writeLong(this.mapSeed);
		dataOutputStream1.writeInt(this.field_35131_d);
		dataOutputStream1.writeByte(this.field_35132_e);
		dataOutputStream1.writeByte(this.field_35129_f);
		dataOutputStream1.writeByte(this.field_35130_g);
		dataOutputStream1.writeByte(this.field_35133_h);
	}

	public void processPacket(NetHandler netHandler1) {
		netHandler1.handleLogin(this);
	}

	public int getPacketSize() {
		return 4 + this.username.length() + 4 + 7 + 4;
	}
}
