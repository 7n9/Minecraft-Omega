package net.minecraft.src;

import java.util.LinkedList;
import java.util.List;

public final class J_JsonObjectNodeBuilder implements J_JsonNodeBuilder {
	private final List field_27238_a = new LinkedList();

	public J_JsonObjectNodeBuilder func_27237_a(J_JsonFieldBuilder j_JsonFieldBuilder1) {
		this.field_27238_a.add(j_JsonFieldBuilder1);
		return this;
	}

	public J_JsonRootNode func_27235_a() {
		return J_JsonNodeFactories.func_27312_a(new J_JsonObjectNodeList(this));
	}

	public J_JsonNode buildNode() {
		return this.func_27235_a();
	}

	static List func_27236_a(J_JsonObjectNodeBuilder j_JsonObjectNodeBuilder0) {
		return j_JsonObjectNodeBuilder0.field_27238_a;
	}
}
