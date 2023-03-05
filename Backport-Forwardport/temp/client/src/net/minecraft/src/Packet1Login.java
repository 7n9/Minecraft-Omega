package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet1Login extends Packet {
	public int protocolVersion;
	public String username;
	public long mapSeed;
	public int field_35249_d;
	public byte field_35250_e;
	public byte field_35247_f;
	public byte field_35248_g;
	public byte field_35251_h;

	public Packet1Login() {
	}

	public Packet1Login(String string1, int i2) {
		this.username = string1;
		this.protocolVersion = i2;
	}

	public void readPacketData(DataInputStream dataInputStream1) throws IOException {
		this.protocolVersion = dataInputStream1.readInt();
		this.username = readString(dataInputStream1, 16);
		this.mapSeed = dataInputStream1.readLong();
		this.field_35249_d = dataInputStream1.readInt();
		this.field_35250_e = dataInputStream1.readByte();
		this.field_35247_f = dataInputStream1.readByte();
		this.field_35248_g = dataInputStream1.readByte();
		this.field_35251_h = dataInputStream1.readByte();
	}

	public void writePacketData(DataOutputStream dataOutputStream1) throws IOException {
		dataOutputStream1.writeInt(this.protocolVersion);
		writeString(this.username, dataOutputStream1);
		dataOutputStream1.writeLong(this.mapSeed);
		dataOutputStream1.writeInt(this.field_35249_d);
		dataOutputStream1.writeByte(this.field_35250_e);
		dataOutputStream1.writeByte(this.field_35247_f);
		dataOutputStream1.writeByte(this.field_35248_g);
		dataOutputStream1.writeByte(this.field_35251_h);
	}

	public void processPacket(NetHandler netHandler1) {
		netHandler1.handleLogin(this);
	}

	public int getPacketSize() {
		return 4 + this.username.length() + 4 + 7 + 4;
	}
}
