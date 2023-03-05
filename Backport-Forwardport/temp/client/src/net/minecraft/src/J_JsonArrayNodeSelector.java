package net.minecraft.src;

import java.util.List;

final class J_JsonArrayNodeSelector extends J_LeafFunctor {
	public boolean matchsNode_(J_JsonNode j_JsonNode1) {
		return EnumJsonNodeType.ARRAY == j_JsonNode1.getType();
	}

	public String shortForm() {
		return "A short form array";
	}

	public List typeSafeApplyTo(J_JsonNode j_JsonNode1) {
		return j_JsonNode1.getElements();
	}

	public String toString() {
		return "an array";
	}

	public Object typeSafeApplyTo(Object object1) {
		return this.typeSafeApplyTo((J_JsonNode)object1);
	}

	public boolean matchsNode(Object object1) {
		return this.matchsNode_((J_JsonNode)object1);
	}
}
