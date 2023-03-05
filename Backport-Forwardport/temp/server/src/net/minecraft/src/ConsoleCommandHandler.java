package net.minecraft.src;

import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

import net.minecraft.server.MinecraftServer;

public class ConsoleCommandHandler {
	private static Logger minecraftLogger = Logger.getLogger("Minecraft");
	private MinecraftServer minecraftServer;

	public ConsoleCommandHandler(MinecraftServer minecraftServer1) {
		this.minecraftServer = minecraftServer1;
	}

	public void handleCommand(ServerCommand serverCommand1) {
		String string2 = serverCommand1.command;
		ICommandListener iCommandListener3 = serverCommand1.commandListener;
		String string4 = iCommandListener3.getUsername();
		ServerConfigurationManager serverConfigurationManager5 = this.minecraftServer.configManager;
		if(!string2.toLowerCase().startsWith("help") && !string2.toLowerCase().startsWith("?")) {
			if(string2.toLowerCase().startsWith("list")) {
				iCommandListener3.log("Connected players: " + serverConfigurationManager5.getPlayerList());
			} else if(string2.toLowerCase().startsWith("stop")) {
				this.sendNoticeToOps(string4, "Stopping the server..");
				this.minecraftServer.initiateShutdown();
			} else {
				int i6;
				WorldServer worldServer7;
				if(string2.toLowerCase().startsWith("save-all")) {
					this.sendNoticeToOps(string4, "Forcing save..");
					if(serverConfigurationManager5 != null) {
						serverConfigurationManager5.savePlayerStates();
					}

					for(i6 = 0; i6 < this.minecraftServer.worldMngr.length; ++i6) {
						worldServer7 = this.minecraftServer.worldMngr[i6];
						worldServer7.saveWorld(true, (IProgressUpdate)null);
					}

					this.sendNoticeToOps(string4, "Save complete.");
				} else if(string2.toLowerCase().startsWith("save-off")) {
					this.sendNoticeToOps(string4, "Disabling level saving..");

					for(i6 = 0; i6 < this.minecraftServer.worldMngr.length; ++i6) {
						worldServer7 = this.minecraftServer.worldMngr[i6];
						worldServer7.levelSaving = true;
					}
				} else if(string2.toLowerCase().startsWith("save-on")) {
					this.sendNoticeToOps(string4, "Enabling level saving..");

					for(i6 = 0; i6 < this.minecraftServer.worldMngr.length; ++i6) {
						worldServer7 = this.minecraftServer.worldMngr[i6];
						worldServer7.levelSaving = false;
					}
				} else {
					String string14;
					if(string2.toLowerCase().startsWith("op ")) {
						string14 = string2.substring(string2.indexOf(" ")).trim();
						serverConfigurationManager5.func_35479_e(string14);
						this.sendNoticeToOps(string4, "Opping " + string14);
						serverConfigurationManager5.sendChatMessageToPlayer(string14, "\u00a7eYou are now op!");
					} else if(string2.toLowerCase().startsWith("deop ")) {
						string14 = string2.substring(string2.indexOf(" ")).trim();
						serverConfigurationManager5.func_35478_f(string14);
						serverConfigurationManager5.sendChatMessageToPlayer(string14, "\u00a7eYou are no longer op!");
						this.sendNoticeToOps(string4, "De-opping " + string14);
					} else if(string2.toLowerCase().startsWith("ban-ip ")) {
						string14 = string2.substring(string2.indexOf(" ")).trim();
						serverConfigurationManager5.banIP(string14);
						this.sendNoticeToOps(string4, "Banning ip " + string14);
					} else if(string2.toLowerCase().startsWith("pardon-ip ")) {
						string14 = string2.substring(string2.indexOf(" ")).trim();
						serverConfigurationManager5.pardonIP(string14);
						this.sendNoticeToOps(string4, "Pardoning ip " + string14);
					} else {
						EntityPlayerMP entityPlayerMP15;
						if(string2.toLowerCase().startsWith("ban ")) {
							string14 = string2.substring(string2.indexOf(" ")).trim();
							serverConfigurationManager5.banPlayer(string14);
							this.sendNoticeToOps(string4, "Banning " + string14);
							entityPlayerMP15 = serverConfigurationManager5.getPlayerEntity(string14);
							if(entityPlayerMP15 != null) {
								entityPlayerMP15.playerNetServerHandler.kickPlayer("Banned by admin");
							}
						} else if(string2.toLowerCase().startsWith("pardon ")) {
							string14 = string2.substring(string2.indexOf(" ")).trim();
							serverConfigurationManager5.pardonPlayer(string14);
							this.sendNoticeToOps(string4, "Pardoning " + string14);
						} else {
							int i8;
							if(string2.toLowerCase().startsWith("kick ")) {
								string14 = string2.substring(string2.indexOf(" ")).trim();
								entityPlayerMP15 = null;

								for(i8 = 0; i8 < serverConfigurationManager5.playerEntities.size(); ++i8) {
									EntityPlayerMP entityPlayerMP9 = (EntityPlayerMP)serverConfigurationManager5.playerEntities.get(i8);
									if(entityPlayerMP9.username.equalsIgnoreCase(string14)) {
										entityPlayerMP15 = entityPlayerMP9;
									}
								}

								if(entityPlayerMP15 != null) {
									entityPlayerMP15.playerNetServerHandler.kickPlayer("Kicked by admin");
									this.sendNoticeToOps(string4, "Kicking " + entityPlayerMP15.username);
								} else {
									iCommandListener3.log("Can\'t find user " + string14 + ". No kick.");
								}
							} else {
								EntityPlayerMP entityPlayerMP16;
								String[] string19;
								if(string2.toLowerCase().startsWith("tp ")) {
									string19 = string2.split(" ");
									if(string19.length == 3) {
										entityPlayerMP15 = serverConfigurationManager5.getPlayerEntity(string19[1]);
										entityPlayerMP16 = serverConfigurationManager5.getPlayerEntity(string19[2]);
										if(entityPlayerMP15 == null) {
											iCommandListener3.log("Can\'t find user " + string19[1] + ". No tp.");
										} else if(entityPlayerMP16 == null) {
											iCommandListener3.log("Can\'t find user " + string19[2] + ". No tp.");
										} else if(entityPlayerMP15.dimension != entityPlayerMP16.dimension) {
											iCommandListener3.log("User " + string19[1] + " and " + string19[2] + " are in different dimensions. No tp.");
										} else {
											entityPlayerMP15.playerNetServerHandler.teleportTo(entityPlayerMP16.posX, entityPlayerMP16.posY, entityPlayerMP16.posZ, entityPlayerMP16.rotationYaw, entityPlayerMP16.rotationPitch);
											this.sendNoticeToOps(string4, "Teleporting " + string19[1] + " to " + string19[2] + ".");
										}
									} else {
										iCommandListener3.log("Syntax error, please provice a source and a target.");
									}
								} else {
									String string17;
									int i18;
									if(string2.toLowerCase().startsWith("give ")) {
										string19 = string2.split(" ");
										if(string19.length != 3 && string19.length != 4) {
											return;
										}

										string17 = string19[1];
										entityPlayerMP16 = serverConfigurationManager5.getPlayerEntity(string17);
										if(entityPlayerMP16 != null) {
											try {
												i18 = Integer.parseInt(string19[2]);
												if(Item.itemsList[i18] != null) {
													this.sendNoticeToOps(string4, "Giving " + entityPlayerMP16.username + " some " + i18);
													int i10 = 1;
													if(string19.length > 3) {
														i10 = this.tryParse(string19[3], 1);
													}

													if(i10 < 1) {
														i10 = 1;
													}

													if(i10 > 64) {
														i10 = 64;
													}

													entityPlayerMP16.dropPlayerItem(new ItemStack(i18, i10, 0));
												} else {
													iCommandListener3.log("There\'s no item with id " + i18);
												}
											} catch (NumberFormatException numberFormatException12) {
												iCommandListener3.log("There\'s no item with id " + string19[2]);
											}
										} else {
											iCommandListener3.log("Can\'t find user " + string17);
										}
									} else if(string2.toLowerCase().startsWith("gamemode ")) {
										string19 = string2.split(" ");
										if(string19.length != 3) {
											return;
										}

										string17 = string19[1];
										entityPlayerMP16 = serverConfigurationManager5.getPlayerEntity(string17);
										if(entityPlayerMP16 != null) {
											try {
												i18 = Integer.parseInt(string19[2]);
												i18 = WorldSettings.func_35037_a(i18);
												if(entityPlayerMP16.itemInWorldManager.func_35697_a() != i18) {
													this.sendNoticeToOps(string4, "Setting " + entityPlayerMP16.username + " to game mode " + i18);
													entityPlayerMP16.itemInWorldManager.func_35696_a(i18);
													entityPlayerMP16.playerNetServerHandler.sendPacket(new Packet70Bed(3, i18));
												} else {
													this.sendNoticeToOps(string4, entityPlayerMP16.username + " already has game mode " + i18);
												}
											} catch (NumberFormatException numberFormatException11) {
												iCommandListener3.log("There\'s no game mode with id " + string19[2]);
											}
										} else {
											iCommandListener3.log("Can\'t find user " + string17);
										}
									} else if(string2.toLowerCase().startsWith("time ")) {
										string19 = string2.split(" ");
										if(string19.length != 3) {
											return;
										}

										string17 = string19[1];

										try {
											i8 = Integer.parseInt(string19[2]);
											WorldServer worldServer20;
											if("add".equalsIgnoreCase(string17)) {
												for(i18 = 0; i18 < this.minecraftServer.worldMngr.length; ++i18) {
													worldServer20 = this.minecraftServer.worldMngr[i18];
													worldServer20.func_32005_b(worldServer20.getWorldTime() + (long)i8);
												}

												this.sendNoticeToOps(string4, "Added " + i8 + " to time");
											} else if("set".equalsIgnoreCase(string17)) {
												for(i18 = 0; i18 < this.minecraftServer.worldMngr.length; ++i18) {
													worldServer20 = this.minecraftServer.worldMngr[i18];
													worldServer20.func_32005_b((long)i8);
												}

												this.sendNoticeToOps(string4, "Set time to " + i8);
											} else {
												iCommandListener3.log("Unknown method, use either \"add\" or \"set\"");
											}
										} catch (NumberFormatException numberFormatException13) {
											iCommandListener3.log("Unable to convert time value, " + string19[2]);
										}
									} else if(string2.toLowerCase().startsWith("say ")) {
										string2 = string2.substring(string2.indexOf(" ")).trim();
										minecraftLogger.info("[" + string4 + "] " + string2);
										serverConfigurationManager5.sendPacketToAllPlayers(new Packet3Chat("\u00a7d[Server] " + string2));
									} else if(string2.toLowerCase().startsWith("tell ")) {
										string19 = string2.split(" ");
										if(string19.length >= 3) {
											string2 = string2.substring(string2.indexOf(" ")).trim();
											string2 = string2.substring(string2.indexOf(" ")).trim();
											minecraftLogger.info("[" + string4 + "->" + string19[1] + "] " + string2);
											string2 = "\u00a77" + string4 + " whispers " + string2;
											minecraftLogger.info(string2);
											if(!serverConfigurationManager5.sendPacketToPlayer(string19[1], new Packet3Chat(string2))) {
												iCommandListener3.log("There\'s no player by that name online.");
											}
										}
									} else if(string2.toLowerCase().startsWith("whitelist ")) {
										this.handleWhitelist(string4, string2, iCommandListener3);
									} else {
										minecraftLogger.info("Unknown console command. Type \"help\" for help.");
									}
								}
							}
						}
					}
				}
			}
		} else {
			this.printHelp(iCommandListener3);
		}

	}

	private void handleWhitelist(String string1, String string2, ICommandListener iCommandListener3) {
		String[] string4 = string2.split(" ");
		if(string4.length >= 2) {
			String string5 = string4[1].toLowerCase();
			if("on".equals(string5)) {
				this.sendNoticeToOps(string1, "Turned on white-listing");
				this.minecraftServer.propertyManagerObj.setProperty("white-list", true);
			} else if("off".equals(string5)) {
				this.sendNoticeToOps(string1, "Turned off white-listing");
				this.minecraftServer.propertyManagerObj.setProperty("white-list", false);
			} else if("list".equals(string5)) {
				Set set6 = this.minecraftServer.configManager.getWhiteListedIPs();
				String string7 = "";

				String string9;
				for(Iterator iterator8 = set6.iterator(); iterator8.hasNext(); string7 = string7 + string9 + " ") {
					string9 = (String)iterator8.next();
				}

				iCommandListener3.log("White-listed players: " + string7);
			} else {
				String string10;
				if("add".equals(string5) && string4.length == 3) {
					string10 = string4[2].toLowerCase();
					this.minecraftServer.configManager.addToWhiteList(string10);
					this.sendNoticeToOps(string1, "Added " + string10 + " to white-list");
				} else if("remove".equals(string5) && string4.length == 3) {
					string10 = string4[2].toLowerCase();
					this.minecraftServer.configManager.removeFromWhiteList(string10);
					this.sendNoticeToOps(string1, "Removed " + string10 + " from white-list");
				} else if("reload".equals(string5)) {
					this.minecraftServer.configManager.reloadWhiteList();
					this.sendNoticeToOps(string1, "Reloaded white-list from file");
				}
			}

		}
	}

	private void printHelp(ICommandListener iCommandListener1) {
		iCommandListener1.log("To run the server without a gui, start it like this:");
		iCommandListener1.log("   java -Xmx1024M -Xms1024M -jar minecraft_server.jar nogui");
		iCommandListener1.log("Console commands:");
		iCommandListener1.log("   help  or  ?               shows this message");
		iCommandListener1.log("   kick <player>             removes a player from the server");
		iCommandListener1.log("   ban <player>              bans a player from the server");
		iCommandListener1.log("   pardon <player>           pardons a banned player so that they can connect again");
		iCommandListener1.log("   ban-ip <ip>               bans an IP address from the server");
		iCommandListener1.log("   pardon-ip <ip>            pardons a banned IP address so that they can connect again");
		iCommandListener1.log("   op <player>               turns a player into an op");
		iCommandListener1.log("   deop <player>             removes op status from a player");
		iCommandListener1.log("   tp <player1> <player2>    moves one player to the same location as another player");
		iCommandListener1.log("   give <player> <id> [num]  gives a player a resource");
		iCommandListener1.log("   tell <player> <message>   sends a private message to a player");
		iCommandListener1.log("   stop                      gracefully stops the server");
		iCommandListener1.log("   save-all                  forces a server-wide level save");
		iCommandListener1.log("   save-off                  disables terrain saving (useful for backup scripts)");
		iCommandListener1.log("   save-on                   re-enables terrain saving");
		iCommandListener1.log("   list                      lists all currently connected players");
		iCommandListener1.log("   say <message>             broadcasts a message to all players");
		iCommandListener1.log("   time <add|set> <amount>   adds to or sets the world time (0-24000)");
		iCommandListener1.log("   gamemode <player> <mode>  sets player\'s game mode (0 or 1)");
	}

	private void sendNoticeToOps(String string1, String string2) {
		String string3 = string1 + ": " + string2;
		this.minecraftServer.configManager.sendChatMessageToAllOps("\u00a77(" + string3 + ")");
		minecraftLogger.info(string3);
	}

	private int tryParse(String string1, int i2) {
		try {
			return Integer.parseInt(string1);
		} catch (NumberFormatException numberFormatException4) {
			return i2;
		}
	}
}
