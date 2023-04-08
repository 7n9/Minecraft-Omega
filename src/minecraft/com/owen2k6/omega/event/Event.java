package com.owen2k6.omega.event;

import java.lang.reflect.InvocationTargetException;

import com.owen2k6.omega.Omega;

public abstract class Event {
    /**
     *
     * Main events you may need:
     * <p>
     * Minecraft:
     * - EventKeyboard
     * - EventMiddleClick
     * - EventTick
     * <p>
     * EntityPlayerSP:
     * - EventUpdate
     * - EventPreMotionUpdates
     * - EventPostMotionUpdates
     * <p>
     * GuiIngame:
     * - EventRender2D
     * <p>
     * EntityRenderer:
     * - EventRender3D
     *
     */

    private boolean cancelled;

    public Event call() {
        this.cancelled = false;
        call(this);
        return this;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * This function is final to make sure it isn't overriden.
     */
    private static void call(final Event event) {
        final ArrayHelper<Data> dataList = Omega.INSTANCE.eventManager.get(event.getClass());

        if (dataList != null) {
            for (final Data data : dataList) {
                try {
                    data.target.invoke(data.source, event);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
