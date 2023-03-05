package net.minecraft.src;

import java.util.Random;

import org.lwjgl.input.Keyboard;

public class GuiCreateWorld extends GuiScreen {
	private GuiScreen parentGuiScreen;
	private GuiTextField textboxWorldName;
	private GuiTextField textboxSeed;
	private String folderName;
	private String field_35364_f = "survival";
	private boolean field_35365_g = true;
	private boolean createClicked;
	private boolean field_35368_i;
	private GuiButton field_35366_j;
	private GuiButton field_35367_k;
	private GuiButton field_35372_s;
	private GuiButton field_35371_t;
	private String field_35370_u;
	private String field_35369_v;

	public GuiCreateWorld(GuiScreen guiScreen1) {
		this.parentGuiScreen = guiScreen1;
	}

	public void updateScreen() {
		this.textboxWorldName.updateCursorCounter();
		this.textboxSeed.updateCursorCounter();
	}

	public void initGui() {
		StringTranslate stringTranslate1 = StringTranslate.getInstance();
		Keyboard.enableRepeatEvents(true);
		this.controlList.clear();
		this.controlList.add(new GuiButton(0, this.width / 2 - 155, this.height - 28, 150, 20, stringTranslate1.translateKey("selectWorld.create")));
		this.controlList.add(new GuiButton(1, this.width / 2 + 5, this.height - 28, 150, 20, stringTranslate1.translateKey("gui.cancel")));
		this.controlList.add(this.field_35366_j = new GuiButton(2, this.width / 2 - 75, 100, 150, 20, stringTranslate1.translateKey("selectWorld.gameMode")));
		this.controlList.add(this.field_35367_k = new GuiButton(3, this.width / 2 - 75, 172, 150, 20, stringTranslate1.translateKey("selectWorld.moreWorldOptions")));
		this.controlList.add(this.field_35372_s = new GuiButton(4, this.width / 2 - 155, 100, 150, 20, stringTranslate1.translateKey("selectWorld.mapFeatures")));
		this.field_35372_s.enabled2 = false;
		this.controlList.add(this.field_35371_t = new GuiButton(5, this.width / 2 + 5, 100, 150, 20, stringTranslate1.translateKey("selectWorld.mapType")));
		this.field_35371_t.enabled2 = false;
		this.field_35371_t.enabled = false;
		this.textboxWorldName = new GuiTextField(this, this.fontRenderer, this.width / 2 - 100, 60, 200, 20, stringTranslate1.translateKey("selectWorld.newWorld"));
		this.textboxWorldName.isFocused = true;
		this.textboxWorldName.setMaxStringLength(32);
		this.textboxSeed = new GuiTextField(this, this.fontRenderer, this.width / 2 - 100, 60, 200, 20, "");
		this.func_22129_j();
		this.func_35363_g();
	}

	private void func_22129_j() {
		this.folderName = this.textboxWorldName.getText().trim();
		char[] c1 = ChatAllowedCharacters.allowedCharactersArray;
		int i2 = c1.length;

		for(int i3 = 0; i3 < i2; ++i3) {
			char c4 = c1[i3];
			this.folderName = this.folderName.replace(c4, '_');
		}

		if(MathHelper.stringNullOrLengthZero(this.folderName)) {
			this.folderName = "World";
		}

		this.folderName = generateUnusedFolderName(this.mc.getSaveLoader(), this.folderName);
	}

	private void func_35363_g() {
		StringTranslate stringTranslate1 = StringTranslate.getInstance();
		this.field_35366_j.displayString = stringTranslate1.translateKey("selectWorld.gameMode") + " " + stringTranslate1.translateKey("selectWorld.gameMode." + this.field_35364_f);
		this.field_35370_u = stringTranslate1.translateKey("selectWorld.gameMode." + this.field_35364_f + ".line1");
		this.field_35369_v = stringTranslate1.translateKey("selectWorld.gameMode." + this.field_35364_f + ".line2");
		this.field_35372_s.displayString = stringTranslate1.translateKey("selectWorld.mapFeatures") + " ";
		if(this.field_35365_g) {
			this.field_35372_s.displayString = this.field_35372_s.displayString + stringTranslate1.translateKey("options.on");
		} else {
			this.field_35372_s.displayString = this.field_35372_s.displayString + stringTranslate1.translateKey("options.off");
		}

		this.field_35371_t.displayString = stringTranslate1.translateKey("selectWorld.mapType") + " " + stringTranslate1.translateKey("selectWorld.mapType.normal");
	}

	public static String generateUnusedFolderName(ISaveFormat iSaveFormat0, String string1) {
		while(iSaveFormat0.getWorldInfo(string1) != null) {
			string1 = string1 + "-";
		}

		return string1;
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	protected void actionPerformed(GuiButton guiButton1) {
		if(guiButton1.enabled) {
			if(guiButton1.id == 1) {
				this.mc.displayGuiScreen(this.parentGuiScreen);
			} else if(guiButton1.id == 0) {
				this.mc.displayGuiScreen((GuiScreen)null);
				if(this.createClicked) {
					return;
				}

				this.createClicked = true;
				long j2 = (new Random()).nextLong();
				String string4 = this.textboxSeed.getText();
				if(!MathHelper.stringNullOrLengthZero(string4)) {
					try {
						long j5 = Long.parseLong(string4);
						if(j5 != 0L) {
							j2 = j5;
						}
					} catch (NumberFormatException numberFormatException7) {
						j2 = (long)string4.hashCode();
					}
				}

				byte b9 = 0;
				if(this.field_35364_f.equals("creative")) {
					b9 = 1;
					this.mc.playerController = new PlayerControllerTest(this.mc);
				} else {
					this.mc.playerController = new PlayerControllerSP(this.mc);
				}

				this.mc.startWorld(this.folderName, this.textboxWorldName.getText(), new WorldSettings(j2, b9, this.field_35365_g));
				this.mc.displayGuiScreen((GuiScreen)null);
			} else if(guiButton1.id == 3) {
				this.field_35368_i = !this.field_35368_i;
				this.field_35366_j.enabled2 = !this.field_35368_i;
				this.field_35372_s.enabled2 = this.field_35368_i;
				this.field_35371_t.enabled2 = this.field_35368_i;
				StringTranslate stringTranslate8;
				if(this.field_35368_i) {
					stringTranslate8 = StringTranslate.getInstance();
					this.field_35367_k.displayString = stringTranslate8.translateKey("gui.done");
				} else {
					stringTranslate8 = StringTranslate.getInstance();
					this.field_35367_k.displayString = stringTranslate8.translateKey("selectWorld.moreWorldOptions");
				}
			} else if(guiButton1.id == 2) {
				if(this.field_35364_f.equals("survival")) {
					this.field_35364_f = "creative";
				} else {
					this.field_35364_f = "survival";
				}

				this.func_35363_g();
			} else if(guiButton1.id == 4) {
				this.field_35365_g = !this.field_35365_g;
				this.func_35363_g();
			}

		}
	}

	protected void keyTyped(char c1, int i2) {
		if(this.textboxWorldName.isFocused && !this.field_35368_i) {
			this.textboxWorldName.textboxKeyTyped(c1, i2);
		} else if(this.textboxSeed.isFocused && this.field_35368_i) {
			this.textboxSeed.textboxKeyTyped(c1, i2);
		}

		if(c1 == 13) {
			this.actionPerformed((GuiButton)this.controlList.get(0));
		}

		((GuiButton)this.controlList.get(0)).enabled = this.textboxWorldName.getText().length() > 0;
		this.func_22129_j();
	}

	protected void mouseClicked(int i1, int i2, int i3) {
		super.mouseClicked(i1, i2, i3);
		if(!this.field_35368_i) {
			this.textboxWorldName.mouseClicked(i1, i2, i3);
		} else {
			this.textboxSeed.mouseClicked(i1, i2, i3);
		}

	}

	public void drawScreen(int i1, int i2, float f3) {
		StringTranslate stringTranslate4 = StringTranslate.getInstance();
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRenderer, stringTranslate4.translateKey("selectWorld.create"), this.width / 2, 20, 0xFFFFFF);
		if(!this.field_35368_i) {
			this.drawString(this.fontRenderer, stringTranslate4.translateKey("selectWorld.enterName"), this.width / 2 - 100, 47, 10526880);
			this.drawString(this.fontRenderer, stringTranslate4.translateKey("selectWorld.resultFolder") + " " + this.folderName, this.width / 2 - 100, 85, 10526880);
			this.textboxWorldName.drawTextBox();
			this.drawString(this.fontRenderer, this.field_35370_u, this.width / 2 - 100, 122, 10526880);
			this.drawString(this.fontRenderer, this.field_35369_v, this.width / 2 - 100, 134, 10526880);
		} else {
			this.drawString(this.fontRenderer, stringTranslate4.translateKey("selectWorld.enterSeed"), this.width / 2 - 100, 47, 10526880);
			this.drawString(this.fontRenderer, stringTranslate4.translateKey("selectWorld.seedInfo"), this.width / 2 - 100, 85, 10526880);
			this.drawString(this.fontRenderer, stringTranslate4.translateKey("selectWorld.mapFeatures.info"), this.width / 2 - 150, 122, 10526880);
			this.textboxSeed.drawTextBox();
		}

		super.drawScreen(i1, i2, f3);
	}

	public void selectNextField() {
		if(this.textboxWorldName.isFocused) {
			this.textboxWorldName.setFocused(false);
			this.textboxSeed.setFocused(true);
		} else {
			this.textboxWorldName.setFocused(true);
			this.textboxSeed.setFocused(false);
		}

	}
}
