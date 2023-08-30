package com.owen2k6.omega;

import com.owen2k6.omega.event.EventTarget;
import com.owen2k6.omega.event.impl.EventRender2D;
import com.owen2k6.omega.event.impl.EventTick;
import com.owen2k6.omega.event.impl.movement.EventPlayerJump;

public class Stamina {
    int maxStamina = 100;
    int stamina = 100;
    short counter = 0;

    short target = 60;

    boolean holdatzero = false;
    int until = 0;
    int newcounter = 0;

    @EventTarget
    public void onTick(EventTick event) {
        counter++;

        if (holdatzero){
            counter = 0;
            if (newcounter == until){
                holdatzero = false;
                newcounter = 0;
            }
            newcounter++;
        }

        if (counter > target)
            counter = 0;

        if (counter == target && stamina < maxStamina) {
            stamina = stamina + 35;
            counter = 0;
        }

        if (stamina > maxStamina)
            stamina = maxStamina;
    }

    @EventTarget
    public void onRender(EventRender2D event) {
    }

    @EventTarget
    public void onJump(EventPlayerJump event) {
        if (stamina <= 5) {
            event.setCancelled(true);
            Omega.INSTANCE.mc.thePlayer.addChatMessage("§cYou're too tired to jump...");
        } else {
            //Omega.INSTANCE.mc.thePlayer.addChatMessage("§cStamina lost. Total: §e" + stamina);
            stamina -= 5;
        }
    }

    public int get() {
        return stamina;
    }

    public int getMax() {
        return maxStamina;
    }

    public void set(int mana) {
        this.stamina = mana;
    }

    public void setMax(int maxmana) {
        this.maxStamina = maxmana;
    }

    public void add(int mana) {
        this.stamina = this.stamina + mana;
    }

    public void addMax(int maxmana) {
        this.maxStamina = this.maxStamina + maxmana;
    }

    public void remove(int mana) {
        if (this.stamina - mana < 0) {
            this.stamina = 0;
            Omega.INSTANCE.mc.thePlayer.addChatMessage("§cinsufficient stamina. Total: §e" + stamina);
        }
        this.stamina =this.stamina -mana;
}

    public void removeMax(int maxmana) {
        this.maxStamina = this.maxStamina - maxmana;
    }

    public void setToMax() {
        this.stamina = this.maxStamina;
    }

    public void setTarget(short i) {
        this.target = i;
    }

    public void hold(int howLong){
        holdatzero = true;
        until = howLong;
    }
}
