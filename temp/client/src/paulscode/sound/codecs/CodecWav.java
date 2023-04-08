package paulscode.sound.codecs;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import paulscode.sound.ICodec;
import paulscode.sound.SoundBuffer;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemLogger;

public class CodecWav implements ICodec {
	private static final boolean GET = false;
	private static final boolean SET = true;
	private static final boolean XXX = false;
	private boolean endOfStream = false;
	private boolean initialized = false;
	private AudioInputStream myAudioInputStream = null;
	private SoundSystemLogger logger = SoundSystemConfig.getLogger();

	public void reverseByteOrder(boolean z1) {
	}

	public boolean initialize(URL uRL1) {
		this.initialized(true, false);
		this.cleanup();
		if(uRL1 == null) {
			this.errorMessage("url null in method \'initialize\'");
			this.cleanup();
			return false;
		} else {
			try {
				this.myAudioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(uRL1.openStream()));
			} catch (UnsupportedAudioFileException unsupportedAudioFileException3) {
				this.errorMessage("Unsupported audio format in method \'initialize\'");
				this.printStackTrace(unsupportedAudioFileException3);
				return false;
			} catch (IOException iOException4) {
				this.errorMessage("Error setting up audio input stream in method \'initialize\'");
				this.printStackTrace(iOException4);
				return false;
			}

			this.endOfStream(true, false);
			this.initialized(true, true);
			return true;
		}
	}

	public boolean initialized() {
		return this.initialized(false, false);
	}

	public SoundBuffer read() {
		if(this.myAudioInputStream == null) {
			return null;
		} else {
			AudioFormat audioFormat1 = this.myAudioInputStream.getFormat();
			if(audioFormat1 == null) {
				this.errorMessage("Audio Format null in method \'read\'");
				return null;
			} else {
				int i2 = 0;
				boolean z3 = false;
				byte[] b4 = new byte[SoundSystemConfig.getStreamingBufferSize()];

				try {
					while(!this.endOfStream(false, false) && i2 < b4.length) {
						int i8;
						if((i8 = this.myAudioInputStream.read(b4, i2, b4.length - i2)) <= 0) {
							this.endOfStream(true, true);
							break;
						}

						i2 += i8;
					}
				} catch (IOException iOException7) {
					this.endOfStream(true, true);
					return null;
				}

				if(i2 <= 0) {
					return null;
				} else {
					if(i2 < b4.length) {
						b4 = trimArray(b4, i2);
					}

					byte[] b5 = convertAudioBytes(b4, audioFormat1.getSampleSizeInBits() == 16);
					SoundBuffer soundBuffer6 = new SoundBuffer(b5, audioFormat1);
					return soundBuffer6;
				}
			}
		}
	}

	public SoundBuffer readAll() {
		if(this.myAudioInputStream == null) {
			this.errorMessage("Audio input stream null in method \'readAll\'");
			return null;
		} else {
			AudioFormat audioFormat1 = this.myAudioInputStream.getFormat();
			if(audioFormat1 == null) {
				this.errorMessage("Audio Format null in method \'readAll\'");
				return null;
			} else {
				byte[] b2 = null;
				int i3 = audioFormat1.getChannels() * (int)this.myAudioInputStream.getFrameLength() * audioFormat1.getSampleSizeInBits() / 8;
				int i5;
				int i12;
				if(i3 > 0) {
					b2 = new byte[audioFormat1.getChannels() * (int)this.myAudioInputStream.getFrameLength() * audioFormat1.getSampleSizeInBits() / 8];
					boolean z4 = false;
					i5 = 0;

					try {
						while((i12 = this.myAudioInputStream.read(b2, i5, b2.length - i5)) != -1 && i5 < b2.length) {
							i5 += i12;
						}
					} catch (IOException iOException11) {
						this.errorMessage("Exception thrown while reading from the AudioInputStream (location #1).");
						this.printStackTrace(iOException11);
						return null;
					}
				} else {
					i12 = 0;
					boolean z14 = false;
					boolean z6 = false;
					Object object7 = null;

					for(byte[] b17 = new byte[SoundSystemConfig.getFileChunkSize()]; !this.endOfStream(false, false) && i12 < SoundSystemConfig.getMaxFileSize(); b2 = appendByteArrays(b2, b17, i5)) {
						i5 = 0;
						z6 = false;

						try {
							while(i5 < b17.length) {
								int i16;
								if((i16 = this.myAudioInputStream.read(b17, i5, b17.length - i5)) <= 0) {
									this.endOfStream(true, true);
									break;
								}

								i5 += i16;
							}
						} catch (IOException iOException10) {
							this.errorMessage("Exception thrown while reading from the AudioInputStream (location #2).");
							this.printStackTrace(iOException10);
							return null;
						}

						i12 += i5;
					}
				}

				byte[] b13 = convertAudioBytes(b2, audioFormat1.getSampleSizeInBits() == 16);
				SoundBuffer soundBuffer15 = new SoundBuffer(b13, audioFormat1);

				try {
					this.myAudioInputStream.close();
				} catch (IOException iOException9) {
				}

				return soundBuffer15;
			}
		}
	}

	public boolean endOfStream() {
		return this.endOfStream(false, false);
	}

	public void cleanup() {
		if(this.myAudioInputStream != null) {
			try {
				this.myAudioInputStream.close();
			} catch (Exception exception2) {
			}
		}

		this.myAudioInputStream = null;
	}

	public AudioFormat getAudioFormat() {
		return this.myAudioInputStream == null ? null : this.myAudioInputStream.getFormat();
	}

	private synchronized boolean initialized(boolean z1, boolean z2) {
		if(z1) {
			this.initialized = z2;
		}

		return this.initialized;
	}

	private synchronized boolean endOfStream(boolean z1, boolean z2) {
		if(z1) {
			this.endOfStream = z2;
		}

		return this.endOfStream;
	}

	private static byte[] trimArray(byte[] b0, int i1) {
		byte[] b2 = null;
		if(b0 != null && b0.length > i1) {
			b2 = new byte[i1];
			System.arraycopy(b0, 0, b2, 0, i1);
		}

		return b2;
	}

	private static byte[] convertAudioBytes(byte[] b0, boolean z1) {
		ByteBuffer byteBuffer2 = ByteBuffer.allocateDirect(b0.length);
		byteBuffer2.order(ByteOrder.nativeOrder());
		ByteBuffer byteBuffer3 = ByteBuffer.wrap(b0);
		byteBuffer3.order(ByteOrder.LITTLE_ENDIAN);
		if(z1) {
			ShortBuffer shortBuffer4 = byteBuffer2.asShortBuffer();
			ShortBuffer shortBuffer5 = byteBuffer3.asShortBuffer();

			while(shortBuffer5.hasRemaining()) {
				shortBuffer4.put(shortBuffer5.get());
			}
		} else {
			while(byteBuffer3.hasRemaining()) {
				byteBuffer2.put(byteBuffer3.get());
			}
		}

		byteBuffer2.rewind();
		if(!byteBuffer2.hasArray()) {
			byte[] b6 = new byte[byteBuffer2.capacity()];
			byteBuffer2.get(b6);
			byteBuffer2.clear();
			return b6;
		} else {
			return byteBuffer2.array();
		}
	}

	private static byte[] appendByteArrays(byte[] b0, byte[] b1, int i2) {
		if(b0 == null && b1 == null) {
			return null;
		} else {
			byte[] b3;
			Object object5;
			if(b0 == null) {
				b3 = new byte[i2];
				System.arraycopy(b1, 0, b3, 0, i2);
				object5 = null;
			} else {
				Object object4;
				if(b1 == null) {
					b3 = new byte[b0.length];
					System.arraycopy(b0, 0, b3, 0, b0.length);
					object4 = null;
				} else {
					b3 = new byte[b0.length + i2];
					System.arraycopy(b0, 0, b3, 0, b0.length);
					System.arraycopy(b1, 0, b3, b0.length, i2);
					object4 = null;
					object5 = null;
				}
			}

			return b3;
		}
	}

	private void errorMessage(String string1) {
		this.logger.errorMessage("CodecWav", string1, 0);
	}

	private void printStackTrace(Exception exception1) {
		this.logger.printStackTrace(exception1, 1);
	}
}
