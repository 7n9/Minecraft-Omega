package net.minecraft.src;

final class J_JsonStringNodeSelector extends J_LeafFunctor {
	public boolean func_27072_a(J_JsonNode j_JsonNode1) {
		return EnumJsonNodeType.STRING == j_JsonNode1.getType();
	}

	public String shortForm() {
		return "A short form string";
	}

	public String func_27073_b(J_JsonNode j_JsonNode1) {
		return j_JsonNode1.getText();
	}

	public String toString() {
		return "a value that is a string";
	}

	public Object typeSafeApplyTo(Object object1) {
		return this.func_27073_b((J_JsonNode)object1);
	}

	public boolean matchsNode(Object object1) {
		return this.func_27072_a((J_JsonNode)object1);
	}
}