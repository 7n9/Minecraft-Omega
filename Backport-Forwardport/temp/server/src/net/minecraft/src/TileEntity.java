package net.minecraft.src;

import java.util.HashMap;
import java.util.Map;

public class TileEntity {
	private static Map nameToClassMap = new HashMap();
	private static Map classToNameMap = new HashMap();
	public World worldObj;
	public int xCoord;
	public int yCoord;
	public int zCoord;
	protected boolean tileEntityInvalid;
	public int field_35165_n = -1;
	public Block field_35166_o;

	private static void addMapping(Class class0, String string1) {
		if(classToNameMap.containsKey(string1)) {
			throw new IllegalArgumentException("Duplicate id: " + string1);
		} else {
			nameToClassMap.put(string1, class0);
			classToNameMap.put(class0, string1);
		}
	}

	public void readFromNBT(NBTTagCompound nBTTagCompound1) {
		this.xCoord = nBTTagCompound1.getInteger("x");
		this.yCoord = nBTTagCompound1.getInteger("y");
		this.zCoord = nBTTagCompound1.getInteger("z");
	}

	public void writeToNBT(NBTTagCompound nBTTagCompound1) {
		String string2 = (String)classToNameMap.get(this.getClass());
		if(string2 == null) {
			throw new RuntimeException(this.getClass() + " is missing a mapping! This is a bug!");
		} else {
			nBTTagCompound1.setString("id", string2);
			nBTTagCompound1.setInteger("x", this.xCoord);
			nBTTagCompound1.setInteger("y", this.yCoord);
			nBTTagCompound1.setInteger("z", this.zCoord);
		}
	}

	public void updateEntity() {
	}

	public static TileEntity createAndLoadEntity(NBTTagCompound nBTTagCompound0) {
		TileEntity tileEntity1 = null;

		try {
			Class class2 = (Class)nameToClassMap.get(nBTTagCompound0.getString("id"));
			if(class2 != null) {
				tileEntity1 = (TileEntity)class2.newInstance();
			}
		} catch (Exception exception3) {
			exception3.printStackTrace();
		}

		if(tileEntity1 != null) {
			tileEntity1.readFromNBT(nBTTagCompound0);
		} else {
			System.out.println("Skipping TileEntity with id " + nBTTagCompound0.getString("id"));
		}

		return tileEntity1;
	}

	public int getBlockMetadata() {
		if(this.field_35165_n == -1) {
			this.field_35165_n = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
		}

		return this.field_35165_n;
	}

	public void onInventoryChanged() {
		if(this.worldObj != null) {
			this.field_35165_n = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
			this.worldObj.updateTileEntityChunkAndDoNothing(this.xCoord, this.yCoord, this.zCoord, this);
		}

	}

	public Packet getDescriptionPacket() {
		return null;
	}

	public boolean isInvalid() {
		return this.tileEntityInvalid;
	}

	public void invalidate() {
		this.tileEntityInvalid = true;
	}

	public void validate() {
		this.tileEntityInvalid = false;
	}

	public void func_35163_b(int i1, int i2) {
	}

	public void func_35164_g() {
		this.field_35166_o = null;
		this.field_35165_n = -1;
	}

	static {
		addMapping(TileEntityFurnace.class, "Furnace");
		addMapping(TileEntityChest.class, "Chest");
		addMapping(TileEntityRecordPlayer.class, "RecordPlayer");
		addMapping(TileEntityDispenser.class, "Trap");
		addMapping(TileEntitySign.class, "Sign");
		addMapping(TileEntityMobSpawner.class, "MobSpawner");
		addMapping(TileEntityNote.class, "Music");
		addMapping(TileEntityPiston.class, "Piston");
	}
}
