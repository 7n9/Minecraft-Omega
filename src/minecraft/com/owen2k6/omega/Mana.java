package com.owen2k6.omega;

import com.owen2k6.omega.event.EventTarget;
import com.owen2k6.omega.event.impl.EventRender2D;
import com.owen2k6.omega.event.impl.EventTick;
import com.owen2k6.omega.event.impl.movement.EventPlayerJump;

public class Mana {
    int maxMana = 20;
    int mana = 20;
    short counter = 0;

    @EventTarget
    public void onTick(EventTick event) {
        counter++;

        if(counter > 60)
            counter = 0;

        if (counter == 60 && mana < 20) {
            mana = mana + 5;
            counter = 0;
        }

        if (mana > maxMana)
            mana = maxMana;
    }

    @EventTarget
    public void onRender(EventRender2D event) {
    }

    @EventTarget
    public void onJump(EventPlayerJump event) {
        if(mana < 5) {
            event.setCancelled(true);
            Omega.INSTANCE.mc.thePlayer.addChatMessage("You're too weak to jump... Mana: " + mana);
        } else {
            Omega.INSTANCE.mc.thePlayer.addChatMessage("§cmana lost. Total: §e" + mana);
            mana -= 1;
        }
    }

    public int getMana() {
        return mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setMaxMana(int maxmana) {
        this.maxMana = maxmana;
    }

    public void addMana(int mana) {
        this.mana = this.mana + mana;
    }

    public void addMaxMana(int maxmana) {
        this.maxMana = this.maxMana + maxmana;
    }

    public void removeMana(int mana) {
        this.mana = this.mana - mana;
    }

    public void removeMaxMana(int maxmana) {
        this.maxMana = this.maxMana - maxmana;
    }

    public void setManaToMax() {
        this.mana = this.maxMana;
    }
}
