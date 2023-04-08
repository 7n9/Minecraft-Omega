package com.owen2k6.omega.event.impl.mouse;

import com.owen2k6.omega.event.Event;

public class EventScroll extends Event {
    int mouseButton;

    public EventScroll(int mouseButtonPressed) {
        this.mouseButton = mouseButtonPressed;
    }

    public int getMouseButton() {
        return this.mouseButton;
    }

    public void setMouseButton(int mouseButton) {
        this.mouseButton = mouseButton;
    }
}
