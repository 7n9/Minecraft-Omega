package paulscode.sound;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class StreamThread extends SimpleThread {
	private SoundSystemLogger logger = SoundSystemConfig.getLogger();
	private List streamingSources = new LinkedList();
	private final Object listLock = new Object();

	protected void cleanup() {
		this.kill();
		super.cleanup();
	}

	public void run() {
		try {
			this.snooze(3600000L);

			while(!this.dying()) {
				while(!this.dying() && !this.streamingSources.isEmpty()) {
					Object object3 = this.listLock;
					synchronized(this.listLock) {
						ListIterator listIterator1 = this.streamingSources.listIterator();

						label211:
						while(true) {
							while(true) {
								if(this.dying() || !listIterator1.hasNext()) {
									break label211;
								}

								Source source2 = (Source)listIterator1.next();
								if(source2 == null) {
									listIterator1.remove();
								} else if(source2.stopped()) {
									if(!source2.rawDataStream) {
										listIterator1.remove();
									}
								} else if(source2.active()) {
									if(!source2.paused()) {
										source2.checkFadeOut();
										if(!source2.stream() && !source2.rawDataStream && (source2.channel == null || !source2.channel.processBuffer())) {
											if(source2.toLoop) {
												if(!source2.playing()) {
													if(source2.checkFadeOut()) {
														source2.preLoad = true;
													} else {
														source2.incrementSoundSequence();
														source2.preLoad = true;
													}
												}
											} else if(!source2.playing() && !source2.checkFadeOut()) {
												if(source2.incrementSoundSequence()) {
													source2.preLoad = true;
												} else {
													listIterator1.remove();
												}
											}
										}
									}
								} else {
									if(source2.toLoop || source2.rawDataStream) {
										source2.toPlay = true;
									}

									listIterator1.remove();
								}
							}
						}
					}

					if(!this.dying() && !this.streamingSources.isEmpty()) {
						this.snooze(20L);
					}
				}

				if(!this.dying() && this.streamingSources.isEmpty()) {
					this.snooze(3600000L);
				}
			}
		} finally {
			this.cleanup();
		}

	}

	public void watch(Source source1) {
		if(source1 != null) {
			if(!this.streamingSources.contains(source1)) {
				Object object4 = this.listLock;
				synchronized(this.listLock) {
					ListIterator listIterator2 = this.streamingSources.listIterator();

					while(listIterator2.hasNext()) {
						Source source3 = (Source)listIterator2.next();
						if(source3 == null) {
							listIterator2.remove();
						} else if(source1.channel == source3.channel) {
							source3.stop();
							listIterator2.remove();
						}
					}

					this.streamingSources.add(source1);
				}
			}
		}
	}

	private void message(String string1) {
		this.logger.message(string1, 0);
	}

	private void importantMessage(String string1) {
		this.logger.importantMessage(string1, 0);
	}

	private boolean errorCheck(boolean z1, String string2) {
		return this.logger.errorCheck(z1, "StreamThread", string2, 0);
	}

	private void errorMessage(String string1) {
		this.logger.errorMessage("StreamThread", string1, 0);
	}
}
