package paulscode.sound;

import javax.sound.sampled.AudioFormat;

public class SoundBuffer {
	public byte[] audioData;
	public AudioFormat audioFormat;

	public SoundBuffer(byte[] b1, AudioFormat audioFormat2) {
		this.audioData = b1;
		this.audioFormat = audioFormat2;
	}

	public void cleanup() {
		this.audioData = null;
		this.audioFormat = null;
	}

	public void trimData(int i1) {
		if(this.audioData != null && i1 != 0) {
			if(this.audioData.length > i1) {
				byte[] b2 = new byte[i1];
				System.arraycopy(this.audioData, 0, b2, 0, i1);
				this.audioData = b2;
			}
		} else {
			this.audioData = null;
		}

	}
}
