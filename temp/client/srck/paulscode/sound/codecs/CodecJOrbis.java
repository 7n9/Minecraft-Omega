package paulscode.sound.codecs;

import com.jcraft.jogg.Packet;
import com.jcraft.jogg.Page;
import com.jcraft.jogg.StreamState;
import com.jcraft.jogg.SyncState;
import com.jcraft.jorbis.Block;
import com.jcraft.jorbis.Comment;
import com.jcraft.jorbis.DspState;
import com.jcraft.jorbis.Info;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownServiceException;
import java.nio.ByteOrder;
import javax.sound.sampled.AudioFormat;

import paulscode.sound.ICodec;
import paulscode.sound.SoundBuffer;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemLogger;

public class CodecJOrbis implements ICodec {
	private static final boolean GET = false;
	private static final boolean SET = true;
	private static final boolean XXX = false;
	protected URL url;
	protected URLConnection urlConnection = null;
	private InputStream inputStream;
	private AudioFormat audioFormat;
	private boolean endOfStream = false;
	private boolean initialized = false;
	private byte[] buffer = null;
	private int bufferSize;
	private int count = 0;
	private int index = 0;
	private int convertedBufferSize;
	private float[][][] pcmInfo;
	private int[] pcmIndex;
	private Packet joggPacket = new Packet();
	private Page joggPage = new Page();
	private StreamState joggStreamState = new StreamState();
	private SyncState joggSyncState = new SyncState();
	private DspState jorbisDspState = new DspState();
	private Block jorbisBlock = new Block(this.jorbisDspState);
	private Comment jorbisComment = new Comment();
	private Info jorbisInfo = new Info();
	private SoundSystemLogger logger = SoundSystemConfig.getLogger();
	private static final boolean LITTLE_ENDIAN = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN;

	public void reverseByteOrder(boolean z1) {
	}

	public boolean initialize(URL uRL1) {
		this.initialized(true, false);
		if(this.joggStreamState != null) {
			this.joggStreamState.clear();
		}

		if(this.jorbisBlock != null) {
			this.jorbisBlock.clear();
		}

		if(this.jorbisDspState != null) {
			this.jorbisDspState.clear();
		}

		if(this.jorbisInfo != null) {
			this.jorbisInfo.clear();
		}

		if(this.joggSyncState != null) {
			this.joggSyncState.clear();
		}

		if(this.inputStream != null) {
			try {
				this.inputStream.close();
			} catch (IOException iOException7) {
			}
		}

		this.url = uRL1;
		this.bufferSize = SoundSystemConfig.getStreamingBufferSize() / 2;
		this.buffer = null;
		this.count = 0;
		this.index = 0;
		this.joggStreamState = new StreamState();
		this.jorbisBlock = new Block(this.jorbisDspState);
		this.jorbisDspState = new DspState();
		this.jorbisInfo = new Info();
		this.joggSyncState = new SyncState();

		try {
			this.urlConnection = uRL1.openConnection();
		} catch (UnknownServiceException unknownServiceException5) {
			this.errorMessage("Unable to create a UrlConnection in method \'initialize\'.");
			this.printStackTrace(unknownServiceException5);
			this.cleanup();
			return false;
		} catch (IOException iOException6) {
			this.errorMessage("Unable to create a UrlConnection in method \'initialize\'.");
			this.printStackTrace(iOException6);
			this.cleanup();
			return false;
		}

		if(this.urlConnection != null) {
			try {
				this.inputStream = this.openInputStream();
			} catch (IOException iOException4) {
				this.errorMessage("Unable to acquire inputstream in method \'initialize\'.");
				this.printStackTrace(iOException4);
				this.cleanup();
				return false;
			}
		}

		this.endOfStream(true, false);
		this.joggSyncState.init();
		this.joggSyncState.buffer(this.bufferSize);
		this.buffer = this.joggSyncState.data;

		try {
			if(!this.readHeader()) {
				this.errorMessage("Error reading the header");
				return false;
			}
		} catch (IOException iOException8) {
			this.errorMessage("Error reading the header");
			return false;
		}

		this.convertedBufferSize = this.bufferSize * 2;
		this.jorbisDspState.synthesis_init(this.jorbisInfo);
		this.jorbisBlock.init(this.jorbisDspState);
		int i2 = this.jorbisInfo.channels;
		int i3 = this.jorbisInfo.rate;
		this.audioFormat = new AudioFormat((float)i3, 16, i2, true, false);
		this.pcmInfo = new float[1][][];
		this.pcmIndex = new int[this.jorbisInfo.channels];
		this.initialized(true, true);
		return true;
	}

	protected InputStream openInputStream() throws IOException {
		return this.urlConnection.getInputStream();
	}

	public boolean initialized() {
		return this.initialized(false, false);
	}

	public SoundBuffer read() {
		byte[] b1 = this.readBytes();
		return b1 == null ? null : new SoundBuffer(b1, this.audioFormat);
	}

	public SoundBuffer readAll() {
		byte[] b1 = this.readBytes();

		while(!this.endOfStream(false, false)) {
			b1 = appendByteArrays(b1, this.readBytes());
			if(b1 != null && b1.length >= SoundSystemConfig.getMaxFileSize()) {
				break;
			}
		}

		return new SoundBuffer(b1, this.audioFormat);
	}

	public boolean endOfStream() {
		return this.endOfStream(false, false);
	}

	public void cleanup() {
		this.joggStreamState.clear();
		this.jorbisBlock.clear();
		this.jorbisDspState.clear();
		this.jorbisInfo.clear();
		this.joggSyncState.clear();
		if(this.inputStream != null) {
			try {
				this.inputStream.close();
			} catch (IOException iOException2) {
			}
		}

		this.joggStreamState = null;
		this.jorbisBlock = null;
		this.jorbisDspState = null;
		this.jorbisInfo = null;
		this.joggSyncState = null;
		this.inputStream = null;
	}

	public AudioFormat getAudioFormat() {
		return this.audioFormat;
	}

	private boolean readHeader() throws IOException {
		this.index = this.joggSyncState.buffer(this.bufferSize);
		int i1 = this.inputStream.read(this.joggSyncState.data, this.index, this.bufferSize);
		if(i1 < 0) {
			i1 = 0;
		}

		this.joggSyncState.wrote(i1);
		if(this.joggSyncState.pageout(this.joggPage) != 1) {
			if(i1 < this.bufferSize) {
				return true;
			} else {
				this.errorMessage("Ogg header not recognized in method \'readHeader\'.");
				return false;
			}
		} else {
			this.joggStreamState.init(this.joggPage.serialno());
			this.jorbisInfo.init();
			this.jorbisComment.init();
			if(this.joggStreamState.pagein(this.joggPage) < 0) {
				this.errorMessage("Problem with first Ogg header page in method \'readHeader\'.");
				return false;
			} else if(this.joggStreamState.packetout(this.joggPacket) != 1) {
				this.errorMessage("Problem with first Ogg header packet in method \'readHeader\'.");
				return false;
			} else if(this.jorbisInfo.synthesis_headerin(this.jorbisComment, this.joggPacket) < 0) {
				this.errorMessage("File does not contain Vorbis header in method \'readHeader\'.");
				return false;
			} else {
				int i2 = 0;

				while(i2 < 2) {
					label73:
					while(true) {
						int i3;
						do {
							if(i2 >= 2) {
								break label73;
							}

							i3 = this.joggSyncState.pageout(this.joggPage);
							if(i3 == 0) {
								break label73;
							}
						} while(i3 != 1);

						this.joggStreamState.pagein(this.joggPage);

						while(i2 < 2) {
							i3 = this.joggStreamState.packetout(this.joggPacket);
							if(i3 == 0) {
								break;
							}

							if(i3 == -1) {
								this.errorMessage("Secondary Ogg header corrupt in method \'readHeader\'.");
								return false;
							}

							this.jorbisInfo.synthesis_headerin(this.jorbisComment, this.joggPacket);
							++i2;
						}
					}

					this.index = this.joggSyncState.buffer(this.bufferSize);
					i1 = this.inputStream.read(this.joggSyncState.data, this.index, this.bufferSize);
					if(i1 < 0) {
						i1 = 0;
					}

					if(i1 == 0 && i2 < 2) {
						this.errorMessage("End of file reached before finished readingOgg header in method \'readHeader\'");
						return false;
					}

					this.joggSyncState.wrote(i1);
				}

				this.index = this.joggSyncState.buffer(this.bufferSize);
				this.buffer = this.joggSyncState.data;
				return true;
			}
		}
	}

	private byte[] readBytes() {
		if(!this.initialized(false, false)) {
			return null;
		} else if(this.endOfStream(false, false)) {
			return null;
		} else {
			byte[] b1 = null;
			switch(this.joggSyncState.pageout(this.joggPage)) {
			case -1:
			case 0:
				this.endOfStream(true, true);
				break;
			case 1:
				this.joggStreamState.pagein(this.joggPage);
				if(this.joggPage.granulepos() == 0L) {
					this.endOfStream(true, true);
				} else {
					label47:
					while(true) {
						switch(this.joggStreamState.packetout(this.joggPacket)) {
						case -1:
						case 0:
							if(this.joggPage.eos() != 0) {
								this.endOfStream(true, true);
							}
							break label47;
						case 1:
							b1 = appendByteArrays(b1, this.decodeCurrentPacket());
						}
					}
				}
			}

			if(!this.endOfStream(false, false)) {
				this.index = this.joggSyncState.buffer(this.bufferSize);
				if(this.index == -1) {
					this.endOfStream(true, true);
				} else {
					this.buffer = this.joggSyncState.data;

					try {
						this.count = this.inputStream.read(this.buffer, this.index, this.bufferSize);
					} catch (Exception exception3) {
						this.printStackTrace(exception3);
						return b1;
					}

					this.joggSyncState.wrote(this.count);
					if(this.count == 0) {
						this.endOfStream(true, true);
					}
				}
			}

			return b1;
		}
	}

	private byte[] decodeCurrentPacket() {
		byte[] b1 = new byte[this.convertedBufferSize];
		if(this.jorbisBlock.synthesis(this.joggPacket) == 0) {
			this.jorbisDspState.synthesis_blockin(this.jorbisBlock);
		}

		int i4 = this.convertedBufferSize / (this.jorbisInfo.channels * 2);
		int i5 = 0;

		int i2;
		while(i5 < this.convertedBufferSize && (i2 = this.jorbisDspState.synthesis_pcmout(this.pcmInfo, this.pcmIndex)) > 0) {
			int i3;
			if(i2 < i4) {
				i3 = i2;
			} else {
				i3 = i4;
			}

			for(int i6 = 0; i6 < this.jorbisInfo.channels; ++i6) {
				int i7 = i6 * 2;

				for(int i8 = 0; i8 < i3; ++i8) {
					int i9 = (int)(this.pcmInfo[0][i6][this.pcmIndex[i6] + i8] * 32767.0F);
					if(i9 > 32767) {
						i9 = 32767;
					}

					if(i9 < -32768) {
						i9 = -32768;
					}

					if(i9 < 0) {
						i9 |= 32768;
					}

					if(LITTLE_ENDIAN) {
						b1[i5 + i7] = (byte)i9;
						b1[i5 + i7 + 1] = (byte)(i9 >>> 8);
					} else {
						b1[i5 + i7 + 1] = (byte)i9;
						b1[i5 + i7] = (byte)(i9 >>> 8);
					}

					i7 += 2 * this.jorbisInfo.channels;
				}
			}

			i5 += i3 * this.jorbisInfo.channels * 2;
			this.jorbisDspState.synthesis_read(i3);
		}

		b1 = trimArray(b1, i5);
		return b1;
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

	private static byte[] appendByteArrays(byte[] b0, byte[] b1) {
		if(b0 == null && b1 == null) {
			return null;
		} else {
			byte[] b2;
			Object object4;
			if(b0 == null) {
				b2 = new byte[b1.length];
				System.arraycopy(b1, 0, b2, 0, b1.length);
				object4 = null;
			} else {
				Object object3;
				if(b1 == null) {
					b2 = new byte[b0.length];
					System.arraycopy(b0, 0, b2, 0, b0.length);
					object3 = null;
				} else {
					b2 = new byte[b0.length + b1.length];
					System.arraycopy(b0, 0, b2, 0, b0.length);
					System.arraycopy(b1, 0, b2, b0.length, b1.length);
					object3 = null;
					object4 = null;
				}
			}

			return b2;
		}
	}

	private void errorMessage(String string1) {
		this.logger.errorMessage("CodecJOrbis", string1, 0);
	}

	private void printStackTrace(Exception exception1) {
		this.logger.printStackTrace(exception1, 1);
	}
}
