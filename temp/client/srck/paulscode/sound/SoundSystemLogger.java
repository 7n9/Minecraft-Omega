package paulscode.sound;

public class SoundSystemLogger {
	public void message(String string1, int i2) {
		String string4 = "";

		for(int i5 = 0; i5 < i2; ++i5) {
			string4 = string4 + "    ";
		}

		String string3 = string4 + string1;
		System.out.println(string3);
	}

	public void importantMessage(String string1, int i2) {
		String string4 = "";

		for(int i5 = 0; i5 < i2; ++i5) {
			string4 = string4 + "    ";
		}

		String string3 = string4 + string1;
		System.out.println(string3);
	}

	public boolean errorCheck(boolean z1, String string2, String string3, int i4) {
		if(z1) {
			this.errorMessage(string2, string3, i4);
		}

		return z1;
	}

	public void errorMessage(String string1, String string2, int i3) {
		String string6 = "";

		for(int i7 = 0; i7 < i3; ++i7) {
			string6 = string6 + "    ";
		}

		String string4 = string6 + "Error in class \'" + string1 + "\'";
		String string5 = "    " + string6 + string2;
		System.out.println(string4);
		System.out.println(string5);
	}

	public void printStackTrace(Exception exception1, int i2) {
		this.printExceptionMessage(exception1, i2);
		this.importantMessage("STACK TRACE:", i2);
		if(exception1 != null) {
			StackTraceElement[] stackTraceElement3 = exception1.getStackTrace();
			if(stackTraceElement3 != null) {
				for(int i5 = 0; i5 < stackTraceElement3.length; ++i5) {
					StackTraceElement stackTraceElement4 = stackTraceElement3[i5];
					if(stackTraceElement4 != null) {
						this.message(stackTraceElement4.toString(), i2 + 1);
					}
				}

			}
		}
	}

	public void printExceptionMessage(Exception exception1, int i2) {
		this.importantMessage("ERROR MESSAGE:", i2);
		if(exception1.getMessage() == null) {
			this.message("(none)", i2 + 1);
		} else {
			this.message(exception1.getMessage(), i2 + 1);
		}

	}
}
