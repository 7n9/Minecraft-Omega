package paulscode.sound;

public class CommandThread extends SimpleThread {
	protected SoundSystemLogger logger = SoundSystemConfig.getLogger();
	private SoundSystem soundSystem;
	protected String className = "CommandThread";

	public CommandThread(SoundSystem soundSystem1) {
		this.soundSystem = soundSystem1;
	}

	protected void cleanup() {
		this.kill();
		this.logger = null;
		this.soundSystem = null;
		super.cleanup();
	}

	public void run() {
		long j1 = System.currentTimeMillis();
		if(this.soundSystem == null) {
			this.errorMessage("SoundSystem was null in method run().", 0);
			this.cleanup();
		} else {
			this.snooze(3600000L);

			while(!this.dying()) {
				this.soundSystem.ManageSources();
				this.soundSystem.CommandQueue((CommandObject)null);
				long j3 = System.currentTimeMillis();
				if(!this.dying() && j3 - j1 > 10000L) {
					j1 = j3;
					this.soundSystem.removeTemporarySources();
				}

				if(!this.dying()) {
					this.snooze(3600000L);
				}
			}

			this.cleanup();
		}
	}

	protected void message(String string1, int i2) {
		this.logger.message(string1, i2);
	}

	protected void importantMessage(String string1, int i2) {
		this.logger.importantMessage(string1, i2);
	}

	protected boolean errorCheck(boolean z1, String string2) {
		return this.logger.errorCheck(z1, this.className, string2, 0);
	}

	protected void errorMessage(String string1, int i2) {
		this.logger.errorMessage(this.className, string1, i2);
	}
}
