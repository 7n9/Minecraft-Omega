package paulscode.sound.libraries;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.LinkedList;
import javax.sound.sampled.AudioFormat;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;

import paulscode.sound.Channel;
import paulscode.sound.FilenameURL;
import paulscode.sound.SoundBuffer;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.Source;

public class SourceLWJGLOpenAL extends Source {
	private ChannelLWJGLOpenAL channelOpenAL = (ChannelLWJGLOpenAL)this.channel;
	private IntBuffer myBuffer;
	private FloatBuffer listenerPosition;
	private FloatBuffer sourcePosition;
	private FloatBuffer sourceVelocity;

	public SourceLWJGLOpenAL(FloatBuffer floatBuffer1, IntBuffer intBuffer2, boolean z3, boolean z4, boolean z5, String string6, FilenameURL filenameURL7, SoundBuffer soundBuffer8, float f9, float f10, float f11, int i12, float f13, boolean z14) {
		super(z3, z4, z5, string6, filenameURL7, soundBuffer8, f9, f10, f11, i12, f13, z14);
		this.reverseByteOrder = true;
		if(this.codec != null) {
			this.codec.reverseByteOrder(true);
		}

		this.listenerPosition = floatBuffer1;
		this.myBuffer = intBuffer2;
		this.libraryType = LibraryLWJGLOpenAL.class;
		this.pitch = 1.0F;
		this.resetALInformation();
	}

	public SourceLWJGLOpenAL(FloatBuffer floatBuffer1, IntBuffer intBuffer2, Source source3, SoundBuffer soundBuffer4) {
		super(source3, soundBuffer4);
		this.reverseByteOrder = true;
		if(this.codec != null) {
			this.codec.reverseByteOrder(true);
		}

		this.listenerPosition = floatBuffer1;
		this.myBuffer = intBuffer2;
		this.libraryType = LibraryLWJGLOpenAL.class;
		this.pitch = 1.0F;
		this.resetALInformation();
	}

	public SourceLWJGLOpenAL(FloatBuffer floatBuffer1, AudioFormat audioFormat2, boolean z3, String string4, float f5, float f6, float f7, int i8, float f9) {
		super(audioFormat2, z3, string4, f5, f6, f7, i8, f9);
		this.reverseByteOrder = true;
		this.listenerPosition = floatBuffer1;
		this.libraryType = LibraryLWJGLOpenAL.class;
		this.pitch = 1.0F;
		this.resetALInformation();
	}

	public void cleanup() {
		super.cleanup();
	}

	public void changeSource(FloatBuffer floatBuffer1, IntBuffer intBuffer2, boolean z3, boolean z4, boolean z5, String string6, FilenameURL filenameURL7, SoundBuffer soundBuffer8, float f9, float f10, float f11, int i12, float f13, boolean z14) {
		super.changeSource(z3, z4, z5, string6, filenameURL7, soundBuffer8, f9, f10, f11, i12, f13, z14);
		this.reverseByteOrder = true;
		this.listenerPosition = floatBuffer1;
		this.myBuffer = intBuffer2;
		this.pitch = 1.0F;
		this.resetALInformation();
	}

	public boolean incrementSoundSequence() {
		if(!this.toStream) {
			this.errorMessage("Method \'incrementSoundSequence\' may only be used for streaming sources.");
			return false;
		} else {
			Object object1 = this.soundSequenceLock;
			synchronized(this.soundSequenceLock) {
				if(this.soundSequenceQueue != null && this.soundSequenceQueue.size() > 0) {
					this.filenameURL = (FilenameURL)this.soundSequenceQueue.remove(0);
					if(this.codec != null) {
						this.codec.cleanup();
					}

					this.codec = SoundSystemConfig.getCodec(this.filenameURL.getFilename());
					if(this.codec != null) {
						this.codec.reverseByteOrder(true);
						if(this.codec.getAudioFormat() == null) {
							this.codec.initialize(this.filenameURL.getURL());
						}

						AudioFormat audioFormat2 = this.codec.getAudioFormat();
						if(audioFormat2 == null) {
							this.errorMessage("Audio Format null in method \'incrementSoundSequence\'");
							return false;
						}

						boolean z3 = false;
						short s6;
						if(audioFormat2.getChannels() == 1) {
							if(audioFormat2.getSampleSizeInBits() == 8) {
								s6 = 4352;
							} else {
								if(audioFormat2.getSampleSizeInBits() != 16) {
									this.errorMessage("Illegal sample size in method \'incrementSoundSequence\'");
									return false;
								}

								s6 = 4353;
							}
						} else {
							if(audioFormat2.getChannels() != 2) {
								this.errorMessage("Audio data neither mono nor stereo in method \'incrementSoundSequence\'");
								return false;
							}

							if(audioFormat2.getSampleSizeInBits() == 8) {
								s6 = 4354;
							} else {
								if(audioFormat2.getSampleSizeInBits() != 16) {
									this.errorMessage("Illegal sample size in method \'incrementSoundSequence\'");
									return false;
								}

								s6 = 4355;
							}
						}

						this.channelOpenAL.setFormat(s6, (int)audioFormat2.getSampleRate());
						this.preLoad = true;
					}

					return true;
				} else {
					return false;
				}
			}
		}
	}

	public void listenerMoved() {
		this.positionChanged();
	}

	public void setPosition(float f1, float f2, float f3) {
		super.setPosition(f1, f2, f3);
		if(this.sourcePosition == null) {
			this.resetALInformation();
		} else {
			this.positionChanged();
		}

		this.sourcePosition.put(0, f1);
		this.sourcePosition.put(1, f2);
		this.sourcePosition.put(2, f3);
		if(this.channel != null && this.channel.attachedSource == this && this.channelOpenAL != null && this.channelOpenAL.ALSource != null) {
			AL10.alSource(this.channelOpenAL.ALSource.get(0), 4100, this.sourcePosition);
			this.checkALError();
		}

	}

	public void positionChanged() {
		this.calculateDistance();
		this.calculateGain();
		if(this.channel != null && this.channel.attachedSource == this && this.channelOpenAL != null && this.channelOpenAL.ALSource != null) {
			AL10.alSourcef(this.channelOpenAL.ALSource.get(0), 4106, this.gain * this.sourceVolume * Math.abs(this.fadeOutGain) * this.fadeInGain);
			this.checkALError();
		}

		this.checkPitch();
	}

	private void checkPitch() {
		if(this.channel != null && this.channel.attachedSource == this && LibraryLWJGLOpenAL.alPitchSupported() && this.channelOpenAL != null && this.channelOpenAL.ALSource != null) {
			AL10.alSourcef(this.channelOpenAL.ALSource.get(0), 4099, this.pitch);
			this.checkALError();
		}

	}

	public void setLooping(boolean z1) {
		super.setLooping(z1);
		if(this.channel != null && this.channel.attachedSource == this && this.channelOpenAL != null && this.channelOpenAL.ALSource != null) {
			if(z1) {
				AL10.alSourcei(this.channelOpenAL.ALSource.get(0), 4103, 1);
			} else {
				AL10.alSourcei(this.channelOpenAL.ALSource.get(0), 4103, 0);
			}

			this.checkALError();
		}

	}

	public void setAttenuation(int i1) {
		super.setAttenuation(i1);
		if(this.channel != null && this.channel.attachedSource == this && this.channelOpenAL != null && this.channelOpenAL.ALSource != null) {
			if(i1 == 1) {
				AL10.alSourcef(this.channelOpenAL.ALSource.get(0), 4129, this.distOrRoll);
			} else {
				AL10.alSourcef(this.channelOpenAL.ALSource.get(0), 4129, 0.0F);
			}

			this.checkALError();
		}

	}

	public void setDistOrRoll(float f1) {
		super.setDistOrRoll(f1);
		if(this.channel != null && this.channel.attachedSource == this && this.channelOpenAL != null && this.channelOpenAL.ALSource != null) {
			if(this.attModel == 1) {
				AL10.alSourcef(this.channelOpenAL.ALSource.get(0), 4129, f1);
			} else {
				AL10.alSourcef(this.channelOpenAL.ALSource.get(0), 4129, 0.0F);
			}

			this.checkALError();
		}

	}

	public void setPitch(float f1) {
		super.setPitch(f1);
		this.checkPitch();
	}

	public void play(Channel channel1) {
		if(!this.active()) {
			if(this.toLoop) {
				this.toPlay = true;
			}

		} else if(channel1 == null) {
			this.errorMessage("Unable to play source, because channel was null");
		} else {
			boolean z2 = this.channel != channel1;
			if(this.channel != null && this.channel.attachedSource != this) {
				z2 = true;
			}

			boolean z3 = this.paused();
			super.play(channel1);
			this.channelOpenAL = (ChannelLWJGLOpenAL)this.channel;
			if(z2) {
				this.setPosition(this.position.x, this.position.y, this.position.z);
				this.checkPitch();
				if(this.channelOpenAL != null && this.channelOpenAL.ALSource != null) {
					if(LibraryLWJGLOpenAL.alPitchSupported()) {
						AL10.alSourcef(this.channelOpenAL.ALSource.get(0), 4099, this.pitch);
						this.checkALError();
					}

					AL10.alSource(this.channelOpenAL.ALSource.get(0), 4100, this.sourcePosition);
					this.checkALError();
					AL10.alSource(this.channelOpenAL.ALSource.get(0), 4102, this.sourceVelocity);
					this.checkALError();
					if(this.attModel == 1) {
						AL10.alSourcef(this.channelOpenAL.ALSource.get(0), 4129, this.distOrRoll);
					} else {
						AL10.alSourcef(this.channelOpenAL.ALSource.get(0), 4129, 0.0F);
					}

					this.checkALError();
					if(this.toLoop && !this.toStream) {
						AL10.alSourcei(this.channelOpenAL.ALSource.get(0), 4103, 1);
					} else {
						AL10.alSourcei(this.channelOpenAL.ALSource.get(0), 4103, 0);
					}

					this.checkALError();
				}

				if(!this.toStream) {
					if(this.myBuffer == null) {
						this.errorMessage("No sound buffer to play");
						return;
					}

					this.channelOpenAL.attachBuffer(this.myBuffer);
				}
			}

			if(!this.playing()) {
				if(this.toStream && !z3) {
					if(this.codec == null) {
						this.errorMessage("Decoder null in method \'play\'");
						return;
					}

					if(this.codec.getAudioFormat() == null) {
						this.codec.initialize(this.filenameURL.getURL());
					}

					AudioFormat audioFormat4 = this.codec.getAudioFormat();
					if(audioFormat4 == null) {
						this.errorMessage("Audio Format null in method \'play\'");
						return;
					}

					boolean z5 = false;
					short s6;
					if(audioFormat4.getChannels() == 1) {
						if(audioFormat4.getSampleSizeInBits() == 8) {
							s6 = 4352;
						} else {
							if(audioFormat4.getSampleSizeInBits() != 16) {
								this.errorMessage("Illegal sample size in method \'play\'");
								return;
							}

							s6 = 4353;
						}
					} else {
						if(audioFormat4.getChannels() != 2) {
							this.errorMessage("Audio data neither mono nor stereo in method \'play\'");
							return;
						}

						if(audioFormat4.getSampleSizeInBits() == 8) {
							s6 = 4354;
						} else {
							if(audioFormat4.getSampleSizeInBits() != 16) {
								this.errorMessage("Illegal sample size in method \'play\'");
								return;
							}

							s6 = 4355;
						}
					}

					this.channelOpenAL.setFormat(s6, (int)audioFormat4.getSampleRate());
					this.preLoad = true;
				}

				this.channel.play();
				if(this.pitch != 1.0F) {
					this.checkPitch();
				}
			}

		}
	}

	public boolean preLoad() {
		if(this.codec == null) {
			return false;
		} else {
			this.codec.initialize(this.filenameURL.getURL());
			LinkedList linkedList1 = new LinkedList();

			for(int i2 = 0; i2 < SoundSystemConfig.getNumberStreamingBuffers(); ++i2) {
				this.soundBuffer = this.codec.read();
				if(this.soundBuffer == null || this.soundBuffer.audioData == null) {
					break;
				}

				linkedList1.add(this.soundBuffer.audioData);
			}

			this.positionChanged();
			this.channel.preLoadBuffers(linkedList1);
			this.preLoad = false;
			return true;
		}
	}

	private void resetALInformation() {
		this.sourcePosition = BufferUtils.createFloatBuffer(3).put(new float[]{this.position.x, this.position.y, this.position.z});
		this.sourceVelocity = BufferUtils.createFloatBuffer(3).put(new float[]{0.0F, 0.0F, 0.0F});
		this.sourcePosition.flip();
		this.sourceVelocity.flip();
		this.positionChanged();
	}

	private void calculateDistance() {
		if(this.listenerPosition != null) {
			double d1 = (double)(this.position.x - this.listenerPosition.get(0));
			double d3 = (double)(this.position.y - this.listenerPosition.get(1));
			double d5 = (double)(this.position.z - this.listenerPosition.get(2));
			this.distanceFromListener = (float)Math.sqrt(d1 * d1 + d3 * d3 + d5 * d5);
		}

	}

	private void calculateGain() {
		if(this.attModel == 2) {
			if(this.distanceFromListener <= 0.0F) {
				this.gain = 1.0F;
			} else if(this.distanceFromListener >= this.distOrRoll) {
				this.gain = 0.0F;
			} else {
				this.gain = 1.0F - this.distanceFromListener / this.distOrRoll;
			}

			if(this.gain > 1.0F) {
				this.gain = 1.0F;
			}

			if(this.gain < 0.0F) {
				this.gain = 0.0F;
			}
		} else {
			this.gain = 1.0F;
		}

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
