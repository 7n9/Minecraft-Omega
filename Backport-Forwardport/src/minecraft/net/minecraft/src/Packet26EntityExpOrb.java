package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet26EntityExpOrb extends Packet {
	public int field_35241_a;
	public int field_35239_b;
	public int field_35240_c;
	public int field_35237_d;
	public int field_35238_e;

	public Packet26EntityExpOrb() {
	}

	public Packet26EntityExpOrb(EntityXPOrb entityXPOrb1) {
		this.field_35241_a = entityXPOrb1.entityId;
		this.field_35239_b = MathHelper.floor_double(entityXPOrb1.posX * 32.0D);
		this.field_35240_c = MathHelper.floor_double(entityXPOrb1.posY * 32.0D);
		this.field_35237_d = MathHelper.floor_double(entityXPOrb1.posZ * 32.0D);
		this.field_35238_e = entityXPOrb1.func_35119_j_();
	}

	public void readPacketData(DataInputStream dataInputStream1) throws IOException {
		this.field_35241_a = dataInputStream1.readInt();
		this.field_35239_b = dataInputStream1.readInt();
		this.field_35240_c = dataInputStream1.readInt();
		this.field_35237_d = dataInputStream1.readInt();
		this.field_35238_e = dataInputStream1.readShort();
	}

	public void writePacketData(DataOutputStream dataOutputStream1) throws IOException {
		dataOutputStream1.writeInt(this.field_35241_a);
		dataOutputStream1.writeInt(this.field_35239_b);
		dataOutputStream1.writeInt(this.field_35240_c);
		dataOutputStream1.writeInt(this.field_35237_d);
		dataOutputStream1.writeShort(this.field_35238_e);
	}

	public void processPacket(NetHandler netHandler1) {
		netHandler1.func_35778_a(this);
	}

	public int getPacketSize() {
		return 18;
	}
}
