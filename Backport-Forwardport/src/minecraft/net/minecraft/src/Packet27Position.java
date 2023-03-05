package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet27Position extends Packet {
	private float strafeMovement;
	private float fowardMovement;
	private boolean field_22043_c;
	private boolean isInJump;
	private float pitchRotation;
	private float yawRotation;

	public void readPacketData(DataInputStream dataInputStream1) throws IOException {
		this.strafeMovement = dataInputStream1.readFloat();
		this.fowardMovement = dataInputStream1.readFloat();
		this.pitchRotation = dataInputStream1.readFloat();
		this.yawRotation = dataInputStream1.readFloat();
		this.field_22043_c = dataInputStream1.readBoolean();
		this.isInJump = dataInputStream1.readBoolean();
	}

	public void writePacketData(DataOutputStream dataOutputStream1) throws IOException {
		dataOutputStream1.writeFloat(this.strafeMovement);
		dataOutputStream1.writeFloat(this.fowardMovement);
		dataOutputStream1.writeFloat(this.pitchRotation);
		dataOutputStream1.writeFloat(this.yawRotation);
		dataOutputStream1.writeBoolean(this.field_22043_c);
		dataOutputStream1.writeBoolean(this.isInJump);
	}

	public void processPacket(NetHandler netHandler1) {
		netHandler1.handlePosition(this);
	}

	public int getPacketSize() {
		return 18;
	}
}
