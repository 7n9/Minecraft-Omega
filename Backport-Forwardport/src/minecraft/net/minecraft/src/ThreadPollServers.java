package net.minecraft.src;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

class ThreadPollServers extends Thread {
	final ServerNBTStorage field_35601_a;
	final GuiSlotServer field_35600_b;

	ThreadPollServers(GuiSlotServer guiSlotServer1, ServerNBTStorage serverNBTStorage2) {
		this.field_35600_b = guiSlotServer1;
		this.field_35601_a = serverNBTStorage2;
	}

	public void run() {
		boolean z27 = false;

		label183: {
			label184: {
				label185: {
					label186: {
						label187: {
							try {
								z27 = true;
								this.field_35601_a.field_35791_d = "\u00a78Polling..";
								long j1 = System.nanoTime();
								GuiMultiplayer.func_35336_a(this.field_35600_b.field_35410_a, this.field_35601_a);
								long j3 = System.nanoTime();
								this.field_35601_a.field_35792_e = (j3 - j1) / 1000000L;
								z27 = false;
								break label183;
							} catch (UnknownHostException unknownHostException35) {
								this.field_35601_a.field_35792_e = -1L;
								this.field_35601_a.field_35791_d = "\u00a74Can\'t resolve hostname";
								z27 = false;
								break label184;
							} catch (SocketTimeoutException socketTimeoutException36) {
								this.field_35601_a.field_35792_e = -1L;
								this.field_35601_a.field_35791_d = "\u00a74Can\'t reach server";
								z27 = false;
							} catch (ConnectException connectException37) {
								this.field_35601_a.field_35792_e = -1L;
								this.field_35601_a.field_35791_d = "\u00a74Can\'t reach server";
								z27 = false;
								break label187;
							} catch (IOException iOException38) {
								this.field_35601_a.field_35792_e = -1L;
								this.field_35601_a.field_35791_d = "\u00a74Communication error";
								z27 = false;
								break label186;
							} catch (Exception exception39) {
								this.field_35601_a.field_35792_e = -1L;
								this.field_35601_a.field_35791_d = "ERROR: " + exception39.getClass();
								z27 = false;
								break label185;
							} finally {
								if(z27) {
									synchronized(GuiMultiplayer.func_35321_g()) {
										GuiMultiplayer.func_35335_o();
									}
								}
							}

							synchronized(GuiMultiplayer.func_35321_g()) {
								GuiMultiplayer.func_35335_o();
								return;
							}
						}

						synchronized(GuiMultiplayer.func_35321_g()) {
							GuiMultiplayer.func_35335_o();
							return;
						}
					}

					synchronized(GuiMultiplayer.func_35321_g()) {
						GuiMultiplayer.func_35335_o();
						return;
					}
				}

				synchronized(GuiMultiplayer.func_35321_g()) {
					GuiMultiplayer.func_35335_o();
					return;
				}
			}

			synchronized(GuiMultiplayer.func_35321_g()) {
				GuiMultiplayer.func_35335_o();
				return;
			}
		}

		synchronized(GuiMultiplayer.func_35321_g()) {
			GuiMultiplayer.func_35335_o();
		}

	}
}
