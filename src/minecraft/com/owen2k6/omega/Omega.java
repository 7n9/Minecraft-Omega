package com.owen2k6.omega;

import com.owen2k6.omega.event.EventManager;
import com.owen2k6.omega.event.EventTarget;
import com.owen2k6.omega.event.impl.EventRender2D;
import net.minecraft.client.Minecraft;

public enum Omega {
    INSTANCE;

    public EventManager eventManager;
    public Mana manaSystem;
    public Stamina staminaSystem;
    public Minecraft mc;

    /**
     * Called Post-startup
     */
    public void onInit() {
        eventManager = new EventManager();
        mc = Minecraft.getMinecraft();
        manaSystem = new Mana();
        staminaSystem = new Stamina();


        eventManager.register(this);
        eventManager.register(manaSystem);
        eventManager.register(staminaSystem);
    }

    @EventTarget
    public void onRender(EventRender2D event) {
        //Example of an event.
    }
}
