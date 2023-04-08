package paulscode.sound;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Locale;

public class SoundSystemConfig {
	public static final Object THREAD_SYNC = new Object();
	public static final int TYPE_NORMAL = 0;
	public static final int TYPE_STREAMING = 1;
	public static final int ATTENUATION_NONE = 0;
	public static final int ATTENUATION_ROLLOFF = 1;
	public static final int ATTENUATION_LINEAR = 2;
	public static final String EXTENSION_MIDI = ".*[mM][iI][dD][iI]?$";
	public static final String PREFIX_URL = "^[hH][tT][tT][pP]://.*";
	private static SoundSystemLogger logger = null;
	private static LinkedList libraries;
	private static LinkedList codecs = null;
	private static int numberNormalChannels = 28;
	private static int numberStreamingChannels = 4;
	private static float masterGain = 1.0F;
	private static int defaultAttenuationModel = 1;
	private static float defaultRolloffFactor = 0.03F;
	private static float defaultFadeDistance = 1000.0F;
	private static String soundFilesPackage = "Sounds/";
	private static int streamingBufferSize = 131072;
	private static int numberStreamingBuffers = 3;
	private static int maxFileSize = 268435456;
	private static int fileChunkSize = 1048576;
	private static boolean midiCodec = false;

	public static void addLibrary(Class class0) throws SoundSystemException {
		if(class0 == null) {
			throw new SoundSystemException("Parameter null in method \'addLibrary\'", 2);
		} else if(!Library.class.isAssignableFrom(class0)) {
			throw new SoundSystemException("The specified class does not extend class \'Library\' in method \'addLibrary\'");
		} else {
			if(libraries == null) {
				libraries = new LinkedList();
			}

			if(!libraries.contains(class0)) {
				libraries.add(class0);
			}

		}
	}

	public static void removeLibrary(Class class0) {
		if(libraries != null && class0 != null) {
			libraries.remove(class0);
		}
	}

	public static LinkedList getLibraries() {
		return libraries;
	}

	public static boolean libraryCompatible(Class class0) {
		if(class0 == null) {
			errorMessage("Parameter \'libraryClass\' null in method\'librayCompatible\'");
			return false;
		} else if(!Library.class.isAssignableFrom(class0)) {
			errorMessage("The specified class does not extend class \'Library\' in method \'libraryCompatible\'");
			return false;
		} else {
			Object object1 = runMethod(class0, "libraryCompatible", new Class[0], new Object[0]);
			if(object1 == null) {
				errorMessage("Method \'Library.libraryCompatible\' returned \'null\' in method \'libraryCompatible\'");
				return false;
			} else {
				return ((Boolean)object1).booleanValue();
			}
		}
	}

	public static String getLibraryTitle(Class class0) {
		if(class0 == null) {
			errorMessage("Parameter \'libraryClass\' null in method\'getLibrayTitle\'");
			return null;
		} else if(!Library.class.isAssignableFrom(class0)) {
			errorMessage("The specified class does not extend class \'Library\' in method \'getLibraryTitle\'");
			return null;
		} else {
			Object object1 = runMethod(class0, "getTitle", new Class[0], new Object[0]);
			if(object1 == null) {
				errorMessage("Method \'Library.getTitle\' returned \'null\' in method \'getLibraryTitle\'");
				return null;
			} else {
				return (String)object1;
			}
		}
	}

	public static String getLibraryDescription(Class class0) {
		if(class0 == null) {
			errorMessage("Parameter \'libraryClass\' null in method\'getLibrayDescription\'");
			return null;
		} else if(!Library.class.isAssignableFrom(class0)) {
			errorMessage("The specified class does not extend class \'Library\' in method \'getLibraryDescription\'");
			return null;
		} else {
			Object object1 = runMethod(class0, "getDescription", new Class[0], new Object[0]);
			if(object1 == null) {
				errorMessage("Method \'Library.getDescription\' returned \'null\' in method \'getLibraryDescription\'");
				return null;
			} else {
				return (String)object1;
			}
		}
	}

	public static void setLogger(SoundSystemLogger soundSystemLogger0) {
		logger = soundSystemLogger0;
	}

	public static SoundSystemLogger getLogger() {
		return logger;
	}

	public static synchronized void setNumberNormalChannels(int i0) {
		numberNormalChannels = i0;
	}

	public static synchronized int getNumberNormalChannels() {
		return numberNormalChannels;
	}

	public static synchronized void setNumberStreamingChannels(int i0) {
		numberStreamingChannels = i0;
	}

	public static synchronized int getNumberStreamingChannels() {
		return numberStreamingChannels;
	}

	public static synchronized void setMasterGain(float f0) {
		masterGain = f0;
	}

	public static synchronized float getMasterGain() {
		return masterGain;
	}

	public static synchronized void setDefaultAttenuation(int i0) {
		defaultAttenuationModel = i0;
	}

	public static synchronized int getDefaultAttenuation() {
		return defaultAttenuationModel;
	}

	public static synchronized void setDefaultRolloff(float f0) {
		defaultRolloffFactor = f0;
	}

	public static synchronized float getDefaultRolloff() {
		return defaultRolloffFactor;
	}

	public static synchronized void setDefaultFadeDistance(float f0) {
		defaultFadeDistance = f0;
	}

	public static synchronized float getDefaultFadeDistance() {
		return defaultFadeDistance;
	}

	public static synchronized void setSoundFilesPackage(String string0) {
		soundFilesPackage = string0;
	}

	public static synchronized String getSoundFilesPackage() {
		return soundFilesPackage;
	}

	public static synchronized void setStreamingBufferSize(int i0) {
		streamingBufferSize = i0;
	}

	public static synchronized int getStreamingBufferSize() {
		return streamingBufferSize;
	}

	public static synchronized void setNumberStreamingBuffers(int i0) {
		numberStreamingBuffers = i0;
	}

	public static synchronized int getNumberStreamingBuffers() {
		return numberStreamingBuffers;
	}

	public static synchronized void setMaxFileSize(int i0) {
		maxFileSize = i0;
	}

	public static synchronized int getMaxFileSize() {
		return maxFileSize;
	}

	public static synchronized void setFileChunkSize(int i0) {
		fileChunkSize = i0;
	}

	public static synchronized int getFileChunkSize() {
		return fileChunkSize;
	}

	public static synchronized void setCodec(String string0, Class class1) throws SoundSystemException {
		if(string0 == null) {
			throw new SoundSystemException("Parameter \'extension\' null in method \'setCodec\'.", 2);
		} else if(class1 == null) {
			throw new SoundSystemException("Parameter \'iCodecClass\' null in method \'setCodec\'.", 2);
		} else if(!ICodec.class.isAssignableFrom(class1)) {
			throw new SoundSystemException("The specified class does not implement interface \'ICodec\' in method \'setCodec\'", 3);
		} else {
			if(codecs == null) {
				codecs = new LinkedList();
			}

			ListIterator listIterator2 = codecs.listIterator();

			while(listIterator2.hasNext()) {
				SoundSystemConfig.Codec soundSystemConfig$Codec3 = (SoundSystemConfig.Codec)listIterator2.next();
				if(string0.matches(soundSystemConfig$Codec3.extensionRegX)) {
					listIterator2.remove();
				}
			}

			codecs.add(new SoundSystemConfig.Codec(string0, class1));
			if(string0.matches(".*[mM][iI][dD][iI]?$")) {
				midiCodec = true;
			}

		}
	}

	public static synchronized ICodec getCodec(String string0) {
		if(codecs == null) {
			return null;
		} else {
			ListIterator listIterator1 = codecs.listIterator();

			SoundSystemConfig.Codec soundSystemConfig$Codec2;
			do {
				if(!listIterator1.hasNext()) {
					return null;
				}

				soundSystemConfig$Codec2 = (SoundSystemConfig.Codec)listIterator1.next();
			} while(!string0.matches(soundSystemConfig$Codec2.extensionRegX));

			return soundSystemConfig$Codec2.getInstance();
		}
	}

	public static boolean midiCodec() {
		return midiCodec;
	}

	private static void errorMessage(String string0) {
		if(logger != null) {
			logger.errorMessage("SoundSystemConfig", string0, 0);
		}

	}

	private static Object runMethod(Class class0, String string1, Class[] class2, Object[] object3) {
		Method method4 = null;

		try {
			method4 = class0.getMethod(string1, class2);
		} catch (NoSuchMethodException noSuchMethodException12) {
			errorMessage("NoSuchMethodException thrown when attempting to call method \'" + string1 + "\' in " + "method \'runMethod\'");
			return null;
		} catch (SecurityException securityException13) {
			errorMessage("Access denied when attempting to call method \'" + string1 + "\' in method \'runMethod\'");
			return null;
		} catch (NullPointerException nullPointerException14) {
			errorMessage("NullPointerException thrown when attempting to call method \'" + string1 + "\' in " + "method \'runMethod\'");
			return null;
		}

		if(method4 == null) {
			errorMessage("Method \'" + string1 + "\' not found for the class " + "specified in method \'runMethod\'");
			return null;
		} else {
			Object object5 = null;

			try {
				object5 = method4.invoke((Object)null, object3);
				return object5;
			} catch (IllegalAccessException illegalAccessException7) {
				errorMessage("IllegalAccessException thrown when attempting to invoke method \'" + string1 + "\' in " + "method \'runMethod\'");
				return null;
			} catch (IllegalArgumentException illegalArgumentException8) {
				errorMessage("IllegalArgumentException thrown when attempting to invoke method \'" + string1 + "\' in " + "method \'runMethod\'");
				return null;
			} catch (InvocationTargetException invocationTargetException9) {
				errorMessage("InvocationTargetException thrown while attempting to invoke method \'Library.getTitle\' in method \'getLibraryTitle\'");
				return null;
			} catch (NullPointerException nullPointerException10) {
				errorMessage("NullPointerException thrown when attempting to invoke method \'" + string1 + "\' in " + "method \'runMethod\'");
				return null;
			} catch (ExceptionInInitializerError exceptionInInitializerError11) {
				errorMessage("ExceptionInInitializerError thrown when attempting to invoke method \'" + string1 + "\' in " + "method \'runMethod\'");
				return null;
			}
		}
	}

	static class Codec {
		public String extensionRegX = "";
		public Class iCodecClass;

		public Codec(String string1, Class class2) {
			if(string1 != null && string1.length() > 0) {
				this.extensionRegX = ".*";

				for(int i4 = 0; i4 < string1.length(); ++i4) {
					String string3 = string1.substring(i4, i4 + 1);
					this.extensionRegX = this.extensionRegX + "[" + string3.toLowerCase(Locale.ENGLISH) + string3.toUpperCase(Locale.ENGLISH) + "]";
				}

				this.extensionRegX = this.extensionRegX + "$";
			}

			this.iCodecClass = class2;
		}

		public ICodec getInstance() {
			if(this.iCodecClass == null) {
				return null;
			} else {
				Object object1 = null;

				try {
					object1 = this.iCodecClass.newInstance();
				} catch (InstantiationException instantiationException3) {
					this.instantiationErrorMessage();
					return null;
				} catch (IllegalAccessException illegalAccessException4) {
					this.instantiationErrorMessage();
					return null;
				} catch (ExceptionInInitializerError exceptionInInitializerError5) {
					this.instantiationErrorMessage();
					return null;
				} catch (SecurityException securityException6) {
					this.instantiationErrorMessage();
					return null;
				}

				if(object1 == null) {
					this.instantiationErrorMessage();
					return null;
				} else {
					return (ICodec)object1;
				}
			}
		}

		private void instantiationErrorMessage() {
			SoundSystemConfig.errorMessage("Unrecognized ICodec implementation in method \'getInstance\'.  Ensure that the implementing class has one public, parameterless constructor.");
		}
	}
}
