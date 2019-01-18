package jsr305.nullcheck;

import javax.annotation.Nonnull;

public class Nonnullcheck {

	public static void main(String...string) {
		testNonnull(null);
	}
	
	public static String testNonnull(@Nonnull String str) {
		return str.length() > 2 ? "": "false";
	}
}
