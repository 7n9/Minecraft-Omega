package net.minecraft.src;

class J_ObjectNodeContainer implements J_NodeContainer {
	final J_JsonObjectNodeBuilder nodeBuilder;
	final J_JsonListenerToJdomAdapter field_27295_b;

	J_ObjectNodeContainer(J_JsonListenerToJdomAdapter j_JsonListenerToJdomAdapter1, J_JsonObjectNodeBuilder j_JsonObjectNodeBuilder2) {
		this.field_27295_b = j_JsonListenerToJdomAdapter1;
		this.nodeBuilder = j_JsonObjectNodeBuilder2;
	}

	public void func_27290_a(J_JsonNodeBuilder j_JsonNodeBuilder1) {
		throw new RuntimeException("Coding failure in Argo:  Attempt to add a node to an object.");
	}

	public void func_27289_a(J_JsonFieldBuilder j_JsonFieldBuilder1) {
		this.nodeBuilder.func_27237_a(j_JsonFieldBuilder1);
	}
}
