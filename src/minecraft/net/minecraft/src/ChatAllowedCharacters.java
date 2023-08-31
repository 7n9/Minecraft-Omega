package net.minecraft.src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ChatAllowedCharacters {
	public static final String allowedCharacters = getAllowedCharacters();
	public static final char[] allowedCharactersArray = new char[]{'/', '\n', '\r', '\t', '\u0000', '\f', '`', '?', '*', '\\', '<', '>', '|', '\"', ':'};

	private static String getAllowedCharacters() {
		StringBuilder string0 = new StringBuilder();

		try {
			BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(Objects.requireNonNull(ChatAllowedCharacters.class.getResourceAsStream("/font.txt")), StandardCharsets.UTF_8));
			String string2 = "";

			while((string2 = bufferedReader1.readLine()) != null) {
				if(!string2.startsWith("#")) {
					string0.append(string2);
				}
			}

			bufferedReader1.close();
		} catch (Exception ignored) {
		}

		return string0.toString();
	}
}
