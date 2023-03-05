package net.minecraft.src;

final class J_ChainedFunctor implements J_Functor {
	private final J_JsonNodeSelector parentJsonNodeSelector;
	private final J_JsonNodeSelector childJsonNodeSelector;

	J_ChainedFunctor(J_JsonNodeSelector j_JsonNodeSelector1, J_JsonNodeSelector j_JsonNodeSelector2) {
		this.parentJsonNodeSelector = j_JsonNodeSelector1;
		this.childJsonNodeSelector = j_JsonNodeSelector2;
	}

	public boolean matchsNode(Object object1) {
		return this.parentJsonNodeSelector.matchs(object1) && this.childJsonNodeSelector.matchs(this.parentJsonNodeSelector.getValue(object1));
	}

	public Object applyTo(Object object1) {
		Object object2;
		try {
			object2 = this.parentJsonNodeSelector.getValue(object1);
		} catch (J_JsonNodeDoesNotMatchChainedJsonNodeSelectorException j_JsonNodeDoesNotMatchChainedJsonNodeSelectorException6) {
			throw J_JsonNodeDoesNotMatchChainedJsonNodeSelectorException.func_27321_b(j_JsonNodeDoesNotMatchChainedJsonNodeSelectorException6, this.parentJsonNodeSelector);
		}

		try {
			Object object3 = this.childJsonNodeSelector.getValue(object2);
			return object3;
		} catch (J_JsonNodeDoesNotMatchChainedJsonNodeSelectorException j_JsonNodeDoesNotMatchChainedJsonNodeSelectorException5) {
			throw J_JsonNodeDoesNotMatchChainedJsonNodeSelectorException.func_27323_a(j_JsonNodeDoesNotMatchChainedJsonNodeSelectorException5, this.parentJsonNodeSelector);
		}
	}

	public String shortForm() {
		return this.childJsonNodeSelector.shortForm();
	}

	public String toString() {
		return this.parentJsonNodeSelector.toString() + ", with " + this.childJsonNodeSelector.toString();
	}
}
