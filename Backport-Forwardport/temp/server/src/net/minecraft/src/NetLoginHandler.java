package net.minecraft.src;

import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Logger;

import net.minecraft.server.MinecraftServer;

public class NetLoginHandler extends NetHandler {
	public static Logger logger = Logger.getLogger("Minecraft");
	private static Random rand = new Random();
	public NetworkManager netManager;
	public boolean finishedProcessing = false;
	private MinecraftServer mcServer;
	private int loginTimer = 0;
	private String username = null;
	private Packet1Login packet1login = null;
	private String serverId = "";

	public NetLoginHandler(MinecraftServer minecraftServer1, Socket socket2, String string3) throws IOException {
		this.mcServer = minecraftServer1;
		this.netManager = new NetworkManager(socket2, string3, this);
		this.netManager.chunkDataSendCounter = 0;
	}

	public void tryLogin() {
		if(this.packet1login != null) {
			this.doLogin(this.packet1login);
			this.packet1login = null;
		}

		if(this.loginTimer++ == 600) {
			this.kickUser("Took too long to log in");
		} else {
			this.netManager.processReadPackets();
		}

	}

	public void kickUser(String string1) {
		try {
			logger.info("Disconnecting " + this.getUserAndIPString() + ": " + string1);
			this.netManager.addToSendQueue(new Packet255KickDisconnect(string1));
			this.netManager.serverShutdown();
			this.finishedProcessing = true;
		} catch (Exception exception3) {
			exception3.printStackTrace();
		}

	}

	public void handleHandshake(Packet2Handshake packet2Handshake1) {
		if(this.mcServer.onlineMode) {
			this.serverId = Long.toHexString(rand.nextLong());
			this.netManager.addToSendQueue(new Packet2Handshake(this.serverId));
		} else {
			this.netManager.addToSendQueue(new Packet2Handshake("-"));
		}

	}

	public void handleLogin(Packet1Login packet1Login1) {
		this.username = packet1Login1.username;
		if(packet1Login1.protocolVersion != 17) {
			if(packet1Login1.protocolVersion > 17) {
				this.kickUser("Outdated server!");
			} else {
				this.kickUser("Outdated client!");
			}

		} else {
			if(!this.mcServer.onlineMode) {
				this.doLogin(packet1Login1);
			} else {
				(new ThreadLoginVerifier(this, packet1Login1)).start();
			}

		}
	}

	public void doLogin(Packet1Login packet1Login1) {
		EntityPlayerMP entityPlayerMP2 = this.mcServer.configManager.login(this, packet1Login1.username);
		if(entityPlayerMP2 != null) {
			this.mcServer.configManager.readPlayerDataFromFile(entityPlayerMP2);
			entityPlayerMP2.Sets(this.mcServer.getWorldManager(entityPlayerMP2.dimension));
			entityPlayerMP2.itemInWorldManager.func_35694_a((WorldServer)entityPlayerMP2.worldObj);
			logger.info(this.getUserAndIPString() + " logged in with entity id " + entityPlayerMP2.entityId + " at (" + entityPlayerMP2.posX + ", " + entityPlayerMP2.posY + ", " + entityPlayerMP2.posZ + ")");
			WorldServer worldServer3 = this.mcServer.getWorldManager(entityPlayerMP2.dimension);
			ChunkCoordinates chunkCoordinates4 = worldServer3.getSpawnPoint();
			entityPlayerMP2.itemInWorldManager.func_35695_b(worldServer3.getWorldInfo().func_35501_n());
			NetServerHandler netServerHandler5 = new NetServerHandler(this.mcServer, this.netManager, entityPlayerMP2);
			int i10004 = entityPlayerMP2.entityId;
			long j10005 = worldServer3.getRandomSeed();
			int i10006 = entityPlayerMP2.itemInWorldManager.func_35697_a();
			byte b10007 = (byte)worldServer3.worldProvider.worldType;
			byte b10008 = (byte)worldServer3.difficultySetting;
			worldServer3.getClass();
			netServerHandler5.sendPacket(new Packet1Login("", i10004, j10005, i10006, b10007, b10008, (byte)-128, (byte)this.mcServer.configManager.func_35480_h()));
			netServerHandler5.sendPacket(new Packet6SpawnPosition(chunkCoordinates4.posX, chunkCoordinates4.posY, chunkCoordinates4.posZ));
			this.mcServer.configManager.func_28170_a(entityPlayerMP2, worldServer3);
			this.mcServer.configManager.sendPacketToAllPlayers(new Packet3Chat("\u00a7e" + entityPlayerMP2.username + " joined the game."));
			this.mcServer.configManager.playerLoggedIn(entityPlayerMP2);
			netServerHandler5.teleportTo(entityPlayerMP2.posX, entityPlayerMP2.posY, entityPlayerMP2.posZ, entityPlayerMP2.rotationYaw, entityPlayerMP2.rotationPitch);
			this.mcServer.networkServer.addPlayer(netServerHandler5);
			netServerHandler5.sendPacket(new Packet4UpdateTime(worldServer3.getWorldTime()));
			Iterator iterator6 = entityPlayerMP2.func_35183_ak().iterator();

			while(iterator6.hasNext()) {
				PotionEffect potionEffect7 = (PotionEffect)iterator6.next();
				netServerHandler5.sendPacket(new Packet41EntityEffect(entityPlayerMP2.entityId, potionEffect7));
			}

			entityPlayerMP2.func_20057_k();
		}

		this.finishedProcessing = true;
	}

	public void handleErrorMessage(String string1, Object[] object2) {
		logger.info(this.getUserAndIPString() + " lost connection");
		this.finishedProcessing = true;
	}

	public void func_35007_a(Packet254ServerPing packet254ServerPing1) {
		try {
			String string2 = this.mcServer.field_35014_p + "\u00a7" + this.mcServer.configManager.func_35481_g() + "\u00a7" + this.mcServer.configManager.func_35480_h();
			this.netManager.addToSendQueue(new Packet255KickDisconnect(string2));
			this.netManager.serverShutdown();
			this.mcServer.networkServer.func_35505_a(this.netManager.func_35596_f());
			this.finishedProcessing = true;
		} catch (Exception exception3) {
			exception3.printStackTrace();
		}

	}

	public void registerPacket(Packet packet1) {
		this.kickUser("Protocol error");
	}

	public String getUserAndIPString() {
		return this.username != null ? this.username + " [" + this.netManager.getRemoteAddress().toString() + "]" : this.netManager.getRemoteAddress().toString();
	}

	public boolean isServerHandler() {
		return true;
	}

	static String getServerId(NetLoginHandler netLoginHandler0) {
		return netLoginHandler0.serverId;
	}

	static Packet1Login setLoginPacket(NetLoginHandler netLoginHandler0, Packet1Login packet1Login1) {
		return netLoginHandler0.packet1login = packet1Login1;
	}
}