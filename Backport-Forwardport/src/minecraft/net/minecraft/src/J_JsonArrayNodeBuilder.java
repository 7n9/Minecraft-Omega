package net.minecraft.src;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class J_JsonArrayNodeBuilder implements J_JsonNodeBuilder {
	private final List elementBuilders = new LinkedList();

	public J_JsonArrayNodeBuilder withElement(J_JsonNodeBuilder j_JsonNodeBuilder1) {
		this.elementBuilders.add(j_JsonNodeBuilder1);
		return this;
	}

	public J_JsonRootNode build() {
		LinkedList linkedList1 = new LinkedList();
		Iterator iterator2 = this.elementBuilders.iterator();

		while(iterator2.hasNext()) {
			J_JsonNodeBuilder j_JsonNodeBuilder3 = (J_JsonNodeBuilder)iterator2.next();
			linkedList1.add(j_JsonNodeBuilder3.buildNode());
		}

		return J_JsonNodeFactories.func_27309_a(linkedList1);
	}

	public J_JsonNode buildNode() {
		return this.build();
	}
}
