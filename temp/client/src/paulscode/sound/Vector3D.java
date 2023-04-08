package paulscode.sound;

public class Vector3D {
	public float x;
	public float y;
	public float z;

	public Vector3D() {
		this.x = 0.0F;
		this.y = 0.0F;
		this.z = 0.0F;
	}

	public Vector3D(float f1, float f2, float f3) {
		this.x = f1;
		this.y = f2;
		this.z = f3;
	}

	public Vector3D clone() {
		return new Vector3D(this.x, this.y, this.z);
	}

	public Vector3D cross(Vector3D vector3D1, Vector3D vector3D2) {
		return new Vector3D(vector3D1.y * vector3D2.z - vector3D2.y * vector3D1.z, vector3D1.z * vector3D2.x - vector3D2.z * vector3D1.x, vector3D1.x * vector3D2.y - vector3D2.x * vector3D1.y);
	}

	public Vector3D cross(Vector3D vector3D1) {
		return new Vector3D(this.y * vector3D1.z - vector3D1.y * this.z, this.z * vector3D1.x - vector3D1.z * this.x, this.x * vector3D1.y - vector3D1.x * this.y);
	}

	public float dot(Vector3D vector3D1, Vector3D vector3D2) {
		return vector3D1.x * vector3D2.x + vector3D1.y * vector3D2.y + vector3D1.z * vector3D2.z;
	}

	public float dot(Vector3D vector3D1) {
		return this.x * vector3D1.x + this.y * vector3D1.y + this.z * vector3D1.z;
	}

	public Vector3D add(Vector3D vector3D1, Vector3D vector3D2) {
		return new Vector3D(vector3D1.x + vector3D2.x, vector3D1.y + vector3D2.y, vector3D1.z + vector3D2.z);
	}

	public Vector3D add(Vector3D vector3D1) {
		return new Vector3D(this.x + vector3D1.x, this.y + vector3D1.y, this.z + vector3D1.z);
	}

	public Vector3D subtract(Vector3D vector3D1, Vector3D vector3D2) {
		return new Vector3D(vector3D1.x - vector3D2.x, vector3D1.y - vector3D2.y, vector3D1.z - vector3D2.z);
	}

	public Vector3D subtract(Vector3D vector3D1) {
		return new Vector3D(this.x - vector3D1.x, this.y - vector3D1.y, this.z - vector3D1.z);
	}

	public void normalize() {
		double d1 = Math.sqrt((double)(this.x * this.x + this.y * this.y + this.z * this.z));
		this.x = (float)((double)this.x / d1);
		this.y = (float)((double)this.y / d1);
		this.z = (float)((double)this.z / d1);
	}

	public Object clone() {
		return this.clone();
	}
}
