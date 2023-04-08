package paulscode.sound;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiDevice.Info;

public class MidiChannel implements MetaEventListener {
	private SoundSystemLogger logger;
	private FilenameURL filenameURL;
	private String sourcename;
	private static final int CHANGE_VOLUME = 7;
	private static final int END_OF_TRACK = 47;
	private static final boolean GET = false;
	private static final boolean SET = true;
	private static final boolean XXX = false;
	private Sequencer sequencer = null;
	private Synthesizer synthesizer = null;
	private MidiDevice synthDevice = null;
	private Sequence sequence = null;
	private boolean toLoop = true;
	private float gain = 1.0F;
	private boolean loading = true;
	private LinkedList sequenceQueue = null;
	private final Object sequenceQueueLock = new Object();
	protected float fadeOutGain = -1.0F;
	protected float fadeInGain = 1.0F;
	protected long fadeOutMilis = 0L;
	protected long fadeInMilis = 0L;
	protected long lastFadeCheck = 0L;
	private MidiChannel.FadeThread fadeThread = null;

	public MidiChannel(boolean z1, String string2, String string3) {
		this.loading(true, true);
		this.logger = SoundSystemConfig.getLogger();
		this.filenameURL(true, new FilenameURL(string3));
		this.sourcename(true, string2);
		this.setLooping(z1);
		this.init();
		this.loading(true, false);
	}

	public MidiChannel(boolean z1, String string2, URL uRL3, String string4) {
		this.loading(true, true);
		this.logger = SoundSystemConfig.getLogger();
		this.filenameURL(true, new FilenameURL(uRL3, string4));
		this.sourcename(true, string2);
		this.setLooping(z1);
		this.init();
		this.loading(true, false);
	}

	public MidiChannel(boolean z1, String string2, FilenameURL filenameURL3) {
		this.loading(true, true);
		this.logger = SoundSystemConfig.getLogger();
		this.filenameURL(true, filenameURL3);
		this.sourcename(true, string2);
		this.setLooping(z1);
		this.init();
		this.loading(true, false);
	}

	private void init() {
		this.getSequencer();
		this.setSequence(this.filenameURL(false, (FilenameURL)null).getURL());
		this.getSynthesizer();
		this.resetGain();
	}

	public void cleanup() {
		this.loading(true, true);
		this.setLooping(true);
		if(this.sequencer != null) {
			try {
				this.sequencer.stop();
				this.sequencer.close();
				this.sequencer.removeMetaEventListener(this);
			} catch (Exception exception6) {
			}
		}

		this.logger = null;
		this.sequencer = null;
		this.synthesizer = null;
		this.sequence = null;
		Object object1 = this.sequenceQueueLock;
		synchronized(this.sequenceQueueLock) {
			if(this.sequenceQueue != null) {
				this.sequenceQueue.clear();
			}

			this.sequenceQueue = null;
		}

		if(this.fadeThread != null) {
			boolean z8 = false;

			try {
				this.fadeThread.kill();
				this.fadeThread.interrupt();
			} catch (Exception exception5) {
				z8 = true;
			}

			if(!z8) {
				for(int i2 = 0; i2 < 50 && this.fadeThread.alive(); ++i2) {
					try {
						Thread.sleep(100L);
					} catch (InterruptedException interruptedException4) {
					}
				}
			}

			if(z8 || this.fadeThread.alive()) {
				this.errorMessage("MIDI fade effects thread did not die!");
				this.message("Ignoring errors... continuing clean-up.");
			}
		}

		this.fadeThread = null;
		this.loading(true, false);
	}

	public void queueSound(FilenameURL filenameURL1) {
		if(filenameURL1 == null) {
			this.errorMessage("Filename/URL not specified in method \'queueSound\'");
		} else {
			Object object2 = this.sequenceQueueLock;
			synchronized(this.sequenceQueueLock) {
				if(this.sequenceQueue == null) {
					this.sequenceQueue = new LinkedList();
				}

				this.sequenceQueue.add(filenameURL1);
			}
		}
	}

	public void dequeueSound(String string1) {
		if(string1 != null && !string1.equals("")) {
			Object object2 = this.sequenceQueueLock;
			synchronized(this.sequenceQueueLock) {
				if(this.sequenceQueue != null) {
					ListIterator listIterator3 = this.sequenceQueue.listIterator();

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
		if(j2 < 0L) {
			this.errorMessage("Miliseconds may not be negative in method \'fadeOut\'.");
		} else {
			this.fadeOutMilis = j2;
			this.fadeInMilis = 0L;
			this.fadeOutGain = 1.0F;
			this.lastFadeCheck = System.currentTimeMillis();
			Object object4 = this.sequenceQueueLock;
			synchronized(this.sequenceQueueLock) {
				if(this.sequenceQueue != null) {
					this.sequenceQueue.clear();
				}

				if(filenameURL1 != null) {
					if(this.sequenceQueue == null) {
						this.sequenceQueue = new LinkedList();
					}

					this.sequenceQueue.add(filenameURL1);
				}
			}

			if(this.fadeThread == null) {
				this.fadeThread = new MidiChannel.FadeThread();
				this.fadeThread.start();
			}

			this.fadeThread.interrupt();
		}
	}

	public void fadeOutIn(FilenameURL filenameURL1, long j2, long j4) {
		if(filenameURL1 == null) {
			this.errorMessage("Filename/URL not specified in method \'fadeOutIn\'.");
		} else if(j2 >= 0L && j4 >= 0L) {
			this.fadeOutMilis = j2;
			this.fadeInMilis = j4;
			this.fadeOutGain = 1.0F;
			this.lastFadeCheck = System.currentTimeMillis();
			Object object6 = this.sequenceQueueLock;
			synchronized(this.sequenceQueueLock) {
				if(this.sequenceQueue == null) {
					this.sequenceQueue = new LinkedList();
				}

				this.sequenceQueue.clear();
				this.sequenceQueue.add(filenameURL1);
			}

			if(this.fadeThread == null) {
				this.fadeThread = new MidiChannel.FadeThread();
				this.fadeThread.start();
			}

			this.fadeThread.interrupt();
		} else {
			this.errorMessage("Miliseconds may not be negative in method \'fadeOutIn\'.");
		}
	}

	private synchronized boolean checkFadeOut() {
		if(this.fadeOutGain == -1.0F && this.fadeInGain == 1.0F) {
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
					if(!this.incrementSequence()) {
						this.stop();
					}

					this.rewind();
					this.resetGain();
					return false;
				} else {
					f5 = (float)j3 / (float)this.fadeOutMilis;
					this.fadeOutGain -= f5;
					if(this.fadeOutGain <= 0.0F) {
						this.fadeOutGain = -1.0F;
						this.fadeInGain = 0.0F;
						if(!this.incrementSequence()) {
							this.stop();
						}

						this.rewind();
						this.resetGain();
						return false;
					} else {
						this.resetGain();
						return true;
					}
				}
			} else {
				if(this.fadeInGain < 1.0F) {
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

					this.resetGain();
				}

				return false;
			}
		}
	}

	private boolean incrementSequence() {
		Object object1 = this.sequenceQueueLock;
		synchronized(this.sequenceQueueLock) {
			if(this.sequenceQueue != null && this.sequenceQueue.size() > 0) {
				this.filenameURL(true, (FilenameURL)this.sequenceQueue.remove(0));
				this.loading(true, true);
				if(this.sequencer == null) {
					this.getSequencer();
				} else {
					this.sequencer.stop();
					this.sequencer.setMicrosecondPosition(0L);
					this.sequencer.removeMetaEventListener(this);

					try {
						Thread.sleep(100L);
					} catch (InterruptedException interruptedException4) {
					}
				}

				if(this.sequencer == null) {
					this.errorMessage("Unable to set the sequence in method \'incrementSequence\', because there wasn\'t a sequencer to use.");
					this.loading(true, false);
					return false;
				} else {
					this.setSequence(this.filenameURL(false, (FilenameURL)null).getURL());
					this.sequencer.start();
					this.resetGain();
					this.sequencer.addMetaEventListener(this);
					this.loading(true, false);
					return true;
				}
			} else {
				return false;
			}
		}
	}

	public void play() {
		if(!this.loading()) {
			if(this.sequencer == null) {
				return;
			}

			try {
				this.sequencer.start();
				this.sequencer.addMetaEventListener(this);
			} catch (Exception exception3) {
				this.errorMessage("Exception in method \'play\'");
				this.printStackTrace(exception3);
				SoundSystemException soundSystemException2 = new SoundSystemException(exception3.getMessage());
				SoundSystem.setException(soundSystemException2);
			}
		}

	}

	public void stop() {
		if(!this.loading()) {
			if(this.sequencer == null) {
				return;
			}

			try {
				this.sequencer.stop();
				this.sequencer.setMicrosecondPosition(0L);
				this.sequencer.removeMetaEventListener(this);
			} catch (Exception exception3) {
				this.errorMessage("Exception in method \'stop\'");
				this.printStackTrace(exception3);
				SoundSystemException soundSystemException2 = new SoundSystemException(exception3.getMessage());
				SoundSystem.setException(soundSystemException2);
			}
		}

	}

	public void pause() {
		if(!this.loading()) {
			if(this.sequencer == null) {
				return;
			}

			try {
				this.sequencer.stop();
			} catch (Exception exception3) {
				this.errorMessage("Exception in method \'pause\'");
				this.printStackTrace(exception3);
				SoundSystemException soundSystemException2 = new SoundSystemException(exception3.getMessage());
				SoundSystem.setException(soundSystemException2);
			}
		}

	}

	public void rewind() {
		if(!this.loading()) {
			if(this.sequencer == null) {
				return;
			}

			try {
				this.sequencer.setMicrosecondPosition(0L);
			} catch (Exception exception3) {
				this.errorMessage("Exception in method \'rewind\'");
				this.printStackTrace(exception3);
				SoundSystemException soundSystemException2 = new SoundSystemException(exception3.getMessage());
				SoundSystem.setException(soundSystemException2);
			}
		}

	}

	public void setVolume(float f1) {
		this.gain = f1;
		this.resetGain();
	}

	public float getVolume() {
		return this.gain;
	}

	public void switchSource(boolean z1, String string2, String string3) {
		this.loading(true, true);
		this.filenameURL(true, new FilenameURL(string3));
		this.sourcename(true, string2);
		this.setLooping(z1);
		this.reset();
		this.loading(true, false);
	}

	public void switchSource(boolean z1, String string2, URL uRL3, String string4) {
		this.loading(true, true);
		this.filenameURL(true, new FilenameURL(uRL3, string4));
		this.sourcename(true, string2);
		this.setLooping(z1);
		this.reset();
		this.loading(true, false);
	}

	public void switchSource(boolean z1, String string2, FilenameURL filenameURL3) {
		this.loading(true, true);
		this.filenameURL(true, filenameURL3);
		this.sourcename(true, string2);
		this.setLooping(z1);
		this.reset();
		this.loading(true, false);
	}

	private void reset() {
		Object object1 = this.sequenceQueueLock;
		synchronized(this.sequenceQueueLock) {
			if(this.sequenceQueue != null) {
				this.sequenceQueue.clear();
			}
		}

		if(this.sequencer == null) {
			this.getSequencer();
		} else {
			this.sequencer.stop();
			this.sequencer.setMicrosecondPosition(0L);
			this.sequencer.removeMetaEventListener(this);

			try {
				Thread.sleep(100L);
			} catch (InterruptedException interruptedException3) {
			}
		}

		if(this.sequencer == null) {
			this.errorMessage("Unable to set the sequence in method \'reset\', because there wasn\'t a sequencer to use.");
		} else {
			this.setSequence(this.filenameURL(false, (FilenameURL)null).getURL());
			this.sequencer.start();
			this.resetGain();
			this.sequencer.addMetaEventListener(this);
		}
	}

	public void setLooping(boolean z1) {
		this.toLoop(true, z1);
	}

	public boolean getLooping() {
		return this.toLoop(false, false);
	}

	private synchronized boolean toLoop(boolean z1, boolean z2) {
		if(z1) {
			this.toLoop = z2;
		}

		return this.toLoop;
	}

	public boolean loading() {
		return this.loading(false, false);
	}

	private synchronized boolean loading(boolean z1, boolean z2) {
		if(z1) {
			this.loading = z2;
		}

		return this.loading;
	}

	public void setSourcename(String string1) {
		this.sourcename(true, string1);
	}

	public String getSourcename() {
		return this.sourcename(false, (String)null);
	}

	private synchronized String sourcename(boolean z1, String string2) {
		if(z1) {
			this.sourcename = string2;
		}

		return this.sourcename;
	}

	public void setFilenameURL(FilenameURL filenameURL1) {
		this.filenameURL(true, filenameURL1);
	}

	public String getFilename() {
		return this.filenameURL(false, (FilenameURL)null).getFilename();
	}

	public FilenameURL getFilenameURL() {
		return this.filenameURL(false, (FilenameURL)null);
	}

	private synchronized FilenameURL filenameURL(boolean z1, FilenameURL filenameURL2) {
		if(z1) {
			this.filenameURL = filenameURL2;
		}

		return this.filenameURL;
	}

	public void meta(MetaMessage metaMessage1) {
		if(metaMessage1.getType() == 47) {
			if(this.toLoop) {
				if(!this.checkFadeOut()) {
					if(!this.incrementSequence()) {
						try {
							this.sequencer.setMicrosecondPosition(0L);
							this.sequencer.start();
							this.resetGain();
						} catch (Exception exception6) {
						}
					}
				} else if(this.sequencer != null) {
					try {
						this.sequencer.setMicrosecondPosition(0L);
						this.sequencer.start();
						this.resetGain();
					} catch (Exception exception5) {
					}
				}
			} else if(!this.checkFadeOut()) {
				if(!this.incrementSequence()) {
					try {
						this.sequencer.stop();
						this.sequencer.setMicrosecondPosition(0L);
						this.sequencer.removeMetaEventListener(this);
					} catch (Exception exception4) {
					}
				}
			} else {
				try {
					this.sequencer.stop();
					this.sequencer.setMicrosecondPosition(0L);
					this.sequencer.removeMetaEventListener(this);
				} catch (Exception exception3) {
				}
			}
		}

	}

	public void resetGain() {
		if(this.gain < 0.0F) {
			this.gain = 0.0F;
		}

		if(this.gain > 1.0F) {
			this.gain = 1.0F;
		}

		int i1 = (int)(this.gain * SoundSystemConfig.getMasterGain() * Math.abs(this.fadeOutGain) * this.fadeInGain * 127.0F);
		javax.sound.midi.MidiChannel[] midiChannel2;
		int i3;
		if(this.synthesizer != null) {
			midiChannel2 = this.synthesizer.getChannels();

			for(i3 = 0; midiChannel2 != null && i3 < midiChannel2.length; ++i3) {
				midiChannel2[i3].controlChange(7, i1);
			}
		} else if(this.synthDevice != null) {
			try {
				ShortMessage shortMessage7 = new ShortMessage();

				for(i3 = 0; i3 < 16; ++i3) {
					shortMessage7.setMessage(176, i3, 7, i1);
					this.synthDevice.getReceiver().send(shortMessage7, -1L);
				}
			} catch (Exception exception6) {
				this.errorMessage("Error resetting gain on MIDI device");
				this.printStackTrace(exception6);
			}
		} else if(this.sequencer != null && this.sequencer instanceof Synthesizer) {
			this.synthesizer = (Synthesizer)this.sequencer;
			midiChannel2 = this.synthesizer.getChannels();

			for(i3 = 0; midiChannel2 != null && i3 < midiChannel2.length; ++i3) {
				midiChannel2[i3].controlChange(7, i1);
			}
		} else {
			try {
				Receiver receiver8 = MidiSystem.getReceiver();
				ShortMessage shortMessage9 = new ShortMessage();

				for(int i4 = 0; i4 < 16; ++i4) {
					shortMessage9.setMessage(176, i4, 7, i1);
					receiver8.send(shortMessage9, -1L);
				}
			} catch (Exception exception5) {
				this.errorMessage("Error resetting gain on default receiver");
				this.printStackTrace(exception5);
			}
		}

	}

	private void getSequencer() {
		try {
			this.sequencer = MidiSystem.getSequencer();
			if(this.sequencer != null) {
				try {
					this.sequencer.getTransmitter();
				} catch (MidiUnavailableException midiUnavailableException2) {
					this.message("Unable to get a transmitter from the default MIDI sequencer");
				}

				this.sequencer.open();
			}
		} catch (MidiUnavailableException midiUnavailableException3) {
			this.message("Unable to open the default MIDI sequencer");
			this.sequencer = null;
		}

		if(this.sequencer == null) {
			this.sequencer = this.openSequencer("Real Time Sequencer");
		}

		if(this.sequencer == null) {
			this.sequencer = this.openSequencer("Java Sound Sequencer");
		}

		if(this.sequencer == null) {
			this.errorMessage("Failed to find an available MIDI sequencer");
		}
	}

	private void setSequence(URL uRL1) {
		if(this.sequencer == null) {
			this.errorMessage("Unable to update the sequence in method \'setSequence\', because variable \'sequencer\' is null");
		} else if(uRL1 == null) {
			this.errorMessage("Unable to load Midi file in method \'setSequence\'.");
		} else {
			try {
				this.sequence = MidiSystem.getSequence(uRL1);
			} catch (IOException iOException5) {
				this.errorMessage("Input failed while reading from MIDI file in method \'setSequence\'.");
				this.printStackTrace(iOException5);
				return;
			} catch (InvalidMidiDataException invalidMidiDataException6) {
				this.errorMessage("Invalid MIDI data encountered, or not a MIDI file in method \'setSequence\' (1).");
				this.printStackTrace(invalidMidiDataException6);
				return;
			}

			if(this.sequence == null) {
				this.errorMessage("MidiSystem \'getSequence\' method returned null in method \'setSequence\'.");
			} else {
				try {
					this.sequencer.setSequence(this.sequence);
				} catch (InvalidMidiDataException invalidMidiDataException3) {
					this.errorMessage("Invalid MIDI data encountered, or not a MIDI file in method \'setSequence\' (2).");
					this.printStackTrace(invalidMidiDataException3);
					return;
				} catch (Exception exception4) {
					this.errorMessage("Problem setting sequence from MIDI file in method \'setSequence\'.");
					this.printStackTrace(exception4);
					return;
				}
			}

		}
	}

	private void getSynthesizer() {
		if(this.sequencer == null) {
			this.errorMessage("Unable to load a Synthesizer in method \'getSynthesizer\', because variable \'sequencer\' is null");
		} else {
			if(this.sequencer instanceof Synthesizer) {
				this.synthesizer = (Synthesizer)this.sequencer;
			} else {
				try {
					this.synthesizer = MidiSystem.getSynthesizer();
					this.synthesizer.open();
				} catch (MidiUnavailableException midiUnavailableException5) {
					this.message("Unable to open the default synthesizer");
					this.synthesizer = null;
				}

				if(this.synthesizer == null) {
					this.synthDevice = this.openMidiDevice("Java Sound Synthesizer");
					if(this.synthDevice == null) {
						this.synthDevice = this.openMidiDevice("Microsoft GS Wavetable");
					}

					if(this.synthDevice == null) {
						this.synthDevice = this.openMidiDevice("Gervill");
					}

					if(this.synthDevice == null) {
						this.errorMessage("Failed to find an available MIDI synthesizer");
						return;
					}
				}

				if(this.synthesizer == null) {
					try {
						this.sequencer.getTransmitter().setReceiver(this.synthDevice.getReceiver());
					} catch (MidiUnavailableException midiUnavailableException4) {
						this.errorMessage("Unable to link sequencer transmitter with MIDI device receiver");
					}
				} else if(this.synthesizer.getDefaultSoundbank() == null) {
					try {
						this.sequencer.getTransmitter().setReceiver(MidiSystem.getReceiver());
					} catch (MidiUnavailableException midiUnavailableException3) {
						this.errorMessage("Unable to link sequencer transmitter with default receiver");
					}
				} else {
					try {
						this.sequencer.getTransmitter().setReceiver(this.synthesizer.getReceiver());
					} catch (MidiUnavailableException midiUnavailableException2) {
						this.errorMessage("Unable to link sequencer transmitter with synthesizer receiver");
					}
				}
			}

		}
	}

	private Sequencer openSequencer(String string1) {
		Sequencer sequencer2 = null;
		sequencer2 = (Sequencer)this.openMidiDevice(string1);
		if(sequencer2 == null) {
			return null;
		} else {
			try {
				sequencer2.getTransmitter();
				return sequencer2;
			} catch (MidiUnavailableException midiUnavailableException4) {
				this.message("    Unable to get a transmitter from this sequencer");
				sequencer2 = null;
				return null;
			}
		}
	}

	private MidiDevice openMidiDevice(String string1) {
		this.message("Searching for MIDI device with name containing \'" + string1 + "\'");
		MidiDevice midiDevice2 = null;
		Info[] midiDevice$Info3 = MidiSystem.getMidiDeviceInfo();

		for(int i4 = 0; i4 < midiDevice$Info3.length; ++i4) {
			midiDevice2 = null;

			try {
				midiDevice2 = MidiSystem.getMidiDevice(midiDevice$Info3[i4]);
			} catch (MidiUnavailableException midiUnavailableException7) {
				this.message("    Problem in method \'getMidiDevice\':  MIDIUnavailableException was thrown");
				midiDevice2 = null;
			}

			if(midiDevice2 != null && midiDevice$Info3[i4].getName().contains(string1)) {
				this.message("    Found MIDI device named \'" + midiDevice$Info3[i4].getName() + "\'");
				if(midiDevice2 instanceof Synthesizer) {
					this.message("        *this is a Synthesizer instance");
				}

				if(midiDevice2 instanceof Sequencer) {
					this.message("        *this is a Sequencer instance");
				}

				try {
					midiDevice2.open();
				} catch (MidiUnavailableException midiUnavailableException6) {
					this.message("    Unable to open this MIDI device");
					midiDevice2 = null;
				}

				return midiDevice2;
			}
		}

		this.message("    MIDI device not found");
		return null;
	}

	protected void message(String string1) {
		this.logger.message(string1, 0);
	}

	protected void importantMessage(String string1) {
		this.logger.importantMessage(string1, 0);
	}

	protected boolean errorCheck(boolean z1, String string2) {
		return this.logger.errorCheck(z1, "MidiChannel", string2, 0);
	}

	protected void errorMessage(String string1) {
		this.logger.errorMessage("MidiChannel", string1, 0);
	}

	protected void printStackTrace(Exception exception1) {
		this.logger.printStackTrace(exception1, 1);
	}

	class FadeThread extends SimpleThread {
		private FadeThread() {
		}

		public void run() {
			while(!this.dying()) {
				if(MidiChannel.this.fadeOutGain == -1.0F && MidiChannel.this.fadeInGain == 1.0F) {
					this.snooze(3600000L);
				}

				MidiChannel.this.checkFadeOut();
				this.snooze(50L);
			}

			this.cleanup();
		}

		FadeThread(Object midiChannel$12) {
			this();
		}
	}
}
