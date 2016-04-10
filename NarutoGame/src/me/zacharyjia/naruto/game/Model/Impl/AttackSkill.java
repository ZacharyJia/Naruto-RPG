package me.zacharyjia.naruto.game.Model.Impl;


import me.zacharyjia.naruto.game.Model.Interface.ISkill;
import me.zacharyjia.naruto.game.utils.SoundManager;

/**
 * 攻击型技能
 * Created by jia19 on 2016/3/31.
 */
public class AttackSkill extends ISkill {

    private int attackValue;
    private int cost;

    private String soundFile = "/res/sound/attack.wav";

    public int getAttackValue() {
        return attackValue;
    }

    public void setAttackValue(int attackValue) {
        this.attackValue = attackValue;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public void playSound() {
        SoundManager.play(soundFile);
    }

    public void setSoundFile(String soundFile) {
        this.soundFile = soundFile;
    }
}
