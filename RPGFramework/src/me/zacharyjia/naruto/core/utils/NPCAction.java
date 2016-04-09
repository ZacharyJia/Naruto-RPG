package me.zacharyjia.naruto.core.utils;

import me.zacharyjia.naruto.core.component.Interface.Direction;

/**
 * NPC的动作标识
 *
 * Created by jia19 on 2016/3/30.
 */
public class NPCAction {

    private Direction action;
    private int value;

    public NPCAction(Direction action, int value) {
        this.action = action;
        this.value = value;
    }

    public Direction getAction() {
        return action;
    }

    public void setAction(Direction action) {
        this.action = action;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
