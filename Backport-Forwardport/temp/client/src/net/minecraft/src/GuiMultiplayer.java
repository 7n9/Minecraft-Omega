package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

public class GuiMultiplayer extends GuiScreen {
	private static int field_35344_a = 0;
	private static Object field_35343_b = new Object();
	private GuiScreen parentScreen;
	private GuiSlotServer field_35342_d;
	private List field_35340_f = new ArrayList();
	private int field_35341_g = -1;
	private GuiButton field_35347_h;
	private GuiButton field_35348_i;
	private GuiButton field_35345_j;
	private boolean field_35346_k = false;
	private boolean field_35353_s = false;
	private boolean field_35352_t = false;
	private boolean field_35351_u = false;
	private String field_35350_v = null;
	private ServerNBTStorage field_35349_w = null;

	public GuiMultiplayer(GuiScreen guiScreen1) {
		this.parentScreen = guiScreen1;
	}

	public void updateScreen() {
	}

	public void initGui() {
		this.func_35324_p();
		Keyboard.enableRepeatEvents(true);
		this.controlList.clear();
		this.field_35342_d = new GuiSlotServer(this);
		this.func_35337_c();
	}

	private void func_35324_p() {
		try {
			NBTTagCompound nBTTagCompound1 = CompressedStreamTools.func_35622_a(new File(this.mc.mcDataDir, "servers.dat"));
			NBTTagList nBTTagList2 = nBTTagCompound1.getTagList("servers");
			this.field_35340_f.clear();

			for(int i3 = 0; i3 < nBTTagList2.tagCount(); ++i3) {
				this.field_35340_f.add(ServerNBTStorage.func_35788_a((NBTTagCompound)nBTTagList2.tagAt(i3)));
			}
		} catch (Exception exception4) {
			exception4.printStackTrace();
		}

	}

	private void func_35323_q() {
		try {
			NBTTagList nBTTagList1 = new NBTTagList();

			for(int i2 = 0; i2 < this.field_35340_f.size(); ++i2) {
				nBTTagList1.setTag(((ServerNBTStorage)this.field_35340_f.get(i2)).func_35789_a());
			}

			NBTTagCompound nBTTagCompound4 = new NBTTagCompound();
			nBTTagCompound4.setTag("servers", nBTTagList1);
			CompressedStreamTools.func_35621_a(nBTTagCompound4, new File(this.mc.mcDataDir, "servers.dat"));
		} catch (Exception exception3) {
			exception3.printStackTrace();
		}

	}

	public void func_35337_c() {
		StringTranslate stringTranslate1 = StringTranslate.getInstance();
		this.controlList.add(this.field_35347_h = new GuiButton(7, this.width / 2 - 154, this.height - 28, 70, 20, stringTranslate1.translateKey("selectServer.edit")));
		this.controlList.add(this.field_35345_j = new GuiButton(2, this.width / 2 - 74, this.height - 28, 70, 20, stringTranslate1.translateKey("selectServer.delete")));
		this.controlList.add(this.field_35348_i = new GuiButton(1, this.width / 2 - 154, this.height - 52, 100, 20, stringTranslate1.translateKey("selectServer.select")));
		this.controlList.add(new GuiButton(4, this.width / 2 - 50, this.height - 52, 100, 20, stringTranslate1.translateKey("selectServer.direct")));
		this.controlList.add(new GuiButton(3, this.width / 2 + 4 + 50, this.height - 52, 100, 20, stringTranslate1.translateKey("selectServer.add")));
		this.controlList.add(new GuiButton(8, this.width / 2 + 4, this.height - 28, 70, 20, stringTranslate1.translateKey("selectServer.refresh")));
		this.controlList.add(new GuiButton(0, this.width / 2 + 4 + 76, this.height - 28, 75, 20, stringTranslate1.translateKey("gui.cancel")));
		boolean z2 = this.field_35341_g >= 0 && this.field_35341_g < this.field_35342_d.getSize();
		this.field_35348_i.enabled = z2;
		this.field_35347_h.enabled = z2;
		this.field_35345_j.enabled = z2;
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	protected void actionPerformed(GuiButton guiButton1) {
		if(guiButton1.enabled) {
			if(guiButton1.id == 2) {
				String string2 = ((ServerNBTStorage)this.field_35340_f.get(this.field_35341_g)).field_35795_a;
				if(string2 != null) {
					this.field_35346_k = true;
					StringTranslate stringTranslate3 = StringTranslate.getInstance();
					String string4 = stringTranslate3.translateKey("selectServer.deleteQuestion");
					String string5 = "\'" + string2 + "\' " + stringTranslate3.translateKey("selectServer.deleteWarning");
					String string6 = stringTranslate3.translateKey("selectServer.deleteButton");
					String string7 = stringTranslate3.translateKey("gui.cancel");
					GuiYesNo guiYesNo8 = new GuiYesNo(this, string4, string5, string6, string7, this.field_35341_g);
					this.mc.displayGuiScreen(guiYesNo8);
				}
			} else if(guiButton1.id == 1) {
				this.func_35322_a(this.field_35341_g);
			} else if(guiButton1.id == 4) {
				this.field_35351_u = true;
				this.mc.displayGuiScreen(new GuiScreenServerList(this, this.field_35349_w = new ServerNBTStorage(StatCollector.translateToLocal("selectServer.defaultName"), "")));
			} else if(guiButton1.id == 3) {
				this.field_35353_s = true;
				this.mc.displayGuiScreen(new GuiScreenAddServer(this, this.field_35349_w = new ServerNBTStorage(StatCollector.translateToLocal("selectServer.defaultName"), "")));
			} else if(guiButton1.id == 7) {
				this.field_35352_t = true;
				ServerNBTStorage serverNBTStorage9 = (ServerNBTStorage)this.field_35340_f.get(this.field_35341_g);
				this.mc.displayGuiScreen(new GuiScreenAddServer(this, this.field_35349_w = new ServerNBTStorage(serverNBTStorage9.field_35795_a, serverNBTStorage9.field_35793_b)));
			} else if(guiButton1.id == 0) {
				this.mc.displayGuiScreen(this.parentScreen);
			} else if(guiButton1.id == 8) {
				this.mc.displayGuiScreen(new GuiMultiplayer(this.parentScreen));
			} else {
				this.field_35342_d.actionPerformed(guiButton1);
			}

		}
	}

	public void deleteWorld(boolean z1, int i2) {
		if(this.field_35346_k) {
			this.field_35346_k = false;
			if(z1) {
				this.field_35340_f.remove(i2);
				this.func_35323_q();
			}

			this.mc.displayGuiScreen(this);
		} else if(this.field_35351_u) {
			this.field_35351_u = false;
			if(z1) {
				this.func_35330_a(this.field_35349_w);
			} else {
				this.mc.displayGuiScreen(this);
			}
		} else if(this.field_35353_s) {
			this.field_35353_s = false;
			if(z1) {
				this.field_35340_f.add(this.field_35349_w);
				this.func_35323_q();
			}

			this.mc.displayGuiScreen(this);
		} else if(this.field_35352_t) {
			this.field_35352_t = false;
			if(z1) {
				ServerNBTStorage serverNBTStorage3 = (ServerNBTStorage)this.field_35340_f.get(this.field_35341_g);
				serverNBTStorage3.field_35795_a = this.field_35349_w.field_35795_a;
				serverNBTStorage3.field_35793_b = this.field_35349_w.field_35793_b;
				this.func_35323_q();
			}

			this.mc.displayGuiScreen(this);
		}

	}

	private int parseIntWithDefault(String string1, int i2) {
		try {
			return Integer.parseInt(string1.trim());
		} catch (Exception exception4) {
			return i2;
		}
	}

	protected void keyTyped(char c1, int i2) {
		if(c1 == 13) {
			this.actionPerformed((GuiButton)this.controlList.get(2));
		}

	}

	protected void mouseClicked(int i1, int i2, int i3) {
		super.mouseClicked(i1, i2, i3);
	}

	public void drawScreen(int i1, int i2, float f3) {
		this.field_35350_v = null;
		StringTranslate stringTranslate4 = StringTranslate.getInstance();
		this.drawDefaultBackground();
		this.field_35342_d.drawScreen(i1, i2, f3);
		this.drawCenteredString(this.fontRenderer, stringTranslate4.translateKey("multiplayer.title"), this.width / 2, 20, 0xFFFFFF);
		super.drawScreen(i1, i2, f3);
		if(this.field_35350_v != null) {
			this.func_35325_a(this.field_35350_v, i1, i2);
		}

	}

	private void func_35322_a(int i1) {
		this.func_35330_a((ServerNBTStorage)this.field_35340_f.get(i1));
	}

	private void func_35330_a(ServerNBTStorage serverNBTStorage1) {
		String string2 = serverNBTStorage1.field_35793_b;
		String[] string3 = string2.split(":");
		if(string2.startsWith("[")) {
			int i4 = string2.indexOf("]");
			if(i4 > 0) {
				String string5 = string2.substring(1, i4);
				String string6 = string2.substring(i4 + 1).trim();
				if(string6.startsWith(":") && string6.length() > 0) {
					string6 = string6.substring(1);
					string3 = new String[]{string5, string6};
				} else {
					string3 = new String[]{string5};
				}
			}
		}

		if(string3.length > 2) {
			string3 = new String[]{string2};
		}

		this.mc.displayGuiScreen(new GuiConnecting(this.mc, string3[0], string3.length > 1 ? this.parseIntWithDefault(string3[1], 25565) : 25565));
	}

	private void func_35328_b(ServerNBTStorage serverNBTStorage1) throws IOException {
		String string2 = serverNBTStorage1.field_35793_b;
		String[] string3 = string2.split(":");
		if(string2.startsWith("[")) {
			int i4 = string2.indexOf("]");
			if(i4 > 0) {
				String string5 = string2.substring(1, i4);
				String string6 = string2.substring(i4 + 1).trim();
				if(string6.startsWith(":") && string6.length() > 0) {
					string6 = string6.substring(1);
					string3 = new String[]{string5, string6};
				} else {
					string3 = new String[]{string5};
				}
			}
		}

		if(string3.length > 2) {
			string3 = new String[]{string2};
		}

		String string29 = string3[0];
		int i30 = string3.length > 1 ? this.parseIntWithDefault(string3[1], 25565) : 25565;
		Socket socket31 = null;
		DataInputStream dataInputStream7 = null;
		DataOutputStream dataOutputStream8 = null;

		try {
			socket31 = new Socket();
			socket31.setSoTimeout(3000);
			socket31.setTcpNoDelay(true);
			socket31.setTrafficClass(18);
			socket31.connect(new InetSocketAddress(string29, i30), 3000);
			dataInputStream7 = new DataInputStream(socket31.getInputStream());
			dataOutputStream8 = new DataOutputStream(socket31.getOutputStream());
			dataOutputStream8.write(254);
			if(dataInputStream7.read() != 255) {
				throw new IOException("Bad message");
			}

			String string9 = Packet.readString(dataInputStream7, 64);
			char[] c10 = string9.toCharArray();

			int i11;
			for(i11 = 0; i11 < c10.length; ++i11) {
				if(c10[i11] != 167 && ChatAllowedCharacters.allowedCharacters.indexOf(c10[i11]) < 0) {
					c10[i11] = 63;
				}
			}

			string9 = new String(c10);
			string3 = string9.split("\u00a7");
			string9 = string3[0];
			i11 = -1;
			int i12 = -1;

			try {
				i11 = Integer.parseInt(string3[1]);
				i12 = Integer.parseInt(string3[2]);
			} catch (Exception exception27) {
			}

			serverNBTStorage1.field_35791_d = "\u00a77" + string9;
			if(i11 >= 0 && i12 > 0) {
				serverNBTStorage1.field_35794_c = "\u00a77" + i11 + "\u00a78/\u00a77" + i12;
			} else {
				serverNBTStorage1.field_35794_c = "\u00a78???";
			}
		} finally {
			try {
				if(dataInputStream7 != null) {
					dataInputStream7.close();
				}
			} catch (Throwable throwable26) {
			}

			try {
				if(dataOutputStream8 != null) {
					dataOutputStream8.close();
				}
			} catch (Throwable throwable25) {
			}

			try {
				if(socket31 != null) {
					socket31.close();
				}
			} catch (Throwable throwable24) {
			}

		}

	}

	protected void func_35325_a(String string1, int i2, int i3) {
		if(string1 != null) {
			int i4 = i2 + 12;
			int i5 = i3 - 12;
			int i6 = this.fontRenderer.getStringWidth(string1);
			this.drawGradientRect(i4 - 3, i5 - 3, i4 + i6 + 3, i5 + 8 + 3, -1073741824, -1073741824);
			this.fontRenderer.drawStringWithShadow(string1, i4, i5, -1);
		}
	}

	static List func_35320_a(GuiMultiplayer guiMultiplayer0) {
		return guiMultiplayer0.field_35340_f;
	}

	static int func_35326_a(GuiMultiplayer guiMultiplayer0, int i1) {
		return guiMultiplayer0.field_35341_g = i1;
	}

	static int func_35333_b(GuiMultiplayer guiMultiplayer0) {
		return guiMultiplayer0.field_35341_g;
	}

	static GuiButton func_35329_c(GuiMultiplayer guiMultiplayer0) {
		return guiMultiplayer0.field_35348_i;
	}

	static GuiButton func_35334_d(GuiMultiplayer guiMultiplayer0) {
		return guiMultiplayer0.field_35347_h;
	}

	static GuiButton func_35339_e(GuiMultiplayer guiMultiplayer0) {
		return guiMultiplayer0.field_35345_j;
	}

	static void func_35332_b(GuiMultiplayer guiMultiplayer0, int i1) {
		guiMultiplayer0.func_35322_a(i1);
	}

	static Object func_35321_g() {
		return field_35343_b;
	}

	static int func_35338_m() {
		return field_35344_a;
	}

	static int func_35331_n() {
		return field_35344_a++;
	}

	static void func_35336_a(GuiMultiplayer guiMultiplayer0, ServerNBTStorage serverNBTStorage1) throws IOException {
		guiMultiplayer0.func_35328_b(serverNBTStorage1);
	}

	static int func_35335_o() {
		return field_35344_a--;
	}

	static String func_35327_a(GuiMultiplayer guiMultiplayer0, String string1) {
		return guiMultiplayer0.field_35350_v = string1;
	}
}
