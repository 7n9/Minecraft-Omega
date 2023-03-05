package net.minecraft.src;

import java.util.List;

final class J_JsonElementNodeSelector extends J_LeafFunctor {
	final int index;

	J_JsonElementNodeSelector(int i1) {
		this.index = i1;
	}

	public boolean matchsNode_(List list1) {
		return list1.size() > this.index;
	}

	public String shortForm() {
		return Integer.toString(this.index);
	}

	public J_JsonNode typeSafeApplyTo_(List list1) {
		return (J_JsonNode)list1.get(this.index);
	}

	public String toString() {
		return "an element at index [" + this.index + "]";
	}

	public Object typeSafeApplyTo(Object object1) {
		return this.typeSafeApplyTo_((List)object1);
	}

	public boolean matchsNode(Object object1) {
		return this.matchsNode_((List)object1);
	}
}
