package paulscode.sound;

public class ListenerData {
	public Vector3D position;
	public Vector3D lookAt;
	public Vector3D up;
	public float angle = 0.0F;

	public ListenerData() {
		this.position = new Vector3D(0.0F, 0.0F, 0.0F);
		this.lookAt = new Vector3D(0.0F, 0.0F, -1.0F);
		this.up = new Vector3D(0.0F, 1.0F, 0.0F);
		this.angle = 0.0F;
	}

	public ListenerData(float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
		this.position = new Vector3D(f1, f2, f3);
		this.lookAt = new Vector3D(f4, f5, f6);
		this.up = new Vector3D(f7, f8, f9);
		this.angle = f10;
	}

	public ListenerData(Vector3D vector3D1, Vector3D vector3D2, Vector3D vector3D3, float f4) {
		this.position = vector3D1.clone();
		this.lookAt = vector3D2.clone();
		this.up = vector3D3.clone();
		this.angle = f4;
	}

	public void setData(float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
		this.position.x = f1;
		this.position.y = f2;
		this.position.z = f3;
		this.lookAt.x = f4;
		this.lookAt.y = f5;
		this.lookAt.z = f6;
		this.up.x = f7;
		this.up.y = f8;
		this.up.z = f9;
		this.angle = f10;
	}

	public void setData(Vector3D vector3D1, Vector3D vector3D2, Vector3D vector3D3, float f4) {
		this.position.x = vector3D1.x;
		this.position.y = vector3D1.y;
		this.position.z = vector3D1.z;
		this.lookAt.x = vector3D2.x;
		this.lookAt.y = vector3D2.y;
		this.lookAt.z = vector3D2.z;
		this.up.x = vector3D3.x;
		this.up.y = vector3D3.y;
		this.up.z = vector3D3.z;
		this.angle = f4;
	}

	public void setData(ListenerData listenerData1) {
		this.position.x = listenerData1.position.x;
		this.position.y = listenerData1.position.y;
		this.position.z = listenerData1.position.z;
		this.lookAt.x = listenerData1.lookAt.x;
		this.lookAt.y = listenerData1.lookAt.y;
		this.lookAt.z = listenerData1.lookAt.z;
		this.up.x = listenerData1.up.x;
		this.up.y = listenerData1.up.y;
		this.up.z = listenerData1.up.z;
		this.angle = listenerData1.angle;
	}

	public void setPosition(float f1, float f2, float f3) {
		this.position.x = f1;
		this.position.y = f2;
		this.position.z = f3;
	}

	public void setPosition(Vector3D vector3D1) {
		this.position.x = vector3D1.x;
		this.position.y = vector3D1.y;
		this.position.z = vector3D1.z;
	}

	public void setOrientation(float f1, float f2, float f3, float f4, float f5, float f6) {
		this.lookAt.x = f1;
		this.lookAt.y = f2;
		this.lookAt.z = f3;
		this.up.x = f4;
		this.up.y = f5;
		this.up.z = f6;
	}

	public void setOrientation(Vector3D vector3D1, Vector3D vector3D2) {
		this.lookAt.x = vector3D1.x;
		this.lookAt.y = vector3D1.y;
		this.lookAt.z = vector3D1.z;
		this.up.x = vector3D2.x;
		this.up.y = vector3D2.y;
		this.up.z = vector3D2.z;
	}

	public void setAngle(float f1) {
		this.angle = f1;
		this.lookAt.x = -1.0F * (float)Math.sin((double)this.angle);
		this.lookAt.z = -1.0F * (float)Math.cos((double)this.angle);
	}
}
