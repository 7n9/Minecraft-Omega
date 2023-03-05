package net.minecraft.src;

abstract class J_LeafFunctor implements J_Functor {
	public final Object applyTo(Object object1) {
		if(!this.matchsNode(object1)) {
			throw J_JsonNodeDoesNotMatchChainedJsonNodeSelectorException.func_27322_a(this);
		} else {
			return this.typeSafeApplyTo(object1);
		}
	}

	protected abstract Object typeSafeApplyTo(Object object1);
}
