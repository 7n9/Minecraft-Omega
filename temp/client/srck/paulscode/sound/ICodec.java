package paulscode.sound;

import java.net.URL;
import javax.sound.sampled.AudioFormat;

public interface ICodec {
	void reverseByteOrder(boolean z1);

	boolean initialize(URL uRL1);

	boolean initialized();

	SoundBuffer read();

	SoundBuffer readAll();

	boolean endOfStream();

	void cleanup();

	AudioFormat getAudioFormat();
}
