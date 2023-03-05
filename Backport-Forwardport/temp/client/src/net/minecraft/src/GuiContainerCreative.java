package net.minecraft.src;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiContainerCreative extends GuiContainer {
	private static InventoryBasic field_35311_f = new InventoryBasic("tmp", 72);
	private float field_35312_g = 0.0F;
	private boolean field_35313_h = false;
	private boolean field_35314_i;

	public GuiContainerCreative(EntityPlayer entityPlayer1) {
		super(new ContainerCreative(entityPlayer1));
		entityPlayer1.craftingInventory = this.inventorySlots;
		this.allowUserInput = true;
		entityPlayer1.addStat(AchievementList.openInventory, 1);
		this.ySize = 208;
	}

	public void updateScreen() {
		if(!this.mc.playerController.func_35640_h()) {
			this.mc.displayGuiScreen(new GuiInventory(this.mc.thePlayer));
		}

	}

	protected void func_35309_a(Slot slot1, int i2, int i3, boolean z4) {
		InventoryPlayer inventoryPlayer5;
		ItemStack itemStack6;
		if(slot1 != null) {
			if(slot1.inventory == field_35311_f) {
				inventoryPlayer5 = this.mc.thePlayer.inventory;
				itemStack6 = inventoryPlayer5.getItemStack();
				ItemStack itemStack7 = slot1.getStack();
				if(itemStack6 != null && itemStack7 != null && itemStack6.itemID == itemStack7.itemID) {
					if(i3 == 0) {
						if(z4) {
							itemStack6.stackSize = itemStack6.getMaxStackSize();
						} else if(itemStack6.stackSize < itemStack6.getMaxStackSize()) {
							++itemStack6.stackSize;
						}
					} else if(itemStack6.stackSize <= 1) {
						inventoryPlayer5.setItemStack((ItemStack)null);
					} else {
						--itemStack6.stackSize;
					}
				} else if(itemStack6 != null) {
					inventoryPlayer5.setItemStack((ItemStack)null);
				} else if(itemStack7 == null) {
					inventoryPlayer5.setItemStack((ItemStack)null);
				} else if(itemStack6 == null || itemStack6.itemID != itemStack7.itemID) {
					inventoryPlayer5.setItemStack(ItemStack.copyItemStack(itemStack7));
					itemStack6 = inventoryPlayer5.getItemStack();
					if(z4) {
						itemStack6.stackSize = itemStack6.getMaxStackSize();
					}
				}
			} else {
				this.inventorySlots.slotClick(slot1.slotNumber, i3, z4, this.mc.thePlayer);
				ItemStack itemStack8 = this.inventorySlots.getSlot(slot1.slotNumber).getStack();
				this.mc.playerController.func_35637_a(itemStack8, slot1.slotNumber - this.inventorySlots.inventorySlots.size() + 9 + 36);
			}
		} else {
			inventoryPlayer5 = this.mc.thePlayer.inventory;
			if(inventoryPlayer5.getItemStack() != null) {
				if(i3 == 0) {
					this.mc.thePlayer.dropPlayerItem(inventoryPlayer5.getItemStack());
					this.mc.playerController.func_35639_a(inventoryPlayer5.getItemStack());
					inventoryPlayer5.setItemStack((ItemStack)null);
				}

				if(i3 == 1) {
					itemStack6 = inventoryPlayer5.getItemStack().splitStack(1);
					this.mc.thePlayer.dropPlayerItem(itemStack6);
					this.mc.playerController.func_35639_a(itemStack6);
					if(inventoryPlayer5.getItemStack().stackSize == 0) {
						inventoryPlayer5.setItemStack((ItemStack)null);
					}
				}
			}
		}

	}

	public void initGui() {
		if(!this.mc.playerController.func_35640_h()) {
			this.mc.displayGuiScreen(new GuiInventory(this.mc.thePlayer));
		}

		this.controlList.clear();
	}

	protected void drawGuiContainerForegroundLayer() {
		this.fontRenderer.drawString("Item selection", 8, 6, 4210752);
	}

	public void handleMouseInput() {
		super.handleMouseInput();
		int i1 = Mouse.getEventDWheel();
		if(i1 != 0) {
			int i2 = ((ContainerCreative)this.inventorySlots).field_35375_a.size() / 8 - 8 + 1;
			if(i1 > 0) {
				i1 = 1;
			}

			if(i1 < 0) {
				i1 = -1;
			}

			this.field_35312_g = (float)((double)this.field_35312_g - (double)i1 / (double)i2);
			if(this.field_35312_g < 0.0F) {
				this.field_35312_g = 0.0F;
			}

			if(this.field_35312_g > 1.0F) {
				this.field_35312_g = 1.0F;
			}

			((ContainerCreative)this.inventorySlots).func_35374_a(this.field_35312_g);
		}

	}

	public void drawScreen(int i1, int i2, float f3) {
		boolean z4 = Mouse.isButtonDown(0);
		int i5 = (this.width - this.xSize) / 2;
		int i6 = (this.height - this.ySize) / 2;
		int i7 = i5 + 155;
		int i8 = i6 + 17;
		int i9 = i7 + 14;
		int i10 = i8 + 160 + 2;
		if(!this.field_35314_i && z4 && i1 >= i7 && i2 >= i8 && i1 < i9 && i2 < i10) {
			this.field_35313_h = true;
		}

		if(!z4) {
			this.field_35313_h = false;
		}

		this.field_35314_i = z4;
		if(this.field_35313_h) {
			this.field_35312_g = (float)(i2 - (i8 + 8)) / ((float)(i10 - i8) - 16.0F);
			if(this.field_35312_g < 0.0F) {
				this.field_35312_g = 0.0F;
			}

			if(this.field_35312_g > 1.0F) {
				this.field_35312_g = 1.0F;
			}

			((ContainerCreative)this.inventorySlots).func_35374_a(this.field_35312_g);
		}

		super.drawScreen(i1, i2, f3);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		int i11 = this.mc.renderEngine.getTexture("/gui/allitems.png");
		this.mc.renderEngine.bindTexture(i11);
		this.drawTexturedModalRect(i5 + 154, i6 + 17 + (int)((float)(i10 - i8 - 17) * this.field_35312_g), 0, 208, 16, 16);
	}

	protected void drawGuiContainerBackgroundLayer(float f1) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int i2 = this.mc.renderEngine.getTexture("/gui/allitems.png");
		this.mc.renderEngine.bindTexture(i2);
		int i3 = (this.width - this.xSize) / 2;
		int i4 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i3, i4, 0, 0, this.xSize, this.ySize);
	}

	protected void actionPerformed(GuiButton guiButton1) {
		if(guiButton1.id == 0) {
			this.mc.displayGuiScreen(new GuiAchievements(this.mc.statFileWriter));
		}

		if(guiButton1.id == 1) {
			this.mc.displayGuiScreen(new GuiStats(this, this.mc.statFileWriter));
		}

	}

	static InventoryBasic func_35310_g() {
		return field_35311_f;
	}
}
