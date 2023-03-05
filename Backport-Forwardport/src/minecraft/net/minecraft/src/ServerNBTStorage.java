package net.minecraft.src;

public class ServerNBTStorage {
	public String field_35795_a;
	public String field_35793_b;
	public String field_35794_c;
	public String field_35791_d;
	public long field_35792_e;
	public boolean field_35790_f = false;

	public ServerNBTStorage(String string1, String string2) {
		this.field_35795_a = string1;
		this.field_35793_b = string2;
	}

	public NBTTagCompound func_35789_a() {
		NBTTagCompound nBTTagCompound1 = new NBTTagCompound();
		nBTTagCompound1.setString("name", this.field_35795_a);
		nBTTagCompound1.setString("ip", this.field_35793_b);
		return nBTTagCompound1;
	}

	public static ServerNBTStorage func_35788_a(NBTTagCompound nBTTagCompound0) {
		return new ServerNBTStorage(nBTTagCompound0.getString("name"), nBTTagCompound0.getString("ip"));
	}
}
