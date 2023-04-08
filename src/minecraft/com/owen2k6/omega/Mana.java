package com.owen2k6.omega;

import com.owen2k6.omega.event.EventTarget;
import com.owen2k6.omega.event.impl.EventRender2D;
import com.owen2k6.omega.event.impl.EventTick;

public class Mana {

    int maxmana = 20;
    int mana = 20;
    int counter = 0;
    @EventTarget
    public void onTick(EventTick event) {
        counter++;
        if (counter == 20 && mana < 20) {
            mana = mana + 5;
            counter = 0;
        }
        if (mana > maxmana)
            mana = maxmana;

    }

    @EventTarget
    public void onRender(EventRender2D event) {

    }

    public int getMana() {
        return mana;
    }

    public int getMaxMana() {
        return maxmana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setMaxMana(int maxmana) {
        this.maxmana = maxmana;
    }

    public void addMana(int mana) {
        this.mana = this.mana + mana;
    }

    public void addMaxMana(int maxmana) {
        this.maxmana = this.maxmana + maxmana;
    }

    public void removeMana(int mana) {
        this.mana = this.mana - mana;
    }

    public void removeMaxMana(int maxmana) {
        this.maxmana = this.maxmana - maxmana;
    }

    public void setManaToMax() {
        this.mana = this.maxmana;
    }
}
