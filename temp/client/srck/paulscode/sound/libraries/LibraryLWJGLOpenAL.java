package paulscode.sound.libraries;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.sound.sampled.AudioFormat;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;

import paulscode.sound.Channel;
import paulscode.sound.FilenameURL;
import paulscode.sound.ICodec;
import paulscode.sound.Library;
import paulscode.sound.ListenerData;
import paulscode.sound.SoundBuffer;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException;
import paulscode.sound.Source;

public class LibraryLWJGLOpenAL extends Library {
	private static final boolean GET = false;
	private static final boolean SET = true;
	private static final boolean XXX = false;
	private FloatBuffer listenerPositionAL = null;
	private FloatBuffer listenerOrientation = null;
	private FloatBuffer listenerVelocity = null;
	private HashMap ALBufferMap = null;
	private static boolean alPitchSupported = true;

	public LibraryLWJGLOpenAL() throws SoundSystemException {
		this.ALBufferMap = new HashMap();
	}

	public void init() throws SoundSystemException {
		boolean z1 = false;

		try {
			AL.create();
			z1 = this.checkALError();
		} catch (LWJGLException lWJGLException5) {
			this.errorMessage("Unable to initialize OpenAL.  Probable cause: OpenAL not supported.");
			this.printStackTrace(lWJGLException5);
			throw new SoundSystemException(lWJGLException5.getMessage(), 6);
		}

		if(z1) {
			this.importantMessage("OpenAL did not initialize properly!");
		} else {
			this.message("OpenAL initialized.");
		}

		this.listenerPositionAL = BufferUtils.createFloatBuffer(3).put(new float[]{this.listener.position.x, this.listener.position.y, this.listener.position.z});
		this.listenerOrientation = BufferUtils.createFloatBuffer(6).put(new float[]{this.listener.lookAt.x, this.listener.lookAt.y, this.listener.lookAt.z, this.listener.up.x, this.listener.up.y, this.listener.up.z});
		this.listenerVelocity = BufferUtils.createFloatBuffer(3).put(new float[]{0.0F, 0.0F, 0.0F});
		this.listenerPositionAL.flip();
		this.listenerOrientation.flip();
		this.listenerVelocity.flip();
		AL10.alListener(4100, this.listenerPositionAL);
		z1 = this.checkALError() || z1;
		AL10.alListener(4111, this.listenerOrientation);
		z1 = this.checkALError() || z1;
		AL10.alListener(4102, this.listenerVelocity);
		z1 = this.checkALError() || z1;
		if(z1) {
			this.importantMessage("OpenAL did not initialize properly!");
			throw new SoundSystemException("Problem encountered while loading OpenAL or creating the listener.  Probably cause:  OpenAL not supported", 6);
		} else {
			super.init();
			ChannelLWJGLOpenAL channelLWJGLOpenAL2 = (ChannelLWJGLOpenAL)this.normalChannels.get(1);

			try {
				AL10.alSourcef(channelLWJGLOpenAL2.ALSource.get(0), 4099, 1.0F);
				if(this.checkALError()) {
					alPitchSupported(true, false);
					throw new SoundSystemException("OpenAL: AL_PITCH not supported.", 13);
				} else {
					alPitchSupported(true, true);
				}
			} catch (Exception exception4) {
				alPitchSupported(true, false);
				throw new SoundSystemException("OpenAL: AL_PITCH not supported.", 13);
			}
		}
	}

	public static boolean libraryCompatible() {
		if(AL.isCreated()) {
			return true;
		} else {
			try {
				AL.create();
			} catch (Exception exception2) {
				return false;
			}

			try {
				AL.destroy();
			} catch (Exception exception1) {
			}

			return true;
		}
	}

	protected Channel createChannel(int i1) {
		IntBuffer intBuffer3 = BufferUtils.createIntBuffer(1);

		try {
			AL10.alGenSources(intBuffer3);
		} catch (Exception exception5) {
			AL10.alGetError();
			return null;
		}

		if(AL10.alGetError() != 0) {
			return null;
		} else {
			ChannelLWJGLOpenAL channelLWJGLOpenAL2 = new ChannelLWJGLOpenAL(i1, intBuffer3);
			return channelLWJGLOpenAL2;
		}
	}

	public void cleanup() {
		super.cleanup();
		Set set1 = this.bufferMap.keySet();
		Iterator iterator2 = set1.iterator();

		while(iterator2.hasNext()) {
			String string3 = (String)iterator2.next();
			IntBuffer intBuffer4 = (IntBuffer)this.ALBufferMap.get(string3);
			if(intBuffer4 != null) {
				AL10.alDeleteBuffers(intBuffer4);
				this.checkALError();
				intBuffer4.clear();
			}
		}

		this.bufferMap.clear();
		AL.destroy();
		this.bufferMap = null;
		this.listenerPositionAL = null;
		this.listenerOrientation = null;
		this.listenerVelocity = null;
	}

	public boolean loadSound(FilenameURL filenameURL1) {
		if(this.bufferMap == null) {
			this.bufferMap = new HashMap();
			this.importantMessage("Buffer Map was null in method \'loadSound\'");
		}

		if(this.ALBufferMap == null) {
			this.ALBufferMap = new HashMap();
			this.importantMessage("Open AL Buffer Map was null in method\'loadSound\'");
		}

		if(this.errorCheck(filenameURL1 == null, "Filename/URL not specified in method \'loadSound\'")) {
			return false;
		} else if(this.bufferMap.get(filenameURL1.getFilename()) != null) {
			return true;
		} else {
			ICodec iCodec2 = SoundSystemConfig.getCodec(filenameURL1.getFilename());
			if(this.errorCheck(iCodec2 == null, "No codec found for file \'" + filenameURL1.getFilename() + "\' in method \'loadSound\'")) {
				return false;
			} else {
				iCodec2.initialize(filenameURL1.getURL());
				SoundBuffer soundBuffer3 = iCodec2.readAll();
				iCodec2.cleanup();
				iCodec2 = null;
				if(this.errorCheck(soundBuffer3 == null, "Sound buffer null in method \'loadSound\'")) {
					return false;
				} else {
					this.bufferMap.put(filenameURL1.getFilename(), soundBuffer3);
					AudioFormat audioFormat4 = soundBuffer3.audioFormat;
					boolean z5 = false;
					short s8;
					if(audioFormat4.getChannels() == 1) {
						if(audioFormat4.getSampleSizeInBits() == 8) {
							s8 = 4352;
						} else {
							if(audioFormat4.getSampleSizeInBits() != 16) {
								this.errorMessage("Illegal sample size in method \'loadSound\'");
								return false;
							}

							s8 = 4353;
						}
					} else {
						if(audioFormat4.getChannels() != 2) {
							this.errorMessage("File neither mono nor stereo in method \'loadSound\'");
							return false;
						}

						if(audioFormat4.getSampleSizeInBits() == 8) {
							s8 = 4354;
						} else {
							if(audioFormat4.getSampleSizeInBits() != 16) {
								this.errorMessage("Illegal sample size in method \'loadSound\'");
								return false;
							}

							s8 = 4355;
						}
					}

					IntBuffer intBuffer6 = BufferUtils.createIntBuffer(1);
					AL10.alGenBuffers(intBuffer6);
					if(this.errorCheck(AL10.alGetError() != 0, "alGenBuffers error when loading " + filenameURL1.getFilename())) {
						return false;
					} else {
						ByteBuffer byteBuffer7 = BufferUtils.createByteBuffer(soundBuffer3.audioData.length);
						byteBuffer7.clear();
						byteBuffer7.put(soundBuffer3.audioData);
						byteBuffer7.flip();
						AL10.alBufferData(intBuffer6.get(0), s8, byteBuffer7, (int)audioFormat4.getSampleRate());
						if(this.errorCheck(AL10.alGetError() != 0, "alBufferData error when loading " + filenameURL1.getFilename()) && this.errorCheck(intBuffer6 == null, "Sound buffer was not created for " + filenameURL1.getFilename())) {
							return false;
						} else {
							this.ALBufferMap.put(filenameURL1.getFilename(), intBuffer6);
							return true;
						}
					}
				}
			}
		}
	}

	public void unloadSound(String string1) {
		this.ALBufferMap.remove(string1);
		super.unloadSound(string1);
	}

	public void setMasterVolume(float f1) {
		super.setMasterVolume(f1);
		AL10.alListenerf(4106, f1);
		this.checkALError();
	}

	public void newSource(boolean z1, boolean z2, boolean z3, String string4, FilenameURL filenameURL5, float f6, float f7, float f8, int i9, float f10) {
		IntBuffer intBuffer11 = null;
		if(!z2) {
			intBuffer11 = (IntBuffer)this.ALBufferMap.get(filenameURL5.getFilename());
			if(intBuffer11 == null && !this.loadSound(filenameURL5)) {
				this.errorMessage("Source \'" + string4 + "\' was not created " + "because an error occurred while loading " + filenameURL5.getFilename());
				return;
			}

			intBuffer11 = (IntBuffer)this.ALBufferMap.get(filenameURL5.getFilename());
			if(intBuffer11 == null) {
				this.errorMessage("Source \'" + string4 + "\' was not created " + "because a sound buffer was not found for " + filenameURL5.getFilename());
				return;
			}
		}

		SoundBuffer soundBuffer12 = null;
		if(!z2) {
			soundBuffer12 = (SoundBuffer)this.bufferMap.get(filenameURL5.getFilename());
			if(soundBuffer12 == null && !this.loadSound(filenameURL5)) {
				this.errorMessage("Source \'" + string4 + "\' was not created " + "because an error occurred while loading " + filenameURL5.getFilename());
				return;
			}

			soundBuffer12 = (SoundBuffer)this.bufferMap.get(filenameURL5.getFilename());
			if(soundBuffer12 == null) {
				this.errorMessage("Source \'" + string4 + "\' was not created " + "because audio data was not found for " + filenameURL5.getFilename());
				return;
			}
		}

		this.sourceMap.put(string4, new SourceLWJGLOpenAL(this.listenerPositionAL, intBuffer11, z1, z2, z3, string4, filenameURL5, soundBuffer12, f6, f7, f8, i9, f10, false));
	}

	public void rawDataStream(AudioFormat audioFormat1, boolean z2, String string3, float f4, float f5, float f6, int i7, float f8) {
		this.sourceMap.put(string3, new SourceLWJGLOpenAL(this.listenerPositionAL, audioFormat1, z2, string3, f4, f5, f6, i7, f8));
	}

	public void quickPlay(boolean z1, boolean z2, boolean z3, String string4, FilenameURL filenameURL5, float f6, float f7, float f8, int i9, float f10, boolean z11) {
		IntBuffer intBuffer12 = null;
		if(!z2) {
			intBuffer12 = (IntBuffer)this.ALBufferMap.get(filenameURL5.getFilename());
			if(intBuffer12 == null) {
				this.loadSound(filenameURL5);
			}

			intBuffer12 = (IntBuffer)this.ALBufferMap.get(filenameURL5.getFilename());
			if(intBuffer12 == null) {
				this.errorMessage("Sound buffer was not created for " + filenameURL5.getFilename());
				return;
			}
		}

		SoundBuffer soundBuffer13 = null;
		if(!z2) {
			soundBuffer13 = (SoundBuffer)this.bufferMap.get(filenameURL5.getFilename());
			if(soundBuffer13 == null && !this.loadSound(filenameURL5)) {
				this.errorMessage("Source \'" + string4 + "\' was not created " + "because an error occurred while loading " + filenameURL5.getFilename());
				return;
			}

			soundBuffer13 = (SoundBuffer)this.bufferMap.get(filenameURL5.getFilename());
			if(soundBuffer13 == null) {
				this.errorMessage("Source \'" + string4 + "\' was not created " + "because audio data was not found for " + filenameURL5.getFilename());
				return;
			}
		}

		SourceLWJGLOpenAL sourceLWJGLOpenAL14 = new SourceLWJGLOpenAL(this.listenerPositionAL, intBuffer12, z1, z2, z3, string4, filenameURL5, soundBuffer13, f6, f7, f8, i9, f10, false);
		this.sourceMap.put(string4, sourceLWJGLOpenAL14);
		this.play(sourceLWJGLOpenAL14);
		if(z11) {
			sourceLWJGLOpenAL14.setTemporary(true);
		}

	}

	public void copySources(HashMap hashMap1) {
		if(hashMap1 != null) {
			Set set2 = hashMap1.keySet();
			Iterator iterator3 = set2.iterator();
			if(this.bufferMap == null) {
				this.bufferMap = new HashMap();
				this.importantMessage("Buffer Map was null in method \'copySources\'");
			}

			if(this.ALBufferMap == null) {
				this.ALBufferMap = new HashMap();
				this.importantMessage("Open AL Buffer Map was null in method\'copySources\'");
			}

			this.sourceMap.clear();

			while(true) {
				String string4;
				Source source5;
				SoundBuffer soundBuffer6;
				do {
					do {
						if(!iterator3.hasNext()) {
							return;
						}

						string4 = (String)iterator3.next();
						source5 = (Source)hashMap1.get(string4);
					} while(source5 == null);

					soundBuffer6 = null;
					if(!source5.toStream) {
						this.loadSound(source5.filenameURL);
						soundBuffer6 = (SoundBuffer)this.bufferMap.get(source5.filenameURL.getFilename());
					}
				} while(!source5.toStream && soundBuffer6 == null);

				this.sourceMap.put(string4, new SourceLWJGLOpenAL(this.listenerPositionAL, (IntBuffer)this.ALBufferMap.get(source5.filenameURL.getFilename()), source5, soundBuffer6));
			}
		}
	}

	public void setListenerPosition(float f1, float f2, float f3) {
		super.setListenerPosition(f1, f2, f3);
		this.listenerPositionAL.put(0, f1);
		this.listenerPositionAL.put(1, f2);
		this.listenerPositionAL.put(2, f3);
		AL10.alListener(4100, this.listenerPositionAL);
		this.checkALError();
	}

	public void setListenerAngle(float f1) {
		super.setListenerAngle(f1);
		this.listenerOrientation.put(0, this.listener.lookAt.x);
		this.listenerOrientation.put(2, this.listener.lookAt.z);
		AL10.alListener(4111, this.listenerOrientation);
		this.checkALError();
	}

	public void setListenerOrientation(float f1, float f2, float f3, float f4, float f5, float f6) {
		super.setListenerOrientation(f1, f2, f3, f4, f5, f6);
		this.listenerOrientation.put(0, f1);
		this.listenerOrientation.put(1, f2);
		this.listenerOrientation.put(2, f3);
		this.listenerOrientation.put(3, f4);
		this.listenerOrientation.put(4, f5);
		this.listenerOrientation.put(5, f6);
		AL10.alListener(4111, this.listenerOrientation);
		this.checkALError();
	}

	public void setListenerData(ListenerData listenerData1) {
		super.setListenerData(listenerData1);
		this.listenerPositionAL.put(0, listenerData1.position.x);
		this.listenerPositionAL.put(1, listenerData1.position.y);
		this.listenerPositionAL.put(2, listenerData1.position.z);
		AL10.alListener(4100, this.listenerPositionAL);
		this.listenerOrientation.put(0, listenerData1.lookAt.x);
		this.listenerOrientation.put(1, listenerData1.lookAt.y);
		this.listenerOrientation.put(2, listenerData1.lookAt.z);
		this.listenerOrientation.put(3, listenerData1.up.x);
		this.listenerOrientation.put(4, listenerData1.up.y);
		this.listenerOrientation.put(5, listenerData1.up.z);
		AL10.alListener(4111, this.listenerOrientation);
		this.checkALError();
	}

	private boolean checkALError() {
		switch(AL10.alGetError()) {
		case 0:
			return false;
		case 40961:
			this.errorMessage("Invalid name parameter.");
			return true;
		case 40962:
			this.errorMessage("Invalid parameter.");
			return true;
		case 40963:
			this.errorMessage("Invalid enumerated parameter value.");
			return true;
		case 40964:
			this.errorMessage("Illegal call.");
			return true;
		case 40965:
			this.errorMessage("Unable to allocate memory.");
			return true;
		default:
			this.errorMessage("An unrecognized error occurred.");
			return true;
		}
	}

	public static boolean alPitchSupported() {
		return alPitchSupported(false, false);
	}

	private static synchronized boolean alPitchSupported(boolean z0, boolean z1) {
		if(z0) {
			alPitchSupported = z1;
		}

		return alPitchSupported;
	}

	public static String getTitle() {
		return "LWJGL OpenAL";
	}

	public static String getDescription() {
		return "The LWJGL binding of OpenAL.  For more information, see http://www.lwjgl.org";
	}

	public String getClassName() {
		return "LibraryLWJGLOpenAL";
	}
}
