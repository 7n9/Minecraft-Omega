package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet26EntityExpOrb extends Packet {
	public int field_35125_a;
	public int field_35123_b;
	public int field_35124_c;
	public int field_35121_d;
	public int field_35122_e;

	public Packet26EntityExpOrb() {
	}

	public Packet26EntityExpOrb(EntityXPOrb entityXPOrb1) {
		this.field_35125_a = entityXPOrb1.entityId;
		this.field_35123_b = MathHelper.floor_double(entityXPOrb1.posX * 32.0D);
		this.field_35124_c = MathHelper.floor_double(entityXPOrb1.posY * 32.0D);
		this.field_35121_d = MathHelper.floor_double(entityXPOrb1.posZ * 32.0D);
		this.field_35122_e = entityXPOrb1.func_35153_j_();
	}

	public void readPacketData(DataInputStream dataInputStream1) throws IOException {
		this.field_35125_a = dataInputStream1.readInt();
		this.field_35123_b = dataInputStream1.readInt();
		this.field_35124_c = dataInputStream1.readInt();
		this.field_35121_d = dataInputStream1.readInt();
		this.field_35122_e = dataInputStream1.readShort();
	}

	public void writePacketData(DataOutputStream dataOutputStream1) throws IOException {
		dataOutputStream1.writeInt(this.field_35125_a);
		dataOutputStream1.writeInt(this.field_35123_b);
		dataOutputStream1.writeInt(this.field_35124_c);
		dataOutputStream1.writeInt(this.field_35121_d);
		dataOutputStream1.writeShort(this.field_35122_e);
	}

	public void processPacket(NetHandler netHandler1) {
		netHandler1.func_35008_a(this);
	}

	public int getPacketSize() {
		return 18;
	}
}
