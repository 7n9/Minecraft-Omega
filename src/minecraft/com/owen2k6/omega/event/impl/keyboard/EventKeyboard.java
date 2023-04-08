package com.owen2k6.omega.event.impl.keyboard;

import com.owen2k6.omega.event.Event;

public class EventKeyboard extends Event {
    int key;

    public EventKeyboard(int key){
        this.key = key;
    }

    public int getKey() { return key; }
}
