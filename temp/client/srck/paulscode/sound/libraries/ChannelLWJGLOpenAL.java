package paulscode.sound.libraries;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.LinkedList;
import javax.sound.sampled.AudioFormat;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;

import paulscode.sound.Channel;

public class ChannelLWJGLOpenAL extends Channel {
	public IntBuffer ALSource;
	public int ALformat;
	public int sampleRate;
	ByteBuffer bufferBuffer = BufferUtils.createByteBuffer(5242880);

	public ChannelLWJGLOpenAL(int i1, IntBuffer intBuffer2) {
		super(i1);
		this.libraryType = LibraryLWJGLOpenAL.class;
		this.ALSource = intBuffer2;
	}

	public void cleanup() {
		if(this.ALSource != null) {
			try {
				AL10.alSourceStop(this.ALSource);
				AL10.alGetError();
			} catch (Exception exception3) {
			}

			try {
				AL10.alDeleteSources(this.ALSource);
				AL10.alGetError();
			} catch (Exception exception2) {
			}

			this.ALSource.clear();
		}

		this.ALSource = null;
		super.cleanup();
	}

	public boolean attachBuffer(IntBuffer intBuffer1) {
		if(this.errorCheck(this.channelType != 0, "Sound buffers may only be attached to normal sources.")) {
			return false;
		} else {
			AL10.alSourcei(this.ALSource.get(0), 4105, intBuffer1.get(0));
			return this.checkALError();
		}
	}

	public void setAudioFormat(AudioFormat audioFormat1) {
		boolean z2 = false;
		short s3;
		if(audioFormat1.getChannels() == 1) {
			if(audioFormat1.getSampleSizeInBits() == 8) {
				s3 = 4352;
			} else {
				if(audioFormat1.getSampleSizeInBits() != 16) {
					this.errorMessage("Illegal sample size in method \'setAudioFormat\'");
					return;
				}

				s3 = 4353;
			}
		} else {
			if(audioFormat1.getChannels() != 2) {
				this.errorMessage("Audio data neither mono nor stereo in method \'setAudioFormat\'");
				return;
			}

			if(audioFormat1.getSampleSizeInBits() == 8) {
				s3 = 4354;
			} else {
				if(audioFormat1.getSampleSizeInBits() != 16) {
					this.errorMessage("Illegal sample size in method \'setAudioFormat\'");
					return;
				}

				s3 = 4355;
			}
		}

		this.ALformat = s3;
		this.sampleRate = (int)audioFormat1.getSampleRate();
	}

	public void setFormat(int i1, int i2) {
		this.ALformat = i1;
		this.sampleRate = i2;
	}

	public boolean preLoadBuffers(LinkedList linkedList1) {
		if(this.errorCheck(this.channelType != 1, "Buffers may only be queued for streaming sources.")) {
			return false;
		} else if(this.errorCheck(linkedList1 == null, "Buffer List null in method \'preLoadBuffers\'")) {
			return false;
		} else {
			boolean z3 = this.playing();
			if(z3) {
				AL10.alSourceStop(this.ALSource.get(0));
				this.checkALError();
			}

			int i4 = AL10.alGetSourcei(this.ALSource.get(0), 4118);
			IntBuffer intBuffer2;
			if(i4 > 0) {
				intBuffer2 = BufferUtils.createIntBuffer(i4);
				AL10.alGenBuffers(intBuffer2);
				if(this.errorCheck(this.checkALError(), "Error clearing stream buffers in method \'preLoadBuffers\'")) {
					return false;
				}

				AL10.alSourceUnqueueBuffers(this.ALSource.get(0), intBuffer2);
				if(this.errorCheck(this.checkALError(), "Error unqueuing stream buffers in method \'preLoadBuffers\'")) {
					return false;
				}
			}

			if(z3) {
				AL10.alSourcePlay(this.ALSource.get(0));
				this.checkALError();
			}

			intBuffer2 = BufferUtils.createIntBuffer(linkedList1.size());
			AL10.alGenBuffers(intBuffer2);
			if(this.errorCheck(this.checkALError(), "Error generating stream buffers in method \'preLoadBuffers\'")) {
				return false;
			} else {
				for(int i5 = 0; i5 < linkedList1.size(); ++i5) {
					this.bufferBuffer.clear();
					this.bufferBuffer.put((byte[])linkedList1.get(i5), 0, ((byte[])linkedList1.get(i5)).length);
					this.bufferBuffer.flip();

					try {
						AL10.alBufferData(intBuffer2.get(i5), this.ALformat, this.bufferBuffer, this.sampleRate);
					} catch (Exception exception8) {
						this.errorMessage("Error creating buffers in method \'preLoadBuffers\'");
						this.printStackTrace(exception8);
						return false;
					}

					if(this.errorCheck(this.checkALError(), "Error creating buffers in method \'preLoadBuffers\'")) {
						return false;
					}
				}

				try {
					AL10.alSourceQueueBuffers(this.ALSource.get(0), intBuffer2);
				} catch (Exception exception7) {
					this.errorMessage("Error queuing buffers in method \'preLoadBuffers\'");
					this.printStackTrace(exception7);
					return false;
				}

				if(this.errorCheck(this.checkALError(), "Error queuing buffers in method \'preLoadBuffers\'")) {
					return false;
				} else {
					AL10.alSourcePlay(this.ALSource.get(0));
					return !this.errorCheck(this.checkALError(), "Error playing source in method \'preLoadBuffers\'");
				}
			}
		}
	}

	public boolean queueBuffer(byte[] b1) {
		if(this.errorCheck(this.channelType != 1, "Buffers may only be queued for streaming sources.")) {
			return false;
		} else {
			this.bufferBuffer.clear();
			this.bufferBuffer.put(b1, 0, b1.length);
			this.bufferBuffer.flip();
			IntBuffer intBuffer2 = BufferUtils.createIntBuffer(1);
			AL10.alSourceUnqueueBuffers(this.ALSource.get(0), intBuffer2);
			if(this.checkALError()) {
				return false;
			} else {
				AL10.alBufferData(intBuffer2.get(0), this.ALformat, this.bufferBuffer, this.sampleRate);
				if(this.checkALError()) {
					return false;
				} else {
					AL10.alSourceQueueBuffers(this.ALSource.get(0), intBuffer2);
					return !this.checkALError();
				}
			}
		}
	}

	public int feedRawAudioData(byte[] b1) {
		if(this.errorCheck(this.channelType != 1, "Raw audio data can only be fed to streaming sources.")) {
			return -1;
		} else {
			ByteBuffer byteBuffer2 = ByteBuffer.wrap(b1, 0, b1.length);
			int i4 = AL10.alGetSourcei(this.ALSource.get(0), 4118);
			IntBuffer intBuffer3;
			if(i4 > 0) {
				intBuffer3 = BufferUtils.createIntBuffer(i4);
				AL10.alGenBuffers(intBuffer3);
				if(this.errorCheck(this.checkALError(), "Error clearing stream buffers in method \'feedRawAudioData\'")) {
					return -1;
				}

				AL10.alSourceUnqueueBuffers(this.ALSource.get(0), intBuffer3);
				if(this.errorCheck(this.checkALError(), "Error unqueuing stream buffers in method \'feedRawAudioData\'")) {
					return -1;
				}
			} else {
				intBuffer3 = BufferUtils.createIntBuffer(1);
				AL10.alGenBuffers(intBuffer3);
				if(this.errorCheck(this.checkALError(), "Error generating stream buffers in method \'preLoadBuffers\'")) {
					return -1;
				}
			}

			AL10.alBufferData(intBuffer3.get(0), this.ALformat, byteBuffer2, this.sampleRate);
			if(this.checkALError()) {
				return -1;
			} else {
				AL10.alSourceQueueBuffers(this.ALSource.get(0), intBuffer3);
				if(this.checkALError()) {
					return -1;
				} else {
					if(this.attachedSource != null && this.attachedSource.channel == this && this.attachedSource.active() && !this.playing()) {
						AL10.alSourcePlay(this.ALSource.get(0));
						this.checkALError();
					}

					return i4;
				}
			}
		}
	}

	public int buffersProcessed() {
		if(this.channelType != 1) {
			return 0;
		} else {
			int i1 = AL10.alGetSourcei(this.ALSource.get(0), 4118);
			return this.checkALError() ? 0 : i1;
		}
	}

	public void flush() {
		if(this.channelType == 1) {
			int i1 = AL10.alGetSourcei(this.ALSource.get(0), 4117);
			if(!this.checkALError()) {
				for(IntBuffer intBuffer2 = BufferUtils.createIntBuffer(1); i1 > 0; --i1) {
					try {
						AL10.alSourceUnqueueBuffers(this.ALSource.get(0), intBuffer2);
					} catch (Exception exception4) {
						return;
					}

					if(this.checkALError()) {
						return;
					}
				}

			}
		}
	}

	public void close() {
		try {
			AL10.alSourceStop(this.ALSource.get(0));
			AL10.alGetError();
		} catch (Exception exception2) {
		}

		if(this.channelType == 1) {
			this.flush();
		}

	}

	public void play() {
		AL10.alSourcePlay(this.ALSource.get(0));
		this.checkALError();
	}

	public void pause() {
		AL10.alSourcePause(this.ALSource.get(0));
		this.checkALError();
	}

	public void stop() {
		AL10.alSourceStop(this.ALSource.get(0));
		this.checkALError();
	}

	public void rewind() {
		if(this.channelType != 1) {
			AL10.alSourceRewind(this.ALSource.get(0));
			this.checkALError();
		}
	}

	public boolean playing() {
		int i1 = AL10.alGetSourcei(this.ALSource.get(0), 4112);
		return this.checkALError() ? false : i1 == 4114;
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
}
