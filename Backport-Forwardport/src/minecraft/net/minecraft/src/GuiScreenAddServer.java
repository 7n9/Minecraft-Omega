package net.minecraft.src;

import org.lwjgl.input.Keyboard;

public class GuiScreenAddServer extends GuiScreen {
	private GuiScreen field_35362_a;
	private GuiTextField field_35360_b;
	private GuiTextField field_35361_c;
	private ServerNBTStorage field_35359_d;

	public GuiScreenAddServer(GuiScreen guiScreen1, ServerNBTStorage serverNBTStorage2) {
		this.field_35362_a = guiScreen1;
		this.field_35359_d = serverNBTStorage2;
	}

	public void updateScreen() {
		this.field_35361_c.updateCursorCounter();
		this.field_35360_b.updateCursorCounter();
	}

	public void initGui() {
		StringTranslate stringTranslate1 = StringTranslate.getInstance();
		Keyboard.enableRepeatEvents(true);
		this.controlList.clear();
		this.controlList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12, stringTranslate1.translateKey("addServer.add")));
		this.controlList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 12, stringTranslate1.translateKey("gui.cancel")));
		this.field_35361_c = new GuiTextField(this, this.fontRenderer, this.width / 2 - 100, 76, 200, 20, this.field_35359_d.field_35795_a);
		this.field_35361_c.isFocused = true;
		this.field_35361_c.setMaxStringLength(32);
		this.field_35360_b = new GuiTextField(this, this.fontRenderer, this.width / 2 - 100, 116, 200, 20, this.field_35359_d.field_35793_b);
		this.field_35360_b.setMaxStringLength(128);
		((GuiButton)this.controlList.get(0)).enabled = this.field_35360_b.getText().length() > 0 && this.field_35361_c.getText().length() > 0;
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	protected void actionPerformed(GuiButton guiButton1) {
		if(guiButton1.enabled) {
			if(guiButton1.id == 1) {
				this.field_35362_a.deleteWorld(false, 0);
			} else if(guiButton1.id == 0) {
				this.field_35359_d.field_35795_a = this.field_35361_c.getText();
				this.field_35359_d.field_35793_b = this.field_35360_b.getText();
				this.field_35362_a.deleteWorld(true, 0);
			}

		}
	}

	protected void keyTyped(char c1, int i2) {
		this.field_35361_c.textboxKeyTyped(c1, i2);
		this.field_35360_b.textboxKeyTyped(c1, i2);
		if(c1 == 9) {
			if(this.field_35361_c.isFocused) {
				this.field_35361_c.isFocused = false;
				this.field_35360_b.isFocused = true;
			} else {
				this.field_35361_c.isFocused = true;
				this.field_35360_b.isFocused = false;
			}
		}

		if(c1 == 13) {
			this.actionPerformed((GuiButton)this.controlList.get(0));
		}

		((GuiButton)this.controlList.get(0)).enabled = this.field_35360_b.getText().length() > 0 && this.field_35361_c.getText().length() > 0;
		if(((GuiButton)this.controlList.get(0)).enabled) {
			String string3 = this.field_35360_b.getText().trim();
			String[] string4 = string3.split(":");
			if(string4.length > 2) {
				((GuiButton)this.controlList.get(0)).enabled = false;
			}
		}

	}

	protected void mouseClicked(int i1, int i2, int i3) {
		super.mouseClicked(i1, i2, i3);
		this.field_35360_b.mouseClicked(i1, i2, i3);
		this.field_35361_c.mouseClicked(i1, i2, i3);
	}

	public void drawScreen(int i1, int i2, float f3) {
		StringTranslate stringTranslate4 = StringTranslate.getInstance();
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRenderer, stringTranslate4.translateKey("addServer.title"), this.width / 2, this.height / 4 - 60 + 20, 0xFFFFFF);
		this.drawString(this.fontRenderer, stringTranslate4.translateKey("addServer.enterName"), this.width / 2 - 100, 63, 10526880);
		this.drawString(this.fontRenderer, stringTranslate4.translateKey("addServer.enterIp"), this.width / 2 - 100, 104, 10526880);
		this.field_35361_c.drawTextBox();
		this.field_35360_b.drawTextBox();
		super.drawScreen(i1, i2, f3);
	}
}
