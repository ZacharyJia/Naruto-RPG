package me.zacharyjia.naruto.game.Model.Impl;

import me.zacharyjia.naruto.game.Model.Interface.ISkill;
import me.zacharyjia.naruto.game.utils.SoundManager;

/**
 * 恢复性技能
 * Created by jia19 on 2016/3/31.
 */
public class RecoverSkill extends ISkill {

    private int lifeValue;
    private int chakraValue;

    private String soundFile = "/res/sound/recover.wav";

    public int getChakraValue() {
        return chakraValue;
    }

    public void setChakraValue(int chakraValue) {
        this.chakraValue = chakraValue;
    }

    public int getLifeValue() {
        return lifeValue;
    }

    public void setLifeValue(int lifeValue) {
        this.lifeValue = lifeValue;
    }

    @Override
    public void playSound() {
        SoundManager.play(soundFile);
    }
}
