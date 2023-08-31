package net.minecraft.src;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

public class GLAllocation {
	private static final List displayLists = new ArrayList();
	private static final List textureNames = new ArrayList();

	public static synchronized int generateDisplayLists(int i0) {
		int i1 = GL11.glGenLists(i0);
		displayLists.add(i1);
		displayLists.add(i0);
		return i1;
	}

	public static synchronized void generateTextureNames(IntBuffer intBuffer0) {
		GL11.glGenTextures(intBuffer0);

		for(int i1 = intBuffer0.position(); i1 < intBuffer0.limit(); ++i1) {
			textureNames.add(intBuffer0.get(i1));
		}

	}

	public static synchronized void func_28194_b(int i0) {
		int i1 = displayLists.indexOf(i0);
		GL11.glDeleteLists((Integer) displayLists.get(i1), (Integer) displayLists.get(i1 + 1));
		displayLists.remove(i1);
		displayLists.remove(i1);
	}

	public static synchronized void deleteTexturesAndDisplayLists() {
		for(int i0 = 0; i0 < displayLists.size(); i0 += 2) {
			GL11.glDeleteLists((Integer) displayLists.get(i0), (Integer) displayLists.get(i0 + 1));
		}

		IntBuffer intBuffer2 = createDirectIntBuffer(textureNames.size());
		intBuffer2.flip();
		GL11.glDeleteTextures(intBuffer2);

		for (Object textureName : textureNames) {
			intBuffer2.put((Integer) textureName);
		}

		intBuffer2.flip();
		GL11.glDeleteTextures(intBuffer2);
		displayLists.clear();
		textureNames.clear();
	}

	public static synchronized ByteBuffer createDirectByteBuffer(int i0) {
		return ByteBuffer.allocateDirect(i0).order(ByteOrder.nativeOrder());
	}

	public static IntBuffer createDirectIntBuffer(int i0) {
		return createDirectByteBuffer(i0 << 2).asIntBuffer();
	}

	public static FloatBuffer createDirectFloatBuffer(int i0) {
		return createDirectByteBuffer(i0 << 2).asFloatBuffer();
	}
}
