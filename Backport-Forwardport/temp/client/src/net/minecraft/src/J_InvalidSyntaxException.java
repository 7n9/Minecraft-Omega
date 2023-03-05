package net.minecraft.src;

public final class J_InvalidSyntaxException extends Exception {
	private final int column;
	private final int row;

	J_InvalidSyntaxException(String string1, J_ThingWithPosition j_ThingWithPosition2) {
		super("At line " + j_ThingWithPosition2.getRow() + ", column " + j_ThingWithPosition2.getColumn() + ":  " + string1);
		this.column = j_ThingWithPosition2.getColumn();
		this.row = j_ThingWithPosition2.getRow();
	}

	J_InvalidSyntaxException(String string1, Throwable throwable2, J_ThingWithPosition j_ThingWithPosition3) {
		super("At line " + j_ThingWithPosition3.getRow() + ", column " + j_ThingWithPosition3.getColumn() + ":  " + string1, throwable2);
		this.column = j_ThingWithPosition3.getColumn();
		this.row = j_ThingWithPosition3.getRow();
	}
}
