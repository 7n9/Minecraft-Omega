package paulscode.sound;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Set;
import javax.sound.sampled.AudioFormat;

public class SoundSystem {
	private static final boolean GET = false;
	private static final boolean SET = true;
	private static final boolean XXX = false;
	protected SoundSystemLogger logger = SoundSystemConfig.getLogger();
	protected Library soundLibrary;
	protected List commandQueue;
	private List sourcePlayList;
	protected CommandThread commandThread;
	public Random randomNumberGenerator;
	protected String className = "SoundSystem";
	private static Class currentLibrary = null;
	private static boolean initialized = false;
	private static SoundSystemException lastException = null;

	public SoundSystem() {
		if(this.logger == null) {
			this.logger = new SoundSystemLogger();
			SoundSystemConfig.setLogger(this.logger);
		}

		this.linkDefaultLibrariesAndCodecs();
		LinkedList linkedList1 = SoundSystemConfig.getLibraries();
		if(linkedList1 != null) {
			ListIterator listIterator2 = linkedList1.listIterator();

			while(listIterator2.hasNext()) {
				Class class3 = (Class)listIterator2.next();

				try {
					this.init(class3);
					return;
				} catch (SoundSystemException soundSystemException6) {
					this.logger.printExceptionMessage(soundSystemException6, 1);
				}
			}
		}

		try {
			this.init(Library.class);
		} catch (SoundSystemException soundSystemException5) {
			this.logger.printExceptionMessage(soundSystemException5, 1);
		}
	}

	public SoundSystem(Class class1) throws SoundSystemException {
		if(this.logger == null) {
			this.logger = new SoundSystemLogger();
			SoundSystemConfig.setLogger(this.logger);
		}

		this.linkDefaultLibrariesAndCodecs();
		this.init(class1);
	}

	protected void linkDefaultLibrariesAndCodecs() {
	}

	protected void init(Class class1) throws SoundSystemException {
		this.message("", 0);
		this.message("Starting up " + this.className + "...", 0);
		this.randomNumberGenerator = new Random();
		this.commandQueue = new LinkedList();
		this.sourcePlayList = new LinkedList();
		this.commandThread = new CommandThread(this);
		this.commandThread.start();
		snooze(200L);
		this.newLibrary(class1);
		this.message("", 0);
	}

	public void cleanup() {
		boolean z1 = false;
		this.message("", 0);
		this.message(this.className + " shutting down...", 0);

		try {
			this.commandThread.kill();
			this.commandThread.interrupt();
		} catch (Exception exception6) {
			z1 = true;
		}

		if(!z1) {
			for(int i2 = 0; i2 < 50 && this.commandThread.alive(); ++i2) {
				snooze(100L);
			}
		}

		if(z1 || this.commandThread.alive()) {
			this.errorMessage("Command thread did not die!", 0);
			this.message("Ignoring errors... continuing clean-up.", 0);
		}

		initialized(true, false);
		currentLibrary(true, (Class)null);

		try {
			if(this.soundLibrary != null) {
				this.soundLibrary.cleanup();
			}
		} catch (Exception exception5) {
			this.errorMessage("Problem during Library.cleanup()!", 0);
			this.message("Ignoring errors... continuing clean-up.", 0);
		}

		try {
			if(this.commandQueue != null) {
				this.commandQueue.clear();
			}
		} catch (Exception exception4) {
			this.errorMessage("Unable to clear the command queue!", 0);
			this.message("Ignoring errors... continuing clean-up.", 0);
		}

		try {
			if(this.sourcePlayList != null) {
				this.sourcePlayList.clear();
			}
		} catch (Exception exception3) {
			this.errorMessage("Unable to clear the source management list!", 0);
			this.message("Ignoring errors... continuing clean-up.", 0);
		}

		this.randomNumberGenerator = null;
		this.soundLibrary = null;
		this.commandQueue = null;
		this.sourcePlayList = null;
		this.commandThread = null;
		this.importantMessage("Author: Paul Lamb, www.paulscode.com", 1);
		this.message("", 0);
	}

	public void interruptCommandThread() {
		if(this.commandThread == null) {
			this.errorMessage("Command Thread null in method \'interruptCommandThread\'", 0);
		} else {
			this.commandThread.interrupt();
		}
	}

	public void loadSound(String string1) {
		this.CommandQueue(new CommandObject(2, new FilenameURL(string1)));
		this.commandThread.interrupt();
	}

	public void loadSound(URL uRL1, String string2) {
		this.CommandQueue(new CommandObject(2, new FilenameURL(uRL1, string2)));
		this.commandThread.interrupt();
	}

	public void unloadSound(String string1) {
		this.CommandQueue(new CommandObject(4, string1));
		this.commandThread.interrupt();
	}

	public void queueSound(String string1, String string2) {
		this.CommandQueue(new CommandObject(5, string1, new FilenameURL(string2)));
		this.commandThread.interrupt();
	}

	public void queueSound(String string1, URL uRL2, String string3) {
		this.CommandQueue(new CommandObject(5, string1, new FilenameURL(uRL2, string3)));
		this.commandThread.interrupt();
	}

	public void dequeueSound(String string1, String string2) {
		this.CommandQueue(new CommandObject(6, string1, string2));
		this.commandThread.interrupt();
	}

	public void fadeOut(String string1, String string2, long j3) {
		FilenameURL filenameURL5 = null;
		if(string2 != null) {
			filenameURL5 = new FilenameURL(string2);
		}

		this.CommandQueue(new CommandObject(7, string1, filenameURL5, j3));
		this.commandThread.interrupt();
	}

	public void fadeOut(String string1, URL uRL2, String string3, long j4) {
		FilenameURL filenameURL6 = null;
		if(uRL2 != null && string3 != null) {
			filenameURL6 = new FilenameURL(uRL2, string3);
		}

		this.CommandQueue(new CommandObject(7, string1, filenameURL6, j4));
		this.commandThread.interrupt();
	}

	public void fadeOutIn(String string1, String string2, long j3, long j5) {
		this.CommandQueue(new CommandObject(8, string1, new FilenameURL(string2), j3, j5));
		this.commandThread.interrupt();
	}

	public void fadeOutIn(String string1, URL uRL2, String string3, long j4, long j6) {
		this.CommandQueue(new CommandObject(8, string1, new FilenameURL(uRL2, string3), j4, j6));
		this.commandThread.interrupt();
	}

	public void checkFadeVolumes() {
		this.CommandQueue(new CommandObject(9));
		this.commandThread.interrupt();
	}

	public void backgroundMusic(String string1, String string2, boolean z3) {
		this.CommandQueue(new CommandObject(12, true, true, z3, string1, new FilenameURL(string2), 0.0F, 0.0F, 0.0F, 0, 0.0F, false));
		this.CommandQueue(new CommandObject(21, string1));
		this.commandThread.interrupt();
	}

	public void backgroundMusic(String string1, URL uRL2, String string3, boolean z4) {
		this.CommandQueue(new CommandObject(12, true, true, z4, string1, new FilenameURL(uRL2, string3), 0.0F, 0.0F, 0.0F, 0, 0.0F, false));
		this.CommandQueue(new CommandObject(21, string1));
		this.commandThread.interrupt();
	}

	public void newSource(boolean z1, String string2, String string3, boolean z4, float f5, float f6, float f7, int i8, float f9) {
		this.CommandQueue(new CommandObject(10, z1, false, z4, string2, new FilenameURL(string3), f5, f6, f7, i8, f9));
		this.commandThread.interrupt();
	}

	public void newSource(boolean z1, String string2, URL uRL3, String string4, boolean z5, float f6, float f7, float f8, int i9, float f10) {
		this.CommandQueue(new CommandObject(10, z1, false, z5, string2, new FilenameURL(uRL3, string4), f6, f7, f8, i9, f10));
		this.commandThread.interrupt();
	}

	public void newStreamingSource(boolean z1, String string2, String string3, boolean z4, float f5, float f6, float f7, int i8, float f9) {
		this.CommandQueue(new CommandObject(10, z1, true, z4, string2, new FilenameURL(string3), f5, f6, f7, i8, f9));
		this.commandThread.interrupt();
	}

	public void newStreamingSource(boolean z1, String string2, URL uRL3, String string4, boolean z5, float f6, float f7, float f8, int i9, float f10) {
		this.CommandQueue(new CommandObject(10, z1, true, z5, string2, new FilenameURL(uRL3, string4), f6, f7, f8, i9, f10));
		this.commandThread.interrupt();
	}

	public void rawDataStream(AudioFormat audioFormat1, boolean z2, String string3, float f4, float f5, float f6, int i7, float f8) {
		this.CommandQueue(new CommandObject(11, audioFormat1, z2, string3, f4, f5, f6, i7, f8));
		this.commandThread.interrupt();
	}

	public String quickPlay(boolean z1, String string2, boolean z3, float f4, float f5, float f6, int i7, float f8) {
		String string9 = "Source_" + this.randomNumberGenerator.nextInt() + "_" + this.randomNumberGenerator.nextInt();
		this.CommandQueue(new CommandObject(12, z1, false, z3, string9, new FilenameURL(string2), f4, f5, f6, i7, f8, true));
		this.CommandQueue(new CommandObject(21, string9));
		this.commandThread.interrupt();
		return string9;
	}

	public String quickPlay(boolean z1, URL uRL2, String string3, boolean z4, float f5, float f6, float f7, int i8, float f9) {
		String string10 = "Source_" + this.randomNumberGenerator.nextInt() + "_" + this.randomNumberGenerator.nextInt();
		this.CommandQueue(new CommandObject(12, z1, false, z4, string10, new FilenameURL(uRL2, string3), f5, f6, f7, i8, f9, true));
		this.CommandQueue(new CommandObject(21, string10));
		this.commandThread.interrupt();
		return string10;
	}

	public String quickStream(boolean z1, String string2, boolean z3, float f4, float f5, float f6, int i7, float f8) {
		String string9 = "Source_" + this.randomNumberGenerator.nextInt() + "_" + this.randomNumberGenerator.nextInt();
		this.CommandQueue(new CommandObject(12, z1, true, z3, string9, new FilenameURL(string2), f4, f5, f6, i7, f8, true));
		this.CommandQueue(new CommandObject(21, string9));
		this.commandThread.interrupt();
		return string9;
	}

	public String quickStream(boolean z1, URL uRL2, String string3, boolean z4, float f5, float f6, float f7, int i8, float f9) {
		String string10 = "Source_" + this.randomNumberGenerator.nextInt() + "_" + this.randomNumberGenerator.nextInt();
		this.CommandQueue(new CommandObject(12, z1, true, z4, string10, new FilenameURL(uRL2, string3), f5, f6, f7, i8, f9, true));
		this.CommandQueue(new CommandObject(21, string10));
		this.commandThread.interrupt();
		return string10;
	}

	public void setPosition(String string1, float f2, float f3, float f4) {
		this.CommandQueue(new CommandObject(13, string1, f2, f3, f4));
		this.commandThread.interrupt();
	}

	public void setVolume(String string1, float f2) {
		this.CommandQueue(new CommandObject(14, string1, f2));
		this.commandThread.interrupt();
	}

	public float getVolume(String string1) {
		Object object2 = SoundSystemConfig.THREAD_SYNC;
		synchronized(SoundSystemConfig.THREAD_SYNC) {
			return this.soundLibrary != null ? this.soundLibrary.getVolume(string1) : 0.0F;
		}
	}

	public void setPitch(String string1, float f2) {
		this.CommandQueue(new CommandObject(15, string1, f2));
		this.commandThread.interrupt();
	}

	public float getPitch(String string1) {
		return this.soundLibrary != null ? this.soundLibrary.getPitch(string1) : 1.0F;
	}

	public void setPriority(String string1, boolean z2) {
		this.CommandQueue(new CommandObject(16, string1, z2));
		this.commandThread.interrupt();
	}

	public void setLooping(String string1, boolean z2) {
		this.CommandQueue(new CommandObject(17, string1, z2));
		this.commandThread.interrupt();
	}

	public void setAttenuation(String string1, int i2) {
		this.CommandQueue(new CommandObject(18, string1, i2));
		this.commandThread.interrupt();
	}

	public void setDistOrRoll(String string1, float f2) {
		this.CommandQueue(new CommandObject(19, string1, f2));
		this.commandThread.interrupt();
	}

	public void feedRawAudioData(String string1, byte[] b2) {
		this.CommandQueue(new CommandObject(22, string1, b2));
		this.commandThread.interrupt();
	}

	public void play(String string1) {
		this.CommandQueue(new CommandObject(21, string1));
		this.commandThread.interrupt();
	}

	public void pause(String string1) {
		this.CommandQueue(new CommandObject(23, string1));
		this.commandThread.interrupt();
	}

	public void stop(String string1) {
		this.CommandQueue(new CommandObject(24, string1));
		this.commandThread.interrupt();
	}

	public void rewind(String string1) {
		this.CommandQueue(new CommandObject(25, string1));
		this.commandThread.interrupt();
	}

	public void flush(String string1) {
		this.CommandQueue(new CommandObject(26, string1));
		this.commandThread.interrupt();
	}

	public void cull(String string1) {
		this.CommandQueue(new CommandObject(27, string1));
		this.commandThread.interrupt();
	}

	public void activate(String string1) {
		this.CommandQueue(new CommandObject(28, string1));
		this.commandThread.interrupt();
	}

	public void setTemporary(String string1, boolean z2) {
		this.CommandQueue(new CommandObject(29, string1, z2));
		this.commandThread.interrupt();
	}

	public void removeSource(String string1) {
		this.CommandQueue(new CommandObject(30, string1));
		this.commandThread.interrupt();
	}

	public void moveListener(float f1, float f2, float f3) {
		this.CommandQueue(new CommandObject(31, f1, f2, f3));
		this.commandThread.interrupt();
	}

	public void setListenerPosition(float f1, float f2, float f3) {
		this.CommandQueue(new CommandObject(32, f1, f2, f3));
		this.commandThread.interrupt();
	}

	public void turnListener(float f1) {
		this.CommandQueue(new CommandObject(33, f1));
		this.commandThread.interrupt();
	}

	public void setListenerAngle(float f1) {
		this.CommandQueue(new CommandObject(34, f1));
		this.commandThread.interrupt();
	}

	public void setListenerOrientation(float f1, float f2, float f3, float f4, float f5, float f6) {
		this.CommandQueue(new CommandObject(35, f1, f2, f3, f4, f5, f6));
		this.commandThread.interrupt();
	}

	public void setMasterVolume(float f1) {
		this.CommandQueue(new CommandObject(36, f1));
		this.commandThread.interrupt();
	}

	public float getMasterVolume() {
		return SoundSystemConfig.getMasterGain();
	}

	public ListenerData getListenerData() {
		Object object1 = SoundSystemConfig.THREAD_SYNC;
		synchronized(SoundSystemConfig.THREAD_SYNC) {
			return this.soundLibrary.getListenerData();
		}
	}

	public boolean switchLibrary(Class class1) throws SoundSystemException {
		Object object2 = SoundSystemConfig.THREAD_SYNC;
		synchronized(SoundSystemConfig.THREAD_SYNC) {
			initialized(true, false);
			HashMap hashMap3 = null;
			ListenerData listenerData4 = null;
			boolean z5 = false;
			MidiChannel midiChannel6 = null;
			FilenameURL filenameURL7 = null;
			String string8 = "";
			boolean z9 = true;
			if(this.soundLibrary != null) {
				currentLibrary(true, (Class)null);
				hashMap3 = this.copySources(this.soundLibrary.getSources());
				listenerData4 = this.soundLibrary.getListenerData();
				midiChannel6 = this.soundLibrary.getMidiChannel();
				if(midiChannel6 != null) {
					z5 = true;
					z9 = midiChannel6.getLooping();
					string8 = midiChannel6.getSourcename();
					filenameURL7 = midiChannel6.getFilenameURL();
				}

				this.soundLibrary.cleanup();
				this.soundLibrary = null;
			}

			this.message("", 0);
			this.message("Switching to " + SoundSystemConfig.getLibraryTitle(class1), 0);
			this.message("(" + SoundSystemConfig.getLibraryDescription(class1) + ")", 1);

			try {
				this.soundLibrary = (Library)class1.newInstance();
			} catch (InstantiationException instantiationException13) {
				this.errorMessage("The specified library did not load properly", 1);
			} catch (IllegalAccessException illegalAccessException14) {
				this.errorMessage("The specified library did not load properly", 1);
			} catch (ExceptionInInitializerError exceptionInInitializerError15) {
				this.errorMessage("The specified library did not load properly", 1);
			} catch (SecurityException securityException16) {
				this.errorMessage("The specified library did not load properly", 1);
			}

			if(this.errorCheck(this.soundLibrary == null, "Library null after initialization in method \'switchLibrary\'", 1)) {
				SoundSystemException soundSystemException10 = new SoundSystemException(this.className + " did not load properly.  " + "Library was null after initialization.", 4);
				lastException(true, soundSystemException10);
				initialized(true, true);
				throw soundSystemException10;
			} else {
				try {
					this.soundLibrary.init();
				} catch (SoundSystemException soundSystemException12) {
					lastException(true, soundSystemException12);
					initialized(true, true);
					throw soundSystemException12;
				}

				this.soundLibrary.setListenerData(listenerData4);
				if(z5) {
					if(midiChannel6 != null) {
						midiChannel6.cleanup();
					}

					midiChannel6 = new MidiChannel(z9, string8, filenameURL7);
					this.soundLibrary.setMidiChannel(midiChannel6);
				}

				this.soundLibrary.copySources(hashMap3);
				this.message("", 0);
				lastException(true, (SoundSystemException)null);
				initialized(true, true);
				return true;
			}
		}
	}

	public boolean newLibrary(Class class1) throws SoundSystemException {
		initialized(true, false);
		this.CommandQueue(new CommandObject(37, class1));
		this.commandThread.interrupt();

		for(int i2 = 0; !initialized(false, false) && i2 < 100; ++i2) {
			snooze(400L);
			this.commandThread.interrupt();
		}

		SoundSystemException soundSystemException3;
		if(!initialized(false, false)) {
			soundSystemException3 = new SoundSystemException(this.className + " did not load after 30 seconds.", 4);
			lastException(true, soundSystemException3);
			throw soundSystemException3;
		} else {
			soundSystemException3 = lastException(false, (SoundSystemException)null);
			if(soundSystemException3 != null) {
				throw soundSystemException3;
			} else {
				return true;
			}
		}
	}

	private void CommandNewLibrary(Class class1) {
		initialized(true, false);
		String string2 = "Initializing ";
		if(this.soundLibrary != null) {
			currentLibrary(true, (Class)null);
			string2 = "Switching to ";
			this.soundLibrary.cleanup();
			this.soundLibrary = null;
		}

		this.message(string2 + SoundSystemConfig.getLibraryTitle(class1), 0);
		this.message("(" + SoundSystemConfig.getLibraryDescription(class1) + ")", 1);

		try {
			this.soundLibrary = (Library)class1.newInstance();
		} catch (InstantiationException instantiationException6) {
			this.errorMessage("The specified library did not load properly", 1);
		} catch (IllegalAccessException illegalAccessException7) {
			this.errorMessage("The specified library did not load properly", 1);
		} catch (ExceptionInInitializerError exceptionInInitializerError8) {
			this.errorMessage("The specified library did not load properly", 1);
		} catch (SecurityException securityException9) {
			this.errorMessage("The specified library did not load properly", 1);
		}

		if(this.errorCheck(this.soundLibrary == null, "Library null after initialization in method \'newLibrary\'", 1)) {
			lastException(true, new SoundSystemException(this.className + " did not load properly.  " + "Library was null after initialization.", 4));
			this.importantMessage("Switching to silent mode", 1);

			try {
				this.soundLibrary = new Library();
			} catch (SoundSystemException soundSystemException5) {
				lastException(true, new SoundSystemException("Silent mode did not load properly.  Library was null after initialization.", 4));
				initialized(true, true);
				return;
			}
		}

		try {
			this.soundLibrary.init();
		} catch (SoundSystemException soundSystemException4) {
			lastException(true, soundSystemException4);
			initialized(true, true);
			return;
		}

		lastException(true, (SoundSystemException)null);
		initialized(true, true);
	}

	private void CommandInitialize() {
		try {
			if(this.errorCheck(this.soundLibrary == null, "Library null after initialization in method \'CommandInitialize\'", 1)) {
				SoundSystemException soundSystemException1 = new SoundSystemException(this.className + " did not load properly.  " + "Library was null after initialization.", 4);
				lastException(true, soundSystemException1);
				throw soundSystemException1;
			}

			this.soundLibrary.init();
		} catch (SoundSystemException soundSystemException2) {
			lastException(true, soundSystemException2);
			initialized(true, true);
		}

	}

	private void CommandLoadSound(FilenameURL filenameURL1) {
		if(this.soundLibrary != null) {
			this.soundLibrary.loadSound(filenameURL1);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandLoadSound\'", 0);
		}

	}

	private void CommandUnloadSound(String string1) {
		if(this.soundLibrary != null) {
			this.soundLibrary.unloadSound(string1);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandLoadSound\'", 0);
		}

	}

	private void CommandQueueSound(String string1, FilenameURL filenameURL2) {
		if(this.soundLibrary != null) {
			this.soundLibrary.queueSound(string1, filenameURL2);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandQueueSound\'", 0);
		}

	}

	private void CommandDequeueSound(String string1, String string2) {
		if(this.soundLibrary != null) {
			this.soundLibrary.dequeueSound(string1, string2);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandDequeueSound\'", 0);
		}

	}

	private void CommandFadeOut(String string1, FilenameURL filenameURL2, long j3) {
		if(this.soundLibrary != null) {
			this.soundLibrary.fadeOut(string1, filenameURL2, j3);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandFadeOut\'", 0);
		}

	}

	private void CommandFadeOutIn(String string1, FilenameURL filenameURL2, long j3, long j5) {
		if(this.soundLibrary != null) {
			this.soundLibrary.fadeOutIn(string1, filenameURL2, j3, j5);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandFadeOutIn\'", 0);
		}

	}

	private void CommandCheckFadeVolumes() {
		if(this.soundLibrary != null) {
			this.soundLibrary.checkFadeVolumes();
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandCheckFadeVolumes\'", 0);
		}

	}

	private void CommandNewSource(boolean z1, boolean z2, boolean z3, String string4, FilenameURL filenameURL5, float f6, float f7, float f8, int i9, float f10) {
		if(this.soundLibrary != null) {
			if(filenameURL5.getFilename().matches(".*[mM][iI][dD][iI]?$") && !SoundSystemConfig.midiCodec()) {
				this.soundLibrary.loadMidi(z3, string4, filenameURL5);
			} else {
				this.soundLibrary.newSource(z1, z2, z3, string4, filenameURL5, f6, f7, f8, i9, f10);
			}
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandNewSource\'", 0);
		}

	}

	private void CommandRawDataStream(AudioFormat audioFormat1, boolean z2, String string3, float f4, float f5, float f6, int i7, float f8) {
		if(this.soundLibrary != null) {
			this.soundLibrary.rawDataStream(audioFormat1, z2, string3, f4, f5, f6, i7, f8);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandRawDataStream\'", 0);
		}

	}

	private void CommandQuickPlay(boolean z1, boolean z2, boolean z3, String string4, FilenameURL filenameURL5, float f6, float f7, float f8, int i9, float f10, boolean z11) {
		if(this.soundLibrary != null) {
			if(filenameURL5.getFilename().matches(".*[mM][iI][dD][iI]?$") && !SoundSystemConfig.midiCodec()) {
				this.soundLibrary.loadMidi(z3, string4, filenameURL5);
			} else {
				this.soundLibrary.quickPlay(z1, z2, z3, string4, filenameURL5, f6, f7, f8, i9, f10, z11);
			}
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandQuickPlay\'", 0);
		}

	}

	private void CommandSetPosition(String string1, float f2, float f3, float f4) {
		if(this.soundLibrary != null) {
			this.soundLibrary.setPosition(string1, f2, f3, f4);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandMoveSource\'", 0);
		}

	}

	private void CommandSetVolume(String string1, float f2) {
		if(this.soundLibrary != null) {
			this.soundLibrary.setVolume(string1, f2);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandSetVolume\'", 0);
		}

	}

	private void CommandSetPitch(String string1, float f2) {
		if(this.soundLibrary != null) {
			this.soundLibrary.setPitch(string1, f2);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandSetPitch\'", 0);
		}

	}

	private void CommandSetPriority(String string1, boolean z2) {
		if(this.soundLibrary != null) {
			this.soundLibrary.setPriority(string1, z2);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandSetPriority\'", 0);
		}

	}

	private void CommandSetLooping(String string1, boolean z2) {
		if(this.soundLibrary != null) {
			this.soundLibrary.setLooping(string1, z2);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandSetLooping\'", 0);
		}

	}

	private void CommandSetAttenuation(String string1, int i2) {
		if(this.soundLibrary != null) {
			this.soundLibrary.setAttenuation(string1, i2);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandSetAttenuation\'", 0);
		}

	}

	private void CommandSetDistOrRoll(String string1, float f2) {
		if(this.soundLibrary != null) {
			this.soundLibrary.setDistOrRoll(string1, f2);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandSetDistOrRoll\'", 0);
		}

	}

	private void CommandPlay(String string1) {
		if(this.soundLibrary != null) {
			this.soundLibrary.play(string1);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandPlay\'", 0);
		}

	}

	private void CommandFeedRawAudioData(String string1, byte[] b2) {
		if(this.soundLibrary != null) {
			this.soundLibrary.feedRawAudioData(string1, b2);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandFeedRawAudioData\'", 0);
		}

	}

	private void CommandPause(String string1) {
		if(this.soundLibrary != null) {
			this.soundLibrary.pause(string1);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandPause\'", 0);
		}

	}

	private void CommandStop(String string1) {
		if(this.soundLibrary != null) {
			this.soundLibrary.stop(string1);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandStop\'", 0);
		}

	}

	private void CommandRewind(String string1) {
		if(this.soundLibrary != null) {
			this.soundLibrary.rewind(string1);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandRewind\'", 0);
		}

	}

	private void CommandFlush(String string1) {
		if(this.soundLibrary != null) {
			this.soundLibrary.flush(string1);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandFlush\'", 0);
		}

	}

	private void CommandSetTemporary(String string1, boolean z2) {
		if(this.soundLibrary != null) {
			this.soundLibrary.setTemporary(string1, z2);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandSetActive\'", 0);
		}

	}

	private void CommandRemoveSource(String string1) {
		if(this.soundLibrary != null) {
			this.soundLibrary.removeSource(string1);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandRemoveSource\'", 0);
		}

	}

	private void CommandMoveListener(float f1, float f2, float f3) {
		if(this.soundLibrary != null) {
			this.soundLibrary.moveListener(f1, f2, f3);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandMoveListener\'", 0);
		}

	}

	private void CommandSetListenerPosition(float f1, float f2, float f3) {
		if(this.soundLibrary != null) {
			this.soundLibrary.setListenerPosition(f1, f2, f3);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandSetListenerPosition\'", 0);
		}

	}

	private void CommandTurnListener(float f1) {
		if(this.soundLibrary != null) {
			this.soundLibrary.turnListener(f1);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandTurnListener\'", 0);
		}

	}

	private void CommandSetListenerAngle(float f1) {
		if(this.soundLibrary != null) {
			this.soundLibrary.setListenerAngle(f1);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandSetListenerAngle\'", 0);
		}

	}

	private void CommandSetListenerOrientation(float f1, float f2, float f3, float f4, float f5, float f6) {
		if(this.soundLibrary != null) {
			this.soundLibrary.setListenerOrientation(f1, f2, f3, f4, f5, f6);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandSetListenerOrientation\'", 0);
		}

	}

	private void CommandCull(String string1) {
		if(this.soundLibrary != null) {
			this.soundLibrary.cull(string1);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandCull\'", 0);
		}

	}

	private void CommandActivate(String string1) {
		if(this.soundLibrary != null) {
			this.soundLibrary.activate(string1);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandActivate\'", 0);
		}

	}

	private void CommandSetMasterVolume(float f1) {
		if(this.soundLibrary != null) {
			this.soundLibrary.setMasterVolume(f1);
		} else {
			this.errorMessage("Variable \'soundLibrary\' null in method \'CommandSetMasterVolume\'", 0);
		}

	}

	protected void ManageSources() {
	}

	public boolean CommandQueue(CommandObject commandObject1) {
		Object object2 = SoundSystemConfig.THREAD_SYNC;
		synchronized(SoundSystemConfig.THREAD_SYNC) {
			if(commandObject1 != null) {
				if(this.commandQueue == null) {
					return false;
				} else {
					this.commandQueue.add(commandObject1);
					return true;
				}
			} else {
				boolean z3 = false;

				CommandObject commandObject4;
				while(this.commandQueue != null && this.commandQueue.size() > 0) {
					commandObject4 = (CommandObject)this.commandQueue.remove(0);
					if(commandObject4 != null) {
						switch(commandObject4.Command) {
						case 1:
							this.CommandInitialize();
							break;
						case 2:
							this.CommandLoadSound((FilenameURL)commandObject4.objectArgs[0]);
						case 3:
						case 20:
						default:
							break;
						case 4:
							this.CommandUnloadSound(commandObject4.stringArgs[0]);
							break;
						case 5:
							this.CommandQueueSound(commandObject4.stringArgs[0], (FilenameURL)commandObject4.objectArgs[0]);
							break;
						case 6:
							this.CommandDequeueSound(commandObject4.stringArgs[0], commandObject4.stringArgs[1]);
							break;
						case 7:
							this.CommandFadeOut(commandObject4.stringArgs[0], (FilenameURL)commandObject4.objectArgs[0], commandObject4.longArgs[0]);
							break;
						case 8:
							this.CommandFadeOutIn(commandObject4.stringArgs[0], (FilenameURL)commandObject4.objectArgs[0], commandObject4.longArgs[0], commandObject4.longArgs[1]);
							break;
						case 9:
							this.CommandCheckFadeVolumes();
							break;
						case 10:
							this.CommandNewSource(commandObject4.boolArgs[0], commandObject4.boolArgs[1], commandObject4.boolArgs[2], commandObject4.stringArgs[0], (FilenameURL)commandObject4.objectArgs[0], commandObject4.floatArgs[0], commandObject4.floatArgs[1], commandObject4.floatArgs[2], commandObject4.intArgs[0], commandObject4.floatArgs[3]);
							break;
						case 11:
							this.CommandRawDataStream((AudioFormat)commandObject4.objectArgs[0], commandObject4.boolArgs[0], commandObject4.stringArgs[0], commandObject4.floatArgs[0], commandObject4.floatArgs[1], commandObject4.floatArgs[2], commandObject4.intArgs[0], commandObject4.floatArgs[3]);
							break;
						case 12:
							this.CommandQuickPlay(commandObject4.boolArgs[0], commandObject4.boolArgs[1], commandObject4.boolArgs[2], commandObject4.stringArgs[0], (FilenameURL)commandObject4.objectArgs[0], commandObject4.floatArgs[0], commandObject4.floatArgs[1], commandObject4.floatArgs[2], commandObject4.intArgs[0], commandObject4.floatArgs[3], commandObject4.boolArgs[3]);
							break;
						case 13:
							this.CommandSetPosition(commandObject4.stringArgs[0], commandObject4.floatArgs[0], commandObject4.floatArgs[1], commandObject4.floatArgs[2]);
							break;
						case 14:
							this.CommandSetVolume(commandObject4.stringArgs[0], commandObject4.floatArgs[0]);
							break;
						case 15:
							this.CommandSetPitch(commandObject4.stringArgs[0], commandObject4.floatArgs[0]);
							break;
						case 16:
							this.CommandSetPriority(commandObject4.stringArgs[0], commandObject4.boolArgs[0]);
							break;
						case 17:
							this.CommandSetLooping(commandObject4.stringArgs[0], commandObject4.boolArgs[0]);
							break;
						case 18:
							this.CommandSetAttenuation(commandObject4.stringArgs[0], commandObject4.intArgs[0]);
							break;
						case 19:
							this.CommandSetDistOrRoll(commandObject4.stringArgs[0], commandObject4.floatArgs[0]);
							break;
						case 21:
							this.sourcePlayList.add(commandObject4);
							break;
						case 22:
							this.sourcePlayList.add(commandObject4);
							break;
						case 23:
							this.CommandPause(commandObject4.stringArgs[0]);
							break;
						case 24:
							this.CommandStop(commandObject4.stringArgs[0]);
							break;
						case 25:
							this.CommandRewind(commandObject4.stringArgs[0]);
							break;
						case 26:
							this.CommandFlush(commandObject4.stringArgs[0]);
							break;
						case 27:
							this.CommandCull(commandObject4.stringArgs[0]);
							break;
						case 28:
							z3 = true;
							this.CommandActivate(commandObject4.stringArgs[0]);
							break;
						case 29:
							this.CommandSetTemporary(commandObject4.stringArgs[0], commandObject4.boolArgs[0]);
							break;
						case 30:
							this.CommandRemoveSource(commandObject4.stringArgs[0]);
							break;
						case 31:
							this.CommandMoveListener(commandObject4.floatArgs[0], commandObject4.floatArgs[1], commandObject4.floatArgs[2]);
							break;
						case 32:
							this.CommandSetListenerPosition(commandObject4.floatArgs[0], commandObject4.floatArgs[1], commandObject4.floatArgs[2]);
							break;
						case 33:
							this.CommandTurnListener(commandObject4.floatArgs[0]);
							break;
						case 34:
							this.CommandSetListenerAngle(commandObject4.floatArgs[0]);
							break;
						case 35:
							this.CommandSetListenerOrientation(commandObject4.floatArgs[0], commandObject4.floatArgs[1], commandObject4.floatArgs[2], commandObject4.floatArgs[3], commandObject4.floatArgs[4], commandObject4.floatArgs[5]);
							break;
						case 36:
							this.CommandSetMasterVolume(commandObject4.floatArgs[0]);
							break;
						case 37:
							this.CommandNewLibrary(commandObject4.classArgs[0]);
						}
					}
				}

				if(z3) {
					this.soundLibrary.replaySources();
				}

				while(this.sourcePlayList != null && this.sourcePlayList.size() > 0) {
					commandObject4 = (CommandObject)this.sourcePlayList.remove(0);
					if(commandObject4 != null) {
						switch(commandObject4.Command) {
						case 21:
							this.CommandPlay(commandObject4.stringArgs[0]);
							break;
						case 22:
							this.CommandFeedRawAudioData(commandObject4.stringArgs[0], commandObject4.buffer);
						}
					}
				}

				return this.commandQueue != null && this.commandQueue.size() > 0;
			}
		}
	}

	public void removeTemporarySources() {
		Object object1 = SoundSystemConfig.THREAD_SYNC;
		synchronized(SoundSystemConfig.THREAD_SYNC) {
			if(this.soundLibrary != null) {
				this.soundLibrary.removeTemporarySources();
			}

		}
	}

	public boolean playing(String string1) {
		Object object2 = SoundSystemConfig.THREAD_SYNC;
		synchronized(SoundSystemConfig.THREAD_SYNC) {
			if(this.soundLibrary == null) {
				return false;
			} else {
				Source source3 = (Source)this.soundLibrary.getSources().get(string1);
				return source3 == null ? false : source3.playing();
			}
		}
	}

	public boolean playing() {
		Object object1 = SoundSystemConfig.THREAD_SYNC;
		synchronized(SoundSystemConfig.THREAD_SYNC) {
			if(this.soundLibrary == null) {
				return false;
			} else {
				HashMap hashMap2 = this.soundLibrary.getSources();
				if(hashMap2 == null) {
					return false;
				} else {
					Set set3 = hashMap2.keySet();
					Iterator iterator4 = set3.iterator();

					Source source6;
					do {
						if(!iterator4.hasNext()) {
							return false;
						}

						String string5 = (String)iterator4.next();
						source6 = (Source)hashMap2.get(string5);
					} while(source6 == null || !source6.playing());

					return true;
				}
			}
		}
	}

	private HashMap copySources(HashMap hashMap1) {
		Set set2 = hashMap1.keySet();
		Iterator iterator3 = set2.iterator();
		HashMap hashMap6 = new HashMap();

		while(iterator3.hasNext()) {
			String string4 = (String)iterator3.next();
			Source source5 = (Source)hashMap1.get(string4);
			if(source5 != null) {
				hashMap6.put(string4, new Source(source5, (SoundBuffer)null));
			}
		}

		return hashMap6;
	}

	public static boolean libraryCompatible(Class class0) {
		SoundSystemLogger soundSystemLogger1 = SoundSystemConfig.getLogger();
		if(soundSystemLogger1 == null) {
			soundSystemLogger1 = new SoundSystemLogger();
			SoundSystemConfig.setLogger(soundSystemLogger1);
		}

		soundSystemLogger1.message("", 0);
		soundSystemLogger1.message("Checking if " + SoundSystemConfig.getLibraryTitle(class0) + " is compatible...", 0);
		boolean z2 = SoundSystemConfig.libraryCompatible(class0);
		if(z2) {
			soundSystemLogger1.message("...yes", 1);
		} else {
			soundSystemLogger1.message("...no", 1);
		}

		return z2;
	}

	public static Class currentLibrary() {
		return currentLibrary(false, (Class)null);
	}

	public static boolean initialized() {
		return initialized(false, false);
	}

	public static SoundSystemException getLastException() {
		return lastException(false, (SoundSystemException)null);
	}

	public static void setException(SoundSystemException soundSystemException0) {
		lastException(true, soundSystemException0);
	}

	private static boolean initialized(boolean z0, boolean z1) {
		Object object2 = SoundSystemConfig.THREAD_SYNC;
		synchronized(SoundSystemConfig.THREAD_SYNC) {
			if(z0) {
				initialized = z1;
			}

			return initialized;
		}
	}

	private static Class currentLibrary(boolean z0, Class class1) {
		Object object2 = SoundSystemConfig.THREAD_SYNC;
		synchronized(SoundSystemConfig.THREAD_SYNC) {
			if(z0) {
				currentLibrary = class1;
			}

			return currentLibrary;
		}
	}

	private static SoundSystemException lastException(boolean z0, SoundSystemException soundSystemException1) {
		Object object2 = SoundSystemConfig.THREAD_SYNC;
		synchronized(SoundSystemConfig.THREAD_SYNC) {
			if(z0) {
				lastException = soundSystemException1;
			}

			return lastException;
		}
	}

	protected static void snooze(long j0) {
		try {
			Thread.sleep(j0);
		} catch (InterruptedException interruptedException3) {
		}

	}

	protected void message(String string1, int i2) {
		this.logger.message(string1, i2);
	}

	protected void importantMessage(String string1, int i2) {
		this.logger.importantMessage(string1, i2);
	}

	protected boolean errorCheck(boolean z1, String string2, int i3) {
		return this.logger.errorCheck(z1, this.className, string2, i3);
	}

	protected void errorMessage(String string1, int i2) {
		this.logger.errorMessage(this.className, string1, i2);
	}
}
