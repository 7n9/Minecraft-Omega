package paulscode.sound;

import java.util.LinkedList;
import javax.sound.sampled.AudioFormat;

public class Channel {
	protected Class libraryType = Library.class;
	public int channelType;
	private SoundSystemLogger logger = SoundSystemConfig.getLogger();
	public Source attachedSource = null;

	public Channel(int i1) {
		this.channelType = i1;
	}

	public void cleanup() {
		this.logger = null;
	}

	public boolean preLoadBuffers(LinkedList linkedList1) {
		return true;
	}

	public boolean queueBuffer(byte[] b1) {
		return false;
	}

	public int feedRawAudioData(byte[] b1) {
		return 1;
	}

	public int buffersProcessed() {
		return 0;
	}

	public boolean processBuffer() {
		return false;
	}

	public void setAudioFormat(AudioFormat audioFormat1) {
	}

	public void flush() {
	}

	public void close() {
	}

	public void play() {
	}

	public void pause() {
	}

	public void stop() {
	}

	public void rewind() {
	}

	public boolean playing() {
		return false;
	}

	public String getClassName() {
		String string1 = SoundSystemConfig.getLibraryTitle(this.libraryType);
		return string1.equals("No Sound") ? "Channel" : "Channel" + string1;
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
