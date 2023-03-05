package net.minecraft.src;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import net.minecraft.server.MinecraftServer;

class NetworkAcceptThread extends Thread {
	final MinecraftServer mcServer;
	final NetworkListenThread field_985_b;

	NetworkAcceptThread(NetworkListenThread networkListenThread1, String string2, MinecraftServer minecraftServer3) {
		super(string2);
		this.field_985_b = networkListenThread1;
		this.mcServer = minecraftServer3;
	}

	public void run() {
		while(this.field_985_b.field_973_b) {
			try {
				Socket socket1 = NetworkListenThread.func_713_a(this.field_985_b).accept();
				if(socket1 != null) {
					synchronized(NetworkListenThread.func_35504_b(this.field_985_b)) {
						InetAddress inetAddress3 = socket1.getInetAddress();
						if(NetworkListenThread.func_35504_b(this.field_985_b).containsKey(inetAddress3) && System.currentTimeMillis() - ((Long)NetworkListenThread.func_35504_b(this.field_985_b).get(inetAddress3)).longValue() < 5000L) {
							NetworkListenThread.func_35504_b(this.field_985_b).put(inetAddress3, System.currentTimeMillis());
							socket1.close();
							continue;
						}

						NetworkListenThread.func_35504_b(this.field_985_b).put(inetAddress3, System.currentTimeMillis());
					}

					NetLoginHandler netLoginHandler2 = new NetLoginHandler(this.mcServer, socket1, "Connection #" + NetworkListenThread.func_712_b(this.field_985_b));
					NetworkListenThread.func_716_a(this.field_985_b, netLoginHandler2);
				}
			} catch (IOException iOException6) {
				iOException6.printStackTrace();
			}
		}

	}
}
