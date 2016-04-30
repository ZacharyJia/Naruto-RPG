package me.zacharyjia.naruto.game.Model.Impl;

import me.zacharyjia.naruto.game.Model.Interface.ISkill;
import me.zacharyjia.naruto.game.utils.SoundManager;

/**
 * 恢复性技能
 * Created by jia19 on 2016/3/31.
 */
public class RecoverSkill extends ISkill {

    private int value;

    private String soundFile = "/res/sound/recover.wav";

    @Override
    public int getValue() {
        return value;
    }

    public void setValue(int chakraValue) {
        this.value = chakraValue;
    }


    @Override
    public void playSound() {
        SoundManager.play(soundFile);
    }
}
