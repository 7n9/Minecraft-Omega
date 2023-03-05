package net.minecraft.src;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

public final class J_SajParser {
	public void func_27463_a(Reader reader1, J_JsonListener j_JsonListener2) throws IOException, J_InvalidSyntaxException {
		J_PositionTrackingPushbackReader j_PositionTrackingPushbackReader3 = new J_PositionTrackingPushbackReader(reader1);
		char c4 = (char)j_PositionTrackingPushbackReader3.read();
		switch(c4) {
		case '[':
			j_PositionTrackingPushbackReader3.func_27334_a(c4);
			j_JsonListener2.func_27195_b();
			this.arrayString(j_PositionTrackingPushbackReader3, j_JsonListener2);
			break;
		case '{':
			j_PositionTrackingPushbackReader3.func_27334_a(c4);
			j_JsonListener2.func_27195_b();
			this.objectString(j_PositionTrackingPushbackReader3, j_JsonListener2);
			break;
		default:
			throw new J_InvalidSyntaxException("Expected either [ or { but got [" + c4 + "].", j_PositionTrackingPushbackReader3);
		}

		int i5 = this.readNextNonWhitespaceChar(j_PositionTrackingPushbackReader3);
		if(i5 != -1) {
			throw new J_InvalidSyntaxException("Got unexpected trailing character [" + (char)i5 + "].", j_PositionTrackingPushbackReader3);
		} else {
			j_JsonListener2.func_27204_c();
		}
	}

	private void arrayString(J_PositionTrackingPushbackReader j_PositionTrackingPushbackReader1, J_JsonListener j_JsonListener2) throws IOException, J_InvalidSyntaxException {
		char c3 = (char)this.readNextNonWhitespaceChar(j_PositionTrackingPushbackReader1);
		if(c3 != 91) {
			throw new J_InvalidSyntaxException("Expected object to start with [ but got [" + c3 + "].", j_PositionTrackingPushbackReader1);
		} else {
			j_JsonListener2.func_27200_d();
			char c4 = (char)this.readNextNonWhitespaceChar(j_PositionTrackingPushbackReader1);
			j_PositionTrackingPushbackReader1.func_27334_a(c4);
			if(c4 != 93) {
				this.aJsonValue(j_PositionTrackingPushbackReader1, j_JsonListener2);
			}

			boolean z5 = false;

			while(!z5) {
				char c6 = (char)this.readNextNonWhitespaceChar(j_PositionTrackingPushbackReader1);
				switch(c6) {
				case ',':
					this.aJsonValue(j_PositionTrackingPushbackReader1, j_JsonListener2);
					break;
				case ']':
					z5 = true;
					break;
				default:
					throw new J_InvalidSyntaxException("Expected either , or ] but got [" + c6 + "].", j_PositionTrackingPushbackReader1);
				}
			}

			j_JsonListener2.func_27197_e();
		}
	}

	private void objectString(J_PositionTrackingPushbackReader j_PositionTrackingPushbackReader1, J_JsonListener j_JsonListener2) throws IOException, J_InvalidSyntaxException {
		char c3 = (char)this.readNextNonWhitespaceChar(j_PositionTrackingPushbackReader1);
		if(c3 != 123) {
			throw new J_InvalidSyntaxException("Expected object to start with { but got [" + c3 + "].", j_PositionTrackingPushbackReader1);
		} else {
			j_JsonListener2.func_27194_f();
			char c4 = (char)this.readNextNonWhitespaceChar(j_PositionTrackingPushbackReader1);
			j_PositionTrackingPushbackReader1.func_27334_a(c4);
			if(c4 != 125) {
				this.aFieldToken(j_PositionTrackingPushbackReader1, j_JsonListener2);
			}

			boolean z5 = false;

			while(!z5) {
				char c6 = (char)this.readNextNonWhitespaceChar(j_PositionTrackingPushbackReader1);
				switch(c6) {
				case ',':
					this.aFieldToken(j_PositionTrackingPushbackReader1, j_JsonListener2);
					break;
				case '}':
					z5 = true;
					break;
				default:
					throw new J_InvalidSyntaxException("Expected either , or } but got [" + c6 + "].", j_PositionTrackingPushbackReader1);
				}
			}

			j_JsonListener2.func_27203_g();
		}
	}

	private void aFieldToken(J_PositionTrackingPushbackReader j_PositionTrackingPushbackReader1, J_JsonListener j_JsonListener2) throws IOException, J_InvalidSyntaxException {
		char c3 = (char)this.readNextNonWhitespaceChar(j_PositionTrackingPushbackReader1);
		if(34 != c3) {
			throw new J_InvalidSyntaxException("Expected object identifier to begin with [\"] but got [" + c3 + "].", j_PositionTrackingPushbackReader1);
		} else {
			j_PositionTrackingPushbackReader1.func_27334_a(c3);
			j_JsonListener2.func_27205_a(this.func_27452_i(j_PositionTrackingPushbackReader1));
			char c4 = (char)this.readNextNonWhitespaceChar(j_PositionTrackingPushbackReader1);
			if(c4 != 58) {
				throw new J_InvalidSyntaxException("Expected object identifier to be followed by : but got [" + c4 + "].", j_PositionTrackingPushbackReader1);
			} else {
				this.aJsonValue(j_PositionTrackingPushbackReader1, j_JsonListener2);
				j_JsonListener2.func_27199_h();
			}
		}
	}

	private void aJsonValue(J_PositionTrackingPushbackReader j_PositionTrackingPushbackReader1, J_JsonListener j_JsonListener2) throws IOException, J_InvalidSyntaxException {
		char c3 = (char)this.readNextNonWhitespaceChar(j_PositionTrackingPushbackReader1);
		switch(c3) {
		case '\"':
			j_PositionTrackingPushbackReader1.func_27334_a(c3);
			j_JsonListener2.func_27198_c(this.func_27452_i(j_PositionTrackingPushbackReader1));
			break;
		case '-':
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
			j_PositionTrackingPushbackReader1.func_27334_a(c3);
			j_JsonListener2.func_27201_b(this.numberToken(j_PositionTrackingPushbackReader1));
			break;
		case '[':
			j_PositionTrackingPushbackReader1.func_27334_a(c3);
			this.arrayString(j_PositionTrackingPushbackReader1, j_JsonListener2);
			break;
		case 'f':
			char[] c6 = new char[4];
			int i7 = j_PositionTrackingPushbackReader1.func_27336_b(c6);
			if(i7 == 4 && c6[0] == 97 && c6[1] == 108 && c6[2] == 115 && c6[3] == 101) {
				j_JsonListener2.func_27193_j();
				break;
			}

			j_PositionTrackingPushbackReader1.func_27335_a(c6);
			throw new J_InvalidSyntaxException("Expected \'f\' to be followed by [[a, l, s, e]], but got [" + Arrays.toString(c6) + "].", j_PositionTrackingPushbackReader1);
		case 'n':
			char[] c8 = new char[3];
			int i9 = j_PositionTrackingPushbackReader1.func_27336_b(c8);
			if(i9 != 3 || c8[0] != 117 || c8[1] != 108 || c8[2] != 108) {
				j_PositionTrackingPushbackReader1.func_27335_a(c8);
				throw new J_InvalidSyntaxException("Expected \'n\' to be followed by [[u, l, l]], but got [" + Arrays.toString(c8) + "].", j_PositionTrackingPushbackReader1);
			}

			j_JsonListener2.func_27202_k();
			break;
		case 't':
			char[] c4 = new char[3];
			int i5 = j_PositionTrackingPushbackReader1.func_27336_b(c4);
			if(i5 != 3 || c4[0] != 114 || c4[1] != 117 || c4[2] != 101) {
				j_PositionTrackingPushbackReader1.func_27335_a(c4);
				throw new J_InvalidSyntaxException("Expected \'t\' to be followed by [[r, u, e]], but got [" + Arrays.toString(c4) + "].", j_PositionTrackingPushbackReader1);
			}

			j_JsonListener2.func_27196_i();
			break;
		case '{':
			j_PositionTrackingPushbackReader1.func_27334_a(c3);
			this.objectString(j_PositionTrackingPushbackReader1, j_JsonListener2);
			break;
		default:
			throw new J_InvalidSyntaxException("Invalid character at start of value [" + c3 + "].", j_PositionTrackingPushbackReader1);
		}

	}

	private String numberToken(J_PositionTrackingPushbackReader j_PositionTrackingPushbackReader1) throws IOException, J_InvalidSyntaxException {
		StringBuilder stringBuilder2 = new StringBuilder();
		char c3 = (char)j_PositionTrackingPushbackReader1.read();
		if(45 == c3) {
			stringBuilder2.append('-');
		} else {
			j_PositionTrackingPushbackReader1.func_27334_a(c3);
		}

		stringBuilder2.append(this.nonNegativeNumberToken(j_PositionTrackingPushbackReader1));
		return stringBuilder2.toString();
	}

	private String nonNegativeNumberToken(J_PositionTrackingPushbackReader j_PositionTrackingPushbackReader1) throws IOException, J_InvalidSyntaxException {
		StringBuilder stringBuilder2 = new StringBuilder();
		char c3 = (char)j_PositionTrackingPushbackReader1.read();
		if(48 == c3) {
			stringBuilder2.append('0');
			stringBuilder2.append(this.possibleFractionalComponent(j_PositionTrackingPushbackReader1));
			stringBuilder2.append(this.possibleExponent(j_PositionTrackingPushbackReader1));
		} else {
			j_PositionTrackingPushbackReader1.func_27334_a(c3);
			stringBuilder2.append(this.nonZeroDigitToken(j_PositionTrackingPushbackReader1));
			stringBuilder2.append(this.digitString(j_PositionTrackingPushbackReader1));
			stringBuilder2.append(this.possibleFractionalComponent(j_PositionTrackingPushbackReader1));
			stringBuilder2.append(this.possibleExponent(j_PositionTrackingPushbackReader1));
		}

		return stringBuilder2.toString();
	}

	private char nonZeroDigitToken(J_PositionTrackingPushbackReader j_PositionTrackingPushbackReader1) throws IOException, J_InvalidSyntaxException {
		char c3 = (char)j_PositionTrackingPushbackReader1.read();
		switch(c3) {
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
			return c3;
		default:
			throw new J_InvalidSyntaxException("Expected a digit 1 - 9 but got [" + c3 + "].", j_PositionTrackingPushbackReader1);
		}
	}

	private char digitToken(J_PositionTrackingPushbackReader j_PositionTrackingPushbackReader1) throws IOException, J_InvalidSyntaxException {
		char c3 = (char)j_PositionTrackingPushbackReader1.read();
		switch(c3) {
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
			return c3;
		default:
			throw new J_InvalidSyntaxException("Expected a digit 1 - 9 but got [" + c3 + "].", j_PositionTrackingPushbackReader1);
		}
	}

	private String digitString(J_PositionTrackingPushbackReader j_PositionTrackingPushbackReader1) throws IOException {
		StringBuilder stringBuilder2 = new StringBuilder();
		boolean z3 = false;

		while(!z3) {
			char c4 = (char)j_PositionTrackingPushbackReader1.read();
			switch(c4) {
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				stringBuilder2.append(c4);
				break;
			default:
				z3 = true;
				j_PositionTrackingPushbackReader1.func_27334_a(c4);
			}
		}

		return stringBuilder2.toString();
	}

	private String possibleFractionalComponent(J_PositionTrackingPushbackReader j_PositionTrackingPushbackReader1) throws IOException, J_InvalidSyntaxException {
		StringBuilder stringBuilder2 = new StringBuilder();
		char c3 = (char)j_PositionTrackingPushbackReader1.read();
		if(c3 == 46) {
			stringBuilder2.append('.');
			stringBuilder2.append(this.digitToken(j_PositionTrackingPushbackReader1));
			stringBuilder2.append(this.digitString(j_PositionTrackingPushbackReader1));
		} else {
			j_PositionTrackingPushbackReader1.func_27334_a(c3);
		}

		return stringBuilder2.toString();
	}

	private String possibleExponent(J_PositionTrackingPushbackReader j_PositionTrackingPushbackReader1) throws IOException, J_InvalidSyntaxException {
		StringBuilder stringBuilder2 = new StringBuilder();
		char c3 = (char)j_PositionTrackingPushbackReader1.read();
		if(c3 != 46 && c3 != 69) {
			j_PositionTrackingPushbackReader1.func_27334_a(c3);
		} else {
			stringBuilder2.append('E');
			stringBuilder2.append(this.possibleSign(j_PositionTrackingPushbackReader1));
			stringBuilder2.append(this.digitToken(j_PositionTrackingPushbackReader1));
			stringBuilder2.append(this.digitString(j_PositionTrackingPushbackReader1));
		}

		return stringBuilder2.toString();
	}

	private String possibleSign(J_PositionTrackingPushbackReader j_PositionTrackingPushbackReader1) throws IOException {
		StringBuilder stringBuilder2 = new StringBuilder();
		char c3 = (char)j_PositionTrackingPushbackReader1.read();
		if(c3 != 43 && c3 != 45) {
			j_PositionTrackingPushbackReader1.func_27334_a(c3);
		} else {
			stringBuilder2.append(c3);
		}

		return stringBuilder2.toString();
	}

	private String func_27452_i(J_PositionTrackingPushbackReader j_PositionTrackingPushbackReader1) throws IOException, J_InvalidSyntaxException {
		StringBuilder stringBuilder2 = new StringBuilder();
		char c3 = (char)j_PositionTrackingPushbackReader1.read();
		if(34 != c3) {
			throw new J_InvalidSyntaxException("Expected [\"] but got [" + c3 + "].", j_PositionTrackingPushbackReader1);
		} else {
			boolean z4 = false;

			while(!z4) {
				char c5 = (char)j_PositionTrackingPushbackReader1.read();
				switch(c5) {
				case '\"':
					z4 = true;
					break;
				case '\\':
					char c6 = this.escapedStringChar(j_PositionTrackingPushbackReader1);
					stringBuilder2.append(c6);
					break;
				default:
					stringBuilder2.append(c5);
				}
			}

			return stringBuilder2.toString();
		}
	}

	private char escapedStringChar(J_PositionTrackingPushbackReader j_PositionTrackingPushbackReader1) throws IOException, J_InvalidSyntaxException {
		char c3 = (char)j_PositionTrackingPushbackReader1.read();
		char c2;
		switch(c3) {
		case '\"':
			c2 = 34;
			break;
		case '/':
			c2 = 47;
			break;
		case '\\':
			c2 = 92;
			break;
		case 'b':
			c2 = 8;
			break;
		case 'f':
			c2 = 12;
			break;
		case 'n':
			c2 = 10;
			break;
		case 'r':
			c2 = 13;
			break;
		case 't':
			c2 = 9;
			break;
		case 'u':
			c2 = (char)this.hexadecimalNumber(j_PositionTrackingPushbackReader1);
			break;
		default:
			throw new J_InvalidSyntaxException("Unrecognised escape character [" + c3 + "].", j_PositionTrackingPushbackReader1);
		}

		return c2;
	}

	private int hexadecimalNumber(J_PositionTrackingPushbackReader j_PositionTrackingPushbackReader1) throws IOException, J_InvalidSyntaxException {
		char[] c2 = new char[4];
		int i3 = j_PositionTrackingPushbackReader1.func_27336_b(c2);
		if(i3 != 4) {
			throw new J_InvalidSyntaxException("Expected a 4 digit hexidecimal number but got only [" + i3 + "], namely [" + String.valueOf(c2, 0, i3) + "].", j_PositionTrackingPushbackReader1);
		} else {
			try {
				int i4 = Integer.parseInt(String.valueOf(c2), 16);
				return i4;
			} catch (NumberFormatException numberFormatException6) {
				j_PositionTrackingPushbackReader1.func_27335_a(c2);
				throw new J_InvalidSyntaxException("Unable to parse [" + String.valueOf(c2) + "] as a hexidecimal number.", numberFormatException6, j_PositionTrackingPushbackReader1);
			}
		}
	}

	private int readNextNonWhitespaceChar(J_PositionTrackingPushbackReader j_PositionTrackingPushbackReader1) throws IOException {
		boolean z3 = false;

		int i2;
		do {
			i2 = j_PositionTrackingPushbackReader1.read();
			switch(i2) {
			case 9:
			case 10:
			case 13:
			case 32:
				break;
			default:
				z3 = true;
			}
		} while(!z3);

		return i2;
	}
}
