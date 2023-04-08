package paulscode.sound;

import java.net.URL;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.sound.sampled.AudioFormat;

public class Source {
	protected Class libraryType = Library.class;
	private static final boolean GET = false;
	private static final boolean SET = true;
	private static final boolean XXX = false;
	private SoundSystemLogger logger = SoundSystemConfig.getLogger();
	public boolean rawDataStream = false;
	public AudioFormat rawDataFormat = null;
	public boolean temporary = false;
	public boolean priority = false;
	public boolean toStream = false;
	public boolean toLoop = false;
	public boolean toPlay = false;
	public String sourcename = "";
	public FilenameURL filenameURL = null;
	public Vector3D position;
	public int attModel = 0;
	public float distOrRoll = 0.0F;
	public float gain = 1.0F;
	public float sourceVolume = 1.0F;
	protected float pitch = 1.0F;
	public float distanceFromListener = 0.0F;
	public Channel channel = null;
	private boolean active = true;
	private boolean stopped = true;
	private boolean paused = false;
	protected SoundBuffer soundBuffer = null;
	protected ICodec codec = null;
	protected boolean reverseByteOrder = false;
	protected LinkedList soundSequenceQueue = null;
	protected final Object soundSequenceLock = new Object();
	public boolean preLoad = false;
	protected float fadeOutGain = -1.0F;
	protected float fadeInGain = 1.0F;
	protected long fadeOutMilis = 0L;
	protected long fadeInMilis = 0L;
	protected long lastFadeCheck = 0L;

	public Source(boolean z1, boolean z2, boolean z3, String string4, FilenameURL filenameURL5, SoundBuffer soundBuffer6, float f7, float f8, float f9, int i10, float f11, boolean z12) {
		this.priority = z1;
		this.toStream = z2;
		this.toLoop = z3;
		this.sourcename = string4;
		this.filenameURL = filenameURL5;
		this.soundBuffer = soundBuffer6;
		this.position = new Vector3D(f7, f8, f9);
		this.attModel = i10;
		this.distOrRoll = f11;
		this.temporary = z12;
		if(z2 && filenameURL5 != null) {
			this.codec = SoundSystemConfig.getCodec(filenameURL5.getFilename());
		}

	}

	public Source(Source source1, SoundBuffer soundBuffer2) {
		this.priority = source1.priority;
		this.toStream = source1.toStream;
		this.toLoop = source1.toLoop;
		this.sourcename = source1.sourcename;
		this.filenameURL = source1.filenameURL;
		this.position = source1.position.clone();
		this.attModel = source1.attModel;
		this.distOrRoll = source1.distOrRoll;
		this.temporary = source1.temporary;
		this.sourceVolume = source1.sourceVolume;
		this.rawDataStream = source1.rawDataStream;
		this.rawDataFormat = source1.rawDataFormat;
		this.soundBuffer = soundBuffer2;
		if(this.toStream && this.filenameURL != null) {
			this.codec = SoundSystemConfig.getCodec(this.filenameURL.getFilename());
		}

	}

	public Source(AudioFormat audioFormat1, boolean z2, String string3, float f4, float f5, float f6, int i7, float f8) {
		this.priority = z2;
		this.toStream = true;
		this.toLoop = false;
		this.sourcename = string3;
		this.filenameURL = null;
		this.soundBuffer = null;
		this.position = new Vector3D(f4, f5, f6);
		this.attModel = i7;
		this.distOrRoll = f8;
		this.temporary = false;
		this.rawDataStream = true;
		this.rawDataFormat = audioFormat1;
	}

	public void cleanup() {
		if(this.codec != null) {
			this.codec.cleanup();
		}

		Object object1 = this.soundSequenceLock;
		synchronized(this.soundSequenceLock) {
			if(this.soundSequenceQueue != null) {
				this.soundSequenceQueue.clear();
			}

			this.soundSequenceQueue = null;
		}

		this.sourcename = null;
		this.filenameURL = null;
		this.position = null;
		this.soundBuffer = null;
		this.codec = null;
	}

	public void queueSound(FilenameURL filenameURL1) {
		if(!this.toStream) {
			this.errorMessage("Method \'queueSound\' may only be used for streaming and MIDI sources.");
		} else if(filenameURL1 == null) {
			this.errorMessage("File not specified in method \'queueSound\'");
		} else {
			Object object2 = this.soundSequenceLock;
			synchronized(this.soundSequenceLock) {
				if(this.soundSequenceQueue == null) {
					this.soundSequenceQueue = new LinkedList();
				}

				this.soundSequenceQueue.add(filenameURL1);
			}
		}
	}

	public void dequeueSound(String string1) {
		if(!this.toStream) {
			this.errorMessage("Method \'dequeueSound\' may only be used for streaming and MIDI sources.");
		} else if(string1 != null && !string1.equals("")) {
			Object object2 = this.soundSequenceLock;
			synchronized(this.soundSequenceLock) {
				if(this.soundSequenceQueue != null) {
					this.soundSequenceQueue.remove(string1);
				}
			}

			object2 = this.soundSequenceLock;
			synchronized(this.soundSequenceLock) {
				if(this.soundSequenceQueue != null) {
					ListIterator listIterator3 = this.soundSequenceQueue.listIterator();

					while(listIterator3.hasNext()) {
						if(((FilenameURL)listIterator3.next()).getFilename().equals(string1)) {
							listIterator3.remove();
							break;
						}
					}
				}

			}
		} else {
			this.errorMessage("Filename not specified in method \'dequeueSound\'");
		}
	}

	public void fadeOut(FilenameURL filenameURL1, long j2) {
		if(!this.toStream) {
			this.errorMessage("Method \'fadeOut\' may only be used for streaming and MIDI sources.");
		} else if(j2 < 0L) {
			this.errorMessage("Miliseconds may not be negative in method \'fadeOut\'.");
		} else {
			this.fadeOutMilis = j2;
			this.fadeInMilis = 0L;
			this.fadeOutGain = 1.0F;
			this.lastFadeCheck = System.currentTimeMillis();
			Object object4 = this.soundSequenceLock;
			synchronized(this.soundSequenceLock) {
				if(this.soundSequenceQueue != null) {
					this.soundSequenceQueue.clear();
				}

				if(filenameURL1 != null) {
					if(this.soundSequenceQueue == null) {
						this.soundSequenceQueue = new LinkedList();
					}

					this.soundSequenceQueue.add(filenameURL1);
				}

			}
		}
	}

	public void fadeOutIn(FilenameURL filenameURL1, long j2, long j4) {
		if(!this.toStream) {
			this.errorMessage("Method \'fadeOutIn\' may only be used for streaming and MIDI sources.");
		} else if(filenameURL1 == null) {
			this.errorMessage("Filename/URL not specified in method \'fadeOutIn\'.");
		} else if(j2 >= 0L && j4 >= 0L) {
			this.fadeOutMilis = j2;
			this.fadeInMilis = j4;
			this.fadeOutGain = 1.0F;
			this.lastFadeCheck = System.currentTimeMillis();
			Object object6 = this.soundSequenceLock;
			synchronized(this.soundSequenceLock) {
				if(this.soundSequenceQueue == null) {
					this.soundSequenceQueue = new LinkedList();
				}

				this.soundSequenceQueue.clear();
				this.soundSequenceQueue.add(filenameURL1);
			}
		} else {
			this.errorMessage("Miliseconds may not be negative in method \'fadeOutIn\'.");
		}
	}

	public boolean checkFadeOut() {
		if(!this.toStream) {
			return false;
		} else if(this.fadeOutGain == -1.0F && this.fadeInGain == 1.0F) {
			return false;
		} else {
			long j1 = System.currentTimeMillis();
			long j3 = j1 - this.lastFadeCheck;
			this.lastFadeCheck = j1;
			float f5;
			if(this.fadeOutGain >= 0.0F) {
				if(this.fadeOutMilis == 0L) {
					this.fadeOutGain = 0.0F;
					this.fadeInGain = 0.0F;
					if(!this.incrementSoundSequence()) {
						this.stop();
					}

					this.positionChanged();
					this.preLoad = true;
					return false;
				} else {
					f5 = (float)j3 / (float)this.fadeOutMilis;
					this.fadeOutGain -= f5;
					if(this.fadeOutGain <= 0.0F) {
						this.fadeOutGain = -1.0F;
						this.fadeInGain = 0.0F;
						if(!this.incrementSoundSequence()) {
							this.stop();
						}

						this.positionChanged();
						this.preLoad = true;
						return false;
					} else {
						this.positionChanged();
						return true;
					}
				}
			} else if(this.fadeInGain < 1.0F) {
				this.fadeOutGain = -1.0F;
				if(this.fadeInMilis == 0L) {
					this.fadeOutGain = -1.0F;
					this.fadeInGain = 1.0F;
				} else {
					f5 = (float)j3 / (float)this.fadeInMilis;
					this.fadeInGain += f5;
					if(this.fadeInGain >= 1.0F) {
						this.fadeOutGain = -1.0F;
						this.fadeInGain = 1.0F;
					}
				}

				this.positionChanged();
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean incrementSoundSequence() {
		if(!this.toStream) {
			this.errorMessage("Method \'incrementSoundSequence\' may only be used for streaming and MIDI sources.");
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
					return true;
				} else {
					return false;
				}
			}
		}
	}

	public void setTemporary(boolean z1) {
		this.temporary = z1;
	}

	public void listenerMoved() {
	}

	public void setPosition(float f1, float f2, float f3) {
		this.position.x = f1;
		this.position.y = f2;
		this.position.z = f3;
	}

	public void positionChanged() {
	}

	public void setPriority(boolean z1) {
		this.priority = z1;
	}

	public void setLooping(boolean z1) {
		this.toLoop = z1;
	}

	public void setAttenuation(int i1) {
		this.attModel = i1;
	}

	public void setDistOrRoll(float f1) {
		this.distOrRoll = f1;
	}

	public float getDistanceFromListener() {
		return this.distanceFromListener;
	}

	public void setPitch(float f1) {
		float f2 = f1;
		if(f1 < 0.5F) {
			f2 = 0.5F;
		} else if(f1 > 2.0F) {
			f2 = 2.0F;
		}

		this.pitch = f2;
	}

	public float getPitch() {
		return this.pitch;
	}

	public boolean reverseByteOrderRequired() {
		return this.reverseByteOrder;
	}

	public void changeSource(boolean z1, boolean z2, boolean z3, String string4, FilenameURL filenameURL5, SoundBuffer soundBuffer6, float f7, float f8, float f9, int i10, float f11, boolean z12) {
		this.priority = z1;
		this.toStream = z2;
		this.toLoop = z3;
		this.sourcename = string4;
		this.filenameURL = filenameURL5;
		this.soundBuffer = soundBuffer6;
		this.position.x = f7;
		this.position.y = f8;
		this.position.z = f9;
		this.attModel = i10;
		this.distOrRoll = f11;
		this.temporary = z12;
	}

	public int feedRawAudioData(Channel channel1, byte[] b2) {
		if(!this.active(false, false)) {
			this.toPlay = true;
			return -1;
		} else {
			if(this.channel != channel1) {
				this.channel = channel1;
				this.channel.close();
				this.channel.setAudioFormat(this.rawDataFormat);
				this.positionChanged();
			}

			this.stopped(true, false);
			this.paused(true, false);
			return this.channel.feedRawAudioData(b2);
		}
	}

	public void play(Channel channel1) {
		if(!this.active(false, false)) {
			if(this.toLoop) {
				this.toPlay = true;
			}

		} else {
			if(this.channel != channel1) {
				this.channel = channel1;
				this.channel.close();
			}

			this.stopped(true, false);
			this.paused(true, false);
		}
	}

	public boolean stream() {
		if(this.channel == null) {
			return false;
		} else {
			if(this.preLoad) {
				if(!this.rawDataStream) {
					return this.preLoad();
				}

				this.preLoad = false;
			}

			if(this.rawDataStream) {
				if(this.stopped() || this.paused()) {
					return true;
				}

				if(this.channel.buffersProcessed() > 0) {
					this.channel.processBuffer();
				}
			} else {
				if(this.codec == null) {
					return false;
				}

				if(this.stopped()) {
					return false;
				}

				if(this.paused()) {
					return true;
				}

				int i1 = this.channel.buffersProcessed();
				SoundBuffer soundBuffer2 = null;

				for(int i3 = 0; i3 < i1; ++i3) {
					soundBuffer2 = this.codec.read();
					if(soundBuffer2 != null) {
						if(soundBuffer2.audioData != null) {
							this.channel.queueBuffer(soundBuffer2.audioData);
						}

						soundBuffer2.cleanup();
						soundBuffer2 = null;
					}

					if(this.codec.endOfStream()) {
						return false;
					}
				}
			}

			return true;
		}
	}

	public boolean preLoad() {
		if(this.channel == null) {
			return false;
		} else if(this.codec == null) {
			return false;
		} else {
			URL uRL1 = this.filenameURL.getURL();
			this.codec.initialize(uRL1);
			SoundBuffer soundBuffer2 = null;

			for(int i3 = 0; i3 < SoundSystemConfig.getNumberStreamingBuffers(); ++i3) {
				soundBuffer2 = this.codec.read();
				if(soundBuffer2 != null) {
					if(this.soundBuffer.audioData != null) {
						this.channel.queueBuffer(this.soundBuffer.audioData);
					}

					soundBuffer2.cleanup();
					soundBuffer2 = null;
				}
			}

			return true;
		}
	}

	public void pause() {
		this.toPlay = false;
		this.paused(true, true);
		if(this.channel != null) {
			this.channel.pause();
		} else {
			this.errorMessage("Channel null in method \'pause\'");
		}

	}

	public void stop() {
		this.toPlay = false;
		this.stopped(true, true);
		this.paused(true, false);
		if(this.channel != null) {
			this.channel.stop();
		} else {
			this.errorMessage("Channel null in method \'stop\'");
		}

	}

	public void rewind() {
		if(this.paused(false, false)) {
			this.stop();
		}

		if(this.channel != null) {
			boolean z1 = this.playing();
			this.channel.rewind();
			if(this.toStream && z1) {
				this.stop();
				this.play(this.channel);
			}
		} else {
			this.errorMessage("Channel null in method \'rewind\'");
		}

	}

	public void flush() {
		if(this.channel != null) {
			this.channel.flush();
		} else {
			this.errorMessage("Channel null in method \'flush\'");
		}

	}

	public void cull() {
		if(this.active(false, false)) {
			if(this.playing() && this.toLoop) {
				this.toPlay = true;
			}

			if(this.rawDataStream) {
				this.toPlay = true;
			}

			this.active(true, false);
			if(this.channel != null) {
				this.channel.close();
			}

			this.channel = null;
		}
	}

	public void activate() {
		this.active(true, true);
	}

	public boolean active() {
		return this.active(false, false);
	}

	public boolean playing() {
		return this.channel != null && this.channel.attachedSource == this ? (!this.paused() && !this.stopped() ? this.channel.playing() : false) : false;
	}

	public boolean stopped() {
		return this.stopped(false, false);
	}

	public boolean paused() {
		return this.paused(false, false);
	}

	private synchronized boolean active(boolean z1, boolean z2) {
		if(z1) {
			this.active = z2;
		}

		return this.active;
	}

	private synchronized boolean stopped(boolean z1, boolean z2) {
		if(z1) {
			this.stopped = z2;
		}

		return this.stopped;
	}

	private synchronized boolean paused(boolean z1, boolean z2) {
		if(z1) {
			this.paused = z2;
		}

		return this.paused;
	}

	public String getClassName() {
		String string1 = SoundSystemConfig.getLibraryTitle(this.libraryType);
		return string1.equals("No Sound") ? "Source" : "Source" + string1;
	}

	protected void message(String string1) {
		this.logger.message(string1, 0);
	}

	protected void importantMessage(String string1) {
		this.logger.importantMessage(string1, 0);
	}

	protected boolean errorCheck(boolean z1, String string2) {
		return this.logger.errorCheck(z1, this.getClassName(), string2, 0);
	}

	protected void errorMessage(String string1) {
		this.logger.errorMessage(this.getClassName(), string1, 0);
	}

	protected void printStackTrace(Exception exception1) {
		this.logger.printStackTrace(exception1, 1);
	}
}
