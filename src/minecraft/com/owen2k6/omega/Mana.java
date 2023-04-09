package com.owen2k6.omega;

import com.owen2k6.omega.event.EventTarget;
import com.owen2k6.omega.event.impl.EventRender2D;
import com.owen2k6.omega.event.impl.EventTick;
import com.owen2k6.omega.event.impl.movement.EventPlayerJump;

public class Mana {
    int maxMana = 20;
    int mana = 20;
    short counter = 0;

    short target = 60;

    @EventTarget
    public void onTick(EventTick event) {
        counter++;

        if (counter > target)
            counter = 0;

        if (counter == target && mana < maxMana) {
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
        //if (mana < 5) {
        //    event.setCancelled(true);
        //    Omega.INSTANCE.mc.thePlayer.addChatMessage("You're too weak to jump... Mana: " + mana);
        //} else {
        //    Omega.INSTANCE.mc.thePlayer.addChatMessage("§cmana lost. Total: §e" + mana);
        //    mana -= 1;
        //}
    }

    public int get() {
        return mana;
    }

    public int getMax() {
        return maxMana;
    }

    public void set(int mana) {
        this.mana = mana;
    }

    public void setMax(int maxmana) {
        this.maxMana = maxmana;
    }

    public void add(int mana) {
        this.mana = this.mana + mana;
    }

    public void addMax(int maxmana) {
        this.maxMana = this.maxMana + maxmana;
    }

    public void remove(int mana) {
        if (this.mana - mana < 0) {
            this.mana = 0;
            Omega.INSTANCE.mc.thePlayer.addChatMessage("§cinsufficient mana. Total: §e" + this.mana);
        }
        this.mana =this.mana -mana;
}

    public void removeMax(int maxmana) {
        this.maxMana = this.maxMana - maxmana;
    }

    public void setToMax() {
        this.mana = this.maxMana;
    }

    public void setTarget(short i) {
        this.target = i;
    }
}
