package com.owen2k6.omega.event;

public enum EventState {
    /**
     * Before the event starts working.
     * Cancelling results in the event being stopped from working.
     */
    PRE,
    /**
     * While the event is working.
     * Cancelling isn't effective.
     */
    WHILE,
    /**
     * After the event has finished.
     * Cancelling isn't effective.
     */
    POST
}
