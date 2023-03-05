package net.minecraft.src;

interface J_Functor {
	boolean matchsNode(Object object1);

	Object applyTo(Object object1);

	String shortForm();
}
