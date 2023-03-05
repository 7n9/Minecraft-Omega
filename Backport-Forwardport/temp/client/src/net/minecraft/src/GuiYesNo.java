package net.minecraft.src;

public class GuiYesNo extends GuiScreen {
	private GuiScreen parentScreen;
	private String message1;
	private String message2;
	private String buttonText1;
	private String buttonText2;
	private int worldNumber;

	public GuiYesNo(GuiScreen guiScreen1, String string2, String string3, String string4, String string5, int i6) {
		this.parentScreen = guiScreen1;
		this.message1 = string2;
		this.message2 = string3;
		this.buttonText1 = string4;
		this.buttonText2 = string5;
		this.worldNumber = i6;
	}

	public void initGui() {
		this.controlList.add(new GuiSmallButton(0, this.width / 2 - 155 + 0, this.height / 6 + 96, this.buttonText1));
		this.controlList.add(new GuiSmallButton(1, this.width / 2 - 155 + 160, this.height / 6 + 96, this.buttonText2));
	}

	protected void actionPerformed(GuiButton guiButton1) {
		this.parentScreen.deleteWorld(guiButton1.id == 0, this.worldNumber);
	}

	public void drawScreen(int i1, int i2, float f3) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRenderer, this.message1, this.width / 2, 70, 0xFFFFFF);
		this.drawCenteredString(this.fontRenderer, this.message2, this.width / 2, 90, 0xFFFFFF);
		super.drawScreen(i1, i2, f3);
	}
}
