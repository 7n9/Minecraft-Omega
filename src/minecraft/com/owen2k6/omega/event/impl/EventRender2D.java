package com.owen2k6.omega.event.impl;

import com.owen2k6.omega.event.Event;
import net.minecraft.src.ScaledResolution;

public class EventRender2D extends Event {
	private ScaledResolution scaledResolution;
	
	public EventRender2D(ScaledResolution res) {
		this.scaledResolution = res;
	}
	
	public ScaledResolution getScaledResolution() {
		return this.scaledResolution;
	}
}