package paulscode.sound;

import java.net.URL;

public class FilenameURL {
	private SoundSystemLogger logger = SoundSystemConfig.getLogger();
	private String filename = null;
	private URL url = null;

	public FilenameURL(URL uRL1, String string2) {
		this.filename = string2;
		this.url = uRL1;
	}

	public FilenameURL(String string1) {
		this.filename = string1;
		this.url = null;
	}

	public String getFilename() {
		return this.filename;
	}

	public URL getURL() {
		if(this.url == null) {
			if(this.filename.matches("^[hH][tT][tT][pP]://.*")) {
				try {
					this.url = new URL(this.filename);
				} catch (Exception exception2) {
					this.errorMessage("Unable to access online URL in method \'getURL\'");
					this.printStackTrace(exception2);
					return null;
				}
			} else {
				this.url = this.getClass().getClassLoader().getResource(SoundSystemConfig.getSoundFilesPackage() + this.filename);
			}
		}

		return this.url;
	}

	private void errorMessage(String string1) {
		this.logger.errorMessage("MidiChannel", string1, 0);
	}

	private void printStackTrace(Exception exception1) {
		this.logger.printStackTrace(exception1, 1);
	}
}
