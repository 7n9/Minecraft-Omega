package net.minecraft.src;

public final class J_JsonNodeSelector {
	final J_Functor valueGetter;

	J_JsonNodeSelector(J_Functor j_Functor1) {
		this.valueGetter = j_Functor1;
	}

	public boolean matchs(Object object1) {
		return this.valueGetter.matchsNode(object1);
	}

	public Object getValue(Object object1) {
		return this.valueGetter.applyTo(object1);
	}

	public J_JsonNodeSelector with(J_JsonNodeSelector j_JsonNodeSelector1) {
		return new J_JsonNodeSelector(new J_ChainedFunctor(this, j_JsonNodeSelector1));
	}

	String shortForm() {
		return this.valueGetter.shortForm();
	}

	public String toString() {
		return this.valueGetter.toString();
	}
}
