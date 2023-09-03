package net.minecraft.src;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.TreeSet;

public final class J_CompactJsonFormatter implements J_JsonFormatter {
	public String format(J_JsonRootNode j_JsonRootNode1) {
		StringWriter stringWriter2 = new StringWriter();

		try {
			this.format(j_JsonRootNode1, stringWriter2);
		} catch (IOException iOException4) {
			throw new RuntimeException("Coding failure in Argo:  StringWriter gave an IOException", iOException4);
		}

		return stringWriter2.toString();
	}

	public void format(J_JsonRootNode j_JsonRootNode1, Writer writer2) throws IOException {
		this.formatJsonNode(j_JsonRootNode1, writer2);
	}

	private void formatJsonNode(J_JsonNode j_JsonNode1, Writer writer2) throws IOException {
		boolean z3 = true;
		Iterator iterator4;
		switch(J_CompactJsonFormatter.SyntheticClass_1.$SwitchMap$net$minecraft$src$EnumJsonNodeType[j_JsonNode1.getType().ordinal()]) {
		case 1:
			writer2.append('[');
			iterator4 = j_JsonNode1.getElements().iterator();

			while(iterator4.hasNext()) {
				J_JsonNode j_JsonNode6 = (J_JsonNode)iterator4.next();
				if(!z3) {
					writer2.append(',');
				}

				z3 = false;
				this.formatJsonNode(j_JsonNode6, writer2);
			}

			writer2.append(']');
			break;
		case 2:
			writer2.append('{');
			iterator4 = (new TreeSet(j_JsonNode1.getFields().keySet())).iterator();

			while(iterator4.hasNext()) {
				J_JsonStringNode j_JsonStringNode5 = (J_JsonStringNode)iterator4.next();
				if(!z3) {
					writer2.append(',');
				}

				z3 = false;
				this.formatJsonNode(j_JsonStringNode5, writer2);
				writer2.append(':');
				this.formatJsonNode((J_JsonNode)j_JsonNode1.getFields().get(j_JsonStringNode5), writer2);
			}

			writer2.append('}');
			break;
		case 3:
			writer2.append('\"').append((new J_JsonEscapedString(j_JsonNode1.getText())).toString()).append('\"');
			break;
		case 4:
			writer2.append(j_JsonNode1.getText());
			break;
		case 5:
			writer2.append("false");
			break;
		case 6:
			writer2.append("true");
			break;
		case 7:
			writer2.append("null");
			break;
		default:
			throw new RuntimeException("Coding failure in Argo:  Attempt to format a JsonNode of unknown type [" + j_JsonNode1.getType() + "];");
		}

	}

	static final class SyntheticClass_1 {
		static final int[] $SwitchMap$net$minecraft$src$EnumJsonNodeType = new int[EnumJsonNodeType.values().length];

		static {
			try {
				$SwitchMap$net$minecraft$src$EnumJsonNodeType[EnumJsonNodeType.ARRAY.ordinal()] = 1;
			} catch (NoSuchFieldError noSuchFieldError7) {
			}

			try {
				$SwitchMap$net$minecraft$src$EnumJsonNodeType[EnumJsonNodeType.OBJECT.ordinal()] = 2;
			} catch (NoSuchFieldError noSuchFieldError6) {
			}

			try {
				$SwitchMap$net$minecraft$src$EnumJsonNodeType[EnumJsonNodeType.STRING.ordinal()] = 3;
			} catch (NoSuchFieldError noSuchFieldError5) {
			}

			try {
				$SwitchMap$net$minecraft$src$EnumJsonNodeType[EnumJsonNodeType.NUMBER.ordinal()] = 4;
			} catch (NoSuchFieldError noSuchFieldError4) {
			}

			try {
				$SwitchMap$net$minecraft$src$EnumJsonNodeType[EnumJsonNodeType.FALSE.ordinal()] = 5;
			} catch (NoSuchFieldError noSuchFieldError3) {
			}

			try {
				$SwitchMap$net$minecraft$src$EnumJsonNodeType[EnumJsonNodeType.TRUE.ordinal()] = 6;
			} catch (NoSuchFieldError noSuchFieldError2) {
			}

			try {
				$SwitchMap$net$minecraft$src$EnumJsonNodeType[EnumJsonNodeType.NULL.ordinal()] = 7;
			} catch (NoSuchFieldError noSuchFieldError1) {
			}

		}
	}
}