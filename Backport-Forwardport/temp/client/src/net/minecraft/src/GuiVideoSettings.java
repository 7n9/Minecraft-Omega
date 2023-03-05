package net.minecraft.src;

public class GuiVideoSettings extends GuiScreen {
	private GuiScreen parentGuiScreen;
	protected String field_22107_a = "Video Settings";
	private GameSettings guiGameSettings;
	private static EnumOptions[] videoOptions = new EnumOptions[]{EnumOptions.GRAPHICS, EnumOptions.RENDER_DISTANCE, EnumOptions.AMBIENT_OCCLUSION, EnumOptions.FRAMERATE_LIMIT, EnumOptions.ANAGLYPH, EnumOptions.VIEW_BOBBING, EnumOptions.GUI_SCALE, EnumOptions.ADVANCED_OPENGL, EnumOptions.GAMMA};

	public GuiVideoSettings(GuiScreen guiScreen1, GameSettings gameSettings2) {
		this.parentGuiScreen = guiScreen1;
		this.guiGameSettings = gameSettings2;
	}

	public void initGui() {
		StringTranslate stringTranslate1 = StringTranslate.getInstance();
		this.field_22107_a = stringTranslate1.translateKey("options.videoTitle");
		int i2 = 0;
		EnumOptions[] enumOptions3 = videoOptions;
		int i4 = enumOptions3.length;

		for(int i5 = 0; i5 < i4; ++i5) {
			EnumOptions enumOptions6 = enumOptions3[i5];
			if(!enumOptions6.getEnumFloat()) {
				this.controlList.add(new GuiSmallButton(enumOptions6.returnEnumOrdinal(), this.width / 2 - 155 + i2 % 2 * 160, this.height / 6 + 24 * (i2 >> 1), enumOptions6, this.guiGameSettings.getKeyBinding(enumOptions6)));
			} else {
				this.controlList.add(new GuiSlider(enumOptions6.returnEnumOrdinal(), this.width / 2 - 155 + i2 % 2 * 160, this.height / 6 + 24 * (i2 >> 1), enumOptions6, this.guiGameSettings.getKeyBinding(enumOptions6), this.guiGameSettings.getOptionFloatValue(enumOptions6)));
			}

			++i2;
		}

		this.controlList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, stringTranslate1.translateKey("gui.done")));
	}

	protected void actionPerformed(GuiButton guiButton1) {
		if(guiButton1.enabled) {
			int i2 = this.guiGameSettings.guiScale;
			if(guiButton1.id < 100 && guiButton1 instanceof GuiSmallButton) {
				this.guiGameSettings.setOptionValue(((GuiSmallButton)guiButton1).returnEnumOptions(), 1);
				guiButton1.displayString = this.guiGameSettings.getKeyBinding(EnumOptions.getEnumOptions(guiButton1.id));
			}

			if(guiButton1.id == 200) {
				this.mc.gameSettings.saveOptions();
				this.mc.displayGuiScreen(this.parentGuiScreen);
			}

			if(this.guiGameSettings.guiScale != i2) {
				ScaledResolution scaledResolution3 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
				int i4 = scaledResolution3.getScaledWidth();
				int i5 = scaledResolution3.getScaledHeight();
				this.setWorldAndResolution(this.mc, i4, i5);
			}

		}
	}

	public void drawScreen(int i1, int i2, float f3) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRenderer, this.field_22107_a, this.width / 2, 20, 0xFFFFFF);
		super.drawScreen(i1, i2, f3);
	}
}
