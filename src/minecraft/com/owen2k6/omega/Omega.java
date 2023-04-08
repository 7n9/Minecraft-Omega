package com.owen2k6.omega;

import com.owen2k6.omega.event.EventManager;
import com.owen2k6.omega.event.EventTarget;
import com.owen2k6.omega.event.impl.EventRender2D;
import net.minecraft.client.Minecraft;

public enum Omega {
    INSTANCE;

    public EventManager eventManager;
    public Minecraft mc;

    /**
     * Called Post-startup
     */
    public void onInit() {
        eventManager = new EventManager();
        mc = Minecraft.getMinecraft();

        eventManager.register(this);
    }

    @EventTarget
    public void onRender2D(EventRender2D eventRender2D) {
        //Example of an event.
    }
}
