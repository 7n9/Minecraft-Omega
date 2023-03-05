package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet200Statistic extends Packet {
	public int statisticId;
	public int amount;

	public void processPacket(NetHandler netHandler1) {
		netHandler1.handleStatistic(this);
	}

	public void readPacketData(DataInputStream dataInputStream1) throws IOException {
		this.statisticId = dataInputStream1.readInt();
		this.amount = dataInputStream1.readByte();
	}

	public void writePacketData(DataOutputStream dataOutputStream1) throws IOException {
		dataOutputStream1.writeInt(this.statisticId);
		dataOutputStream1.writeByte(this.amount);
	}

	public int getPacketSize() {
		return 6;
	}
}
