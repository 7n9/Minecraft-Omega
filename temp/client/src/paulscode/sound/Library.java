package paulscode.sound;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.sound.sampled.AudioFormat;

public class Library {
	private SoundSystemLogger logger = SoundSystemConfig.getLogger();
	protected ListenerData listener;
	protected HashMap bufferMap = null;
	protected HashMap sourceMap;
	private MidiChannel midiChannel;
	protected List streamingChannels;
	protected List normalChannels;
	private String[] streamingChannelSourceNames;
	private String[] normalChannelSourceNames;
	private int nextStreamingChannel = 0;
	private int nextNormalChannel = 0;
	protected StreamThread streamThread;

	public Library() throws SoundSystemException {
		this.bufferMap = new HashMap();
		this.sourceMap = new HashMap();
		this.listener = new ListenerData(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 0.0F, 0.0F);
		this.streamingChannels = new LinkedList();
		this.normalChannels = new LinkedList();
		this.streamingChannelSourceNames = new String[SoundSystemConfig.getNumberStreamingChannels()];
		this.normalChannelSourceNames = new String[SoundSystemConfig.getNumberNormalChannels()];
		this.streamThread = new StreamThread();
		this.streamThread.start();
	}

	public void cleanup() {
		this.streamThread.kill();
		this.streamThread.interrupt();

		for(int i1 = 0; i1 < 50 && this.streamThread.alive(); ++i1) {
			try {
				Thread.sleep(100L);
			} catch (Exception exception6) {
			}
		}

		if(this.streamThread.alive()) {
			this.errorMessage("Stream thread did not die!");
			this.message("Ignoring errors... continuing clean-up.");
		}

		if(this.midiChannel != null) {
			this.midiChannel.cleanup();
			this.midiChannel = null;
		}

		Channel channel7 = null;
		if(this.streamingChannels != null) {
			while(!this.streamingChannels.isEmpty()) {
				channel7 = (Channel)this.streamingChannels.remove(0);
				channel7.close();
				channel7.cleanup();
				channel7 = null;
			}

			this.streamingChannels.clear();
			this.streamingChannels = null;
		}

		if(this.normalChannels != null) {
			while(!this.normalChannels.isEmpty()) {
				channel7 = (Channel)this.normalChannels.remove(0);
				channel7.close();
				channel7.cleanup();
				channel7 = null;
			}

			this.normalChannels.clear();
			this.normalChannels = null;
		}

		Set set2 = this.sourceMap.keySet();
		Iterator iterator3 = set2.iterator();

		while(iterator3.hasNext()) {
			String string4 = (String)iterator3.next();
			Source source5 = (Source)this.sourceMap.get(string4);
			if(source5 != null) {
				source5.cleanup();
			}
		}

		this.sourceMap.clear();
		this.sourceMap = null;
		this.listener = null;
		this.streamThread = null;
	}

	public void init() throws SoundSystemException {
		Channel channel1 = null;

		int i2;
		for(i2 = 0; i2 < SoundSystemConfig.getNumberStreamingChannels(); ++i2) {
			channel1 = this.createChannel(1);
			if(channel1 == null) {
				break;
			}

			this.streamingChannels.add(channel1);
		}

		for(i2 = 0; i2 < SoundSystemConfig.getNumberNormalChannels(); ++i2) {
			channel1 = this.createChannel(0);
			if(channel1 == null) {
				break;
			}

			this.normalChannels.add(channel1);
		}

	}

	public static boolean libraryCompatible() {
		return true;
	}

	protected Channel createChannel(int i1) {
		return null;
	}

	public boolean loadSound(FilenameURL filenameURL1) {
		return true;
	}

	public void unloadSound(String string1) {
		this.bufferMap.remove(string1);
	}

	public void rawDataStream(AudioFormat audioFormat1, boolean z2, String string3, float f4, float f5, float f6, int i7, float f8) {
		this.sourceMap.put(string3, new Source(audioFormat1, z2, string3, f4, f5, f6, i7, f8));
	}

	public void newSource(boolean z1, boolean z2, boolean z3, String string4, FilenameURL filenameURL5, float f6, float f7, float f8, int i9, float f10) {
		this.sourceMap.put(string4, new Source(z1, z2, z3, string4, filenameURL5, (SoundBuffer)null, f6, f7, f8, i9, f10, false));
	}

	public void quickPlay(boolean z1, boolean z2, boolean z3, String string4, FilenameURL filenameURL5, float f6, float f7, float f8, int i9, float f10, boolean z11) {
		this.sourceMap.put(string4, new Source(z1, z2, z3, string4, filenameURL5, (SoundBuffer)null, f6, f7, f8, i9, f10, z11));
	}

	public void setTemporary(String string1, boolean z2) {
		Source source3 = (Source)this.sourceMap.get(string1);
		if(source3 != null) {
			source3.setTemporary(z2);
		}

	}

	public void setPosition(String string1, float f2, float f3, float f4) {
		Source source5 = (Source)this.sourceMap.get(string1);
		if(source5 != null) {
			source5.setPosition(f2, f3, f4);
		}

	}

	public void setPriority(String string1, boolean z2) {
		Source source3 = (Source)this.sourceMap.get(string1);
		if(source3 != null) {
			source3.setPriority(z2);
		}

	}

	public void setLooping(String string1, boolean z2) {
		Source source3 = (Source)this.sourceMap.get(string1);
		if(source3 != null) {
			source3.setLooping(z2);
		}

	}

	public void setAttenuation(String string1, int i2) {
		Source source3 = (Source)this.sourceMap.get(string1);
		if(source3 != null) {
			source3.setAttenuation(i2);
		}

	}

	public void setDistOrRoll(String string1, float f2) {
		Source source3 = (Source)this.sourceMap.get(string1);
		if(source3 != null) {
			source3.setDistOrRoll(f2);
		}

	}

	public int feedRawAudioData(String string1, byte[] b2) {
		if(string1 != null && !string1.equals("")) {
			if(this.midiSourcename(string1)) {
				this.errorMessage("Raw audio data can not be fed to the MIDI channel.");
				return -1;
			} else {
				Source source3 = (Source)this.sourceMap.get(string1);
				if(source3 == null) {
					this.errorMessage("Source \'" + string1 + "\' not found in " + "method \'feedRawAudioData\'");
				}

				return this.feedRawAudioData(source3, b2);
			}
		} else {
			this.errorMessage("Sourcename not specified in method \'feedRawAudioData\'");
			return -1;
		}
	}

	public int feedRawAudioData(Source source1, byte[] b2) {
		if(source1 == null) {
			this.errorMessage("Source parameter null in method \'feedRawAudioData\'");
			return -1;
		} else if(!source1.toStream) {
			this.errorMessage("Only a streaming source may be specified in method \'feedRawAudioData\'");
			return -1;
		} else if(!source1.rawDataStream) {
			this.errorMessage("Streaming source already associated with a file or URL in method\'feedRawAudioData\'");
			return -1;
		} else if(source1.playing() && source1.channel != null) {
			return source1.feedRawAudioData(source1.channel, b2);
		} else {
			Channel channel3;
			if(source1.channel != null && source1.channel.attachedSource == source1) {
				channel3 = source1.channel;
			} else {
				channel3 = this.getNextChannel(source1);
			}

			int i4 = source1.feedRawAudioData(channel3, b2);
			channel3.attachedSource = source1;
			this.streamThread.watch(source1);
			this.streamThread.interrupt();
			return i4;
		}
	}

	public void play(String string1) {
		if(string1 != null && !string1.equals("")) {
			if(this.midiSourcename(string1)) {
				this.midiChannel.play();
			} else {
				Source source2 = (Source)this.sourceMap.get(string1);
				if(source2 == null) {
					this.errorMessage("Source \'" + string1 + "\' not found in " + "method \'play\'");
				}

				this.play(source2);
			}

		} else {
			this.errorMessage("Sourcename not specified in method \'play\'");
		}
	}

	public void play(Source source1) {
		if(source1 != null) {
			if(!source1.rawDataStream) {
				if(source1.active()) {
					if(!source1.playing()) {
						Channel channel2 = this.getNextChannel(source1);
						if(source1 != null && channel2 != null) {
							if(source1.channel != null && source1.channel.attachedSource != source1) {
								source1.channel = null;
							}

							channel2.attachedSource = source1;
							source1.play(channel2);
							if(source1.toStream) {
								this.streamThread.watch(source1);
								this.streamThread.interrupt();
							}
						}
					}

				}
			}
		}
	}

	public void stop(String string1) {
		if(string1 != null && !string1.equals("")) {
			if(this.midiSourcename(string1)) {
				this.midiChannel.stop();
			} else {
				Source source2 = (Source)this.sourceMap.get(string1);
				if(source2 != null) {
					source2.stop();
				}
			}

		} else {
			this.errorMessage("Sourcename not specified in method \'stop\'");
		}
	}

	public void pause(String string1) {
		if(string1 != null && !string1.equals("")) {
			if(this.midiSourcename(string1)) {
				this.midiChannel.pause();
			} else {
				Source source2 = (Source)this.sourceMap.get(string1);
				if(source2 != null) {
					source2.pause();
				}
			}

		} else {
			this.errorMessage("Sourcename not specified in method \'stop\'");
		}
	}

	public void rewind(String string1) {
		if(this.midiSourcename(string1)) {
			this.midiChannel.rewind();
		} else {
			Source source2 = (Source)this.sourceMap.get(string1);
			if(source2 != null) {
				source2.rewind();
			}
		}

	}

	public void flush(String string1) {
		if(this.midiSourcename(string1)) {
			this.errorMessage("You can not flush the MIDI channel");
		} else {
			Source source2 = (Source)this.sourceMap.get(string1);
			if(source2 != null) {
				source2.flush();
			}
		}

	}

	public void cull(String string1) {
		Source source2 = (Source)this.sourceMap.get(string1);
		if(source2 != null) {
			source2.cull();
		}

	}

	public void activate(String string1) {
		Source source2 = (Source)this.sourceMap.get(string1);
		if(source2 != null) {
			source2.activate();
			if(source2.toPlay) {
				this.play(source2);
			}
		}

	}

	public void setMasterVolume(float f1) {
		SoundSystemConfig.setMasterGain(f1);
		if(this.midiChannel != null) {
			this.midiChannel.resetGain();
		}

	}

	public void setVolume(String string1, float f2) {
		if(this.midiSourcename(string1)) {
			this.midiChannel.setVolume(f2);
		} else {
			Source source3 = (Source)this.sourceMap.get(string1);
			if(source3 != null) {
				float f4 = f2;
				if(f2 < 0.0F) {
					f4 = 0.0F;
				} else if(f2 > 1.0F) {
					f4 = 1.0F;
				}

				source3.sourceVolume = f4;
				source3.positionChanged();
			}
		}

	}

	public float getVolume(String string1) {
		if(this.midiSourcename(string1)) {
			return this.midiChannel.getVolume();
		} else {
			Source source2 = (Source)this.sourceMap.get(string1);
			return source2 != null ? source2.sourceVolume : 0.0F;
		}
	}

	public void setPitch(String string1, float f2) {
		if(!this.midiSourcename(string1)) {
			Source source3 = (Source)this.sourceMap.get(string1);
			if(source3 != null) {
				float f4 = f2;
				if(f2 < 0.5F) {
					f4 = 0.5F;
				} else if(f2 > 2.0F) {
					f4 = 2.0F;
				}

				source3.setPitch(f4);
				source3.positionChanged();
			}
		}

	}

	public float getPitch(String string1) {
		if(!this.midiSourcename(string1)) {
			Source source2 = (Source)this.sourceMap.get(string1);
			if(source2 != null) {
				return source2.getPitch();
			}
		}

		return 1.0F;
	}

	public void moveListener(float f1, float f2, float f3) {
		this.setListenerPosition(this.listener.position.x + f1, this.listener.position.y + f2, this.listener.position.z + f3);
	}

	public void setListenerPosition(float f1, float f2, float f3) {
		this.listener.setPosition(f1, f2, f3);
		Set set4 = this.sourceMap.keySet();
		Iterator iterator5 = set4.iterator();

		while(iterator5.hasNext()) {
			String string6 = (String)iterator5.next();
			Source source7 = (Source)this.sourceMap.get(string6);
			if(source7 != null) {
				source7.positionChanged();
			}
		}

	}

	public void turnListener(float f1) {
		this.setListenerAngle(this.listener.angle + f1);
	}

	public void setListenerAngle(float f1) {
		this.listener.setAngle(f1);
		Set set2 = this.sourceMap.keySet();
		Iterator iterator3 = set2.iterator();

		while(iterator3.hasNext()) {
			String string4 = (String)iterator3.next();
			Source source5 = (Source)this.sourceMap.get(string4);
			if(source5 != null) {
				source5.positionChanged();
			}
		}

	}

	public void setListenerOrientation(float f1, float f2, float f3, float f4, float f5, float f6) {
		this.listener.setOrientation(f1, f2, f3, f4, f5, f6);
		Set set7 = this.sourceMap.keySet();
		Iterator iterator8 = set7.iterator();

		while(iterator8.hasNext()) {
			String string9 = (String)iterator8.next();
			Source source10 = (Source)this.sourceMap.get(string9);
			if(source10 != null) {
				source10.positionChanged();
			}
		}

	}

	public void setListenerData(ListenerData listenerData1) {
		this.listener.setData(listenerData1);
	}

	public void copySources(HashMap hashMap1) {
		if(hashMap1 != null) {
			Set set2 = hashMap1.keySet();
			Iterator iterator3 = set2.iterator();
			this.sourceMap.clear();

			while(iterator3.hasNext()) {
				String string4 = (String)iterator3.next();
				Source source5 = (Source)hashMap1.get(string4);
				if(source5 != null) {
					this.loadSound(source5.filenameURL);
					this.sourceMap.put(string4, new Source(source5, (SoundBuffer)null));
				}
			}

		}
	}

	public void removeSource(String string1) {
		Source source2 = (Source)this.sourceMap.get(string1);
		if(source2 != null) {
			source2.cleanup();
		}

		this.sourceMap.remove(string1);
	}

	public void removeTemporarySources() {
		Set set1 = this.sourceMap.keySet();
		Iterator iterator2 = set1.iterator();

		while(iterator2.hasNext()) {
			String string3 = (String)iterator2.next();
			Source source4 = (Source)this.sourceMap.get(string3);
			if(source4 != null && source4.temporary && !source4.playing()) {
				source4.cleanup();
				iterator2.remove();
			}
		}

	}

	private Channel getNextChannel(Source source1) {
		if(source1 == null) {
			return null;
		} else {
			String string2 = source1.sourcename;
			if(string2 == null) {
				return null;
			} else {
				int i5;
				List list6;
				String[] string7;
				if(source1.toStream) {
					i5 = this.nextStreamingChannel;
					list6 = this.streamingChannels;
					string7 = this.streamingChannelSourceNames;
				} else {
					i5 = this.nextNormalChannel;
					list6 = this.normalChannels;
					string7 = this.normalChannelSourceNames;
				}

				int i4 = list6.size();

				int i3;
				for(i3 = 0; i3 < i4; ++i3) {
					if(string2.equals(string7[i3])) {
						return (Channel)list6.get(i3);
					}
				}

				int i9 = i5;
				i3 = 0;

				while(true) {
					String string8;
					Source source10;
					if(i3 < i4) {
						string8 = string7[i9];
						if(string8 == null) {
							source10 = null;
						} else {
							source10 = (Source)this.sourceMap.get(string8);
						}

						if(source10 != null && source10.playing()) {
							++i9;
							if(i9 >= i4) {
								i9 = 0;
							}

							++i3;
							continue;
						}

						if(source1.toStream) {
							this.nextStreamingChannel = i9 + 1;
							if(this.nextStreamingChannel >= i4) {
								this.nextStreamingChannel = 0;
							}
						} else {
							this.nextNormalChannel = i9 + 1;
							if(this.nextNormalChannel >= i4) {
								this.nextNormalChannel = 0;
							}
						}

						string7[i9] = string2;
						return (Channel)list6.get(i9);
					}

					i9 = i5;

					for(i3 = 0; i3 < i4; ++i3) {
						string8 = string7[i9];
						if(string8 == null) {
							source10 = null;
						} else {
							source10 = (Source)this.sourceMap.get(string8);
						}

						if(source10 == null || !source10.playing() || !source10.priority) {
							if(source1.toStream) {
								this.nextStreamingChannel = i9 + 1;
								if(this.nextStreamingChannel >= i4) {
									this.nextStreamingChannel = 0;
								}
							} else {
								this.nextNormalChannel = i9 + 1;
								if(this.nextNormalChannel >= i4) {
									this.nextNormalChannel = 0;
								}
							}

							string7[i9] = string2;
							return (Channel)list6.get(i9);
						}

						++i9;
						if(i9 >= i4) {
							i9 = 0;
						}
					}

					return null;
				}
			}
		}
	}

	public void replaySources() {
		Set set1 = this.sourceMap.keySet();
		Iterator iterator2 = set1.iterator();

		while(iterator2.hasNext()) {
			String string3 = (String)iterator2.next();
			Source source4 = (Source)this.sourceMap.get(string3);
			if(source4 != null && source4.toPlay && !source4.playing()) {
				this.play(string3);
				source4.toPlay = false;
			}
		}

	}

	public void queueSound(String string1, FilenameURL filenameURL2) {
		if(this.midiSourcename(string1)) {
			this.midiChannel.queueSound(filenameURL2);
		} else {
			Source source3 = (Source)this.sourceMap.get(string1);
			if(source3 != null) {
				source3.queueSound(filenameURL2);
			}
		}

	}

	public void dequeueSound(String string1, String string2) {
		if(this.midiSourcename(string1)) {
			this.midiChannel.dequeueSound(string2);
		} else {
			Source source3 = (Source)this.sourceMap.get(string1);
			if(source3 != null) {
				source3.dequeueSound(string2);
			}
		}

	}

	public void fadeOut(String string1, FilenameURL filenameURL2, long j3) {
		if(this.midiSourcename(string1)) {
			this.midiChannel.fadeOut(filenameURL2, j3);
		} else {
			Source source5 = (Source)this.sourceMap.get(string1);
			if(source5 != null) {
				source5.fadeOut(filenameURL2, j3);
			}
		}

	}

	public void fadeOutIn(String string1, FilenameURL filenameURL2, long j3, long j5) {
		if(this.midiSourcename(string1)) {
			this.midiChannel.fadeOutIn(filenameURL2, j3, j5);
		} else {
			Source source7 = (Source)this.sourceMap.get(string1);
			if(source7 != null) {
				source7.fadeOutIn(filenameURL2, j3, j5);
			}
		}

	}

	public void checkFadeVolumes() {
		if(this.midiChannel != null) {
			this.midiChannel.resetGain();
		}

		Channel channel1;
		Source source2;
		for(int i3 = 0; i3 < this.streamingChannels.size(); ++i3) {
			channel1 = (Channel)this.streamingChannels.get(i3);
			if(channel1 != null) {
				source2 = channel1.attachedSource;
				if(source2 != null) {
					source2.checkFadeOut();
				}
			}
		}

		channel1 = null;
		source2 = null;
	}

	public void loadMidi(boolean z1, String string2, FilenameURL filenameURL3) {
		if(filenameURL3 == null) {
			this.errorMessage("Filename/URL not specified in method \'loadMidi\'.");
		} else if(!filenameURL3.getFilename().matches(".*[mM][iI][dD][iI]?$")) {
			this.errorMessage("Filename/identifier doesn\'t end in \'.mid\' or\'.midi\' in method loadMidi.");
		} else {
			if(this.midiChannel == null) {
				this.midiChannel = new MidiChannel(z1, string2, filenameURL3);
			} else {
				this.midiChannel.switchSource(z1, string2, filenameURL3);
			}

		}
	}

	public void unloadMidi() {
		if(this.midiChannel != null) {
			this.midiChannel.cleanup();
		}

		this.midiChannel = null;
	}

	public boolean midiSourcename(String string1) {
		return this.midiChannel != null && string1 != null ? (this.midiChannel.getSourcename() != null && !string1.equals("") ? string1.equals(this.midiChannel.getSourcename()) : false) : false;
	}

	public Source getSource(String string1) {
		return (Source)this.sourceMap.get(string1);
	}

	public MidiChannel getMidiChannel() {
		return this.midiChannel;
	}

	public void setMidiChannel(MidiChannel midiChannel1) {
		if(this.midiChannel != null && this.midiChannel != midiChannel1) {
			this.midiChannel.cleanup();
		}

		this.midiChannel = midiChannel1;
	}

	public void listenerMoved() {
		Set set1 = this.sourceMap.keySet();
		Iterator iterator2 = set1.iterator();

		while(iterator2.hasNext()) {
			String string3 = (String)iterator2.next();
			Source source4 = (Source)this.sourceMap.get(string3);
			if(source4 != null) {
				source4.listenerMoved();
			}
		}

	}

	public HashMap getSources() {
		return this.sourceMap;
	}

	public ListenerData getListenerData() {
		return this.listener;
	}

	public static String getTitle() {
		return "No Sound";
	}

	public static String getDescription() {
		return "Silent Mode";
	}

	public String getClassName() {
		return "Library";
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
