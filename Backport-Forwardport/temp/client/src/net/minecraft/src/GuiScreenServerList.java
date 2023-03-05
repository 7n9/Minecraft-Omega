package net.minecraft.src;

import org.lwjgl.input.Keyboard;

public class GuiScreenServerList extends GuiScreen {
	private GuiScreen field_35319_a;
	private GuiTextField field_35317_b;
	private ServerNBTStorage field_35318_c;

	public GuiScreenServerList(GuiScreen guiScreen1, ServerNBTStorage serverNBTStorage2) {
		this.field_35319_a = guiScreen1;
		this.field_35318_c = serverNBTStorage2;
	}

	public void updateScreen() {
		this.field_35317_b.updateCursorCounter();
	}

	public void initGui() {
		StringTranslate stringTranslate1 = StringTranslate.getInstance();
		Keyboard.enableRepeatEvents(true);
		this.controlList.clear();
		this.controlList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12, stringTranslate1.translateKey("selectServer.select")));
		this.controlList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 12, stringTranslate1.translateKey("gui.cancel")));
		this.field_35317_b = new GuiTextField(this, this.fontRenderer, this.width / 2 - 100, 116, 200, 20, this.field_35318_c.field_35793_b);
		this.field_35317_b.setMaxStringLength(128);
		((GuiButton)this.controlList.get(0)).enabled = this.field_35317_b.getText().length() > 0;
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	protected void actionPerformed(GuiButton guiButton1) {
		if(guiButton1.enabled) {
			if(guiButton1.id == 1) {
				this.field_35319_a.deleteWorld(false, 0);
			} else if(guiButton1.id == 0) {
				this.field_35318_c.field_35793_b = this.field_35317_b.getText();
				this.field_35319_a.deleteWorld(true, 0);
			}

		}
	}

	protected void keyTyped(char c1, int i2) {
		this.field_35317_b.textboxKeyTyped(c1, i2);
		if(c1 == 13) {
			this.actionPerformed((GuiButton)this.controlList.get(0));
		}

		((GuiButton)this.controlList.get(0)).enabled = this.field_35317_b.getText().length() > 0;
	}

	protected void mouseClicked(int i1, int i2, int i3) {
		super.mouseClicked(i1, i2, i3);
		this.field_35317_b.mouseClicked(i1, i2, i3);
	}

	public void drawScreen(int i1, int i2, float f3) {
		StringTranslate stringTranslate4 = StringTranslate.getInstance();
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRenderer, stringTranslate4.translateKey("selectServer.direct"), this.width / 2, this.height / 4 - 60 + 20, 0xFFFFFF);
		this.drawString(this.fontRenderer, stringTranslate4.translateKey("addServer.enterIp"), this.width / 2 - 100, 100, 10526880);
		this.field_35317_b.drawTextBox();
		super.drawScreen(i1, i2, f3);
	}
}
