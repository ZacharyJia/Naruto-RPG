package me.zacharyjia.naruto.game.Model.Impl;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import me.zacharyjia.naruto.core.event.Interface.OnSkillFinishListener;
import me.zacharyjia.naruto.game.Model.Interface.InfoShowable;
import me.zacharyjia.naruto.game.components.InfoHub;

/**
 * Created by jia19 on 2016/4/5.
 */
public class Monster extends me.zacharyjia.naruto.core.component.Implement.Monster implements InfoShowable {

    private int life;
    private int fullLife;
    private String name;

    private int attackValue;

    private String soundFile;

    private InfoHub infoHub = null;

    private boolean left = true;
    private Timeline attackAnimation = new Timeline(new KeyFrame(Duration.millis(100), event -> {
        if (left) {
            super.imageView.setLayoutX(super.imageView.getLayoutX() + 10);
            left = false;
        } else {
            super.imageView.setLayoutX(super.imageView.getLayoutX() - 10);
            left = true;
        }
    }));

    public Monster(String imgName) {
        super(imgName);
    }

    public void attack(Hero target, int value, OnSkillFinishListener listener) {
        target.attacked(value, listener);
    }

    public void attacked(int value, OnSkillFinishListener listener) {
        left = true;
        attackAnimation.setCycleCount(10);
        attackAnimation.play();

        int life = this.life - value;
        if (life <= 0) {
            this.alive = false;
            life = 0;
        }
        setLife(life);

        attackAnimation.setOnFinished(event-> {
            if (listener != null) {
                listener.onSkillFinish();
            }
        });
    }

    public int getFullLife() {
        return fullLife;
    }

    @Override
    public int getFullChakra() {
        return 0;
    }

    @Override
    public boolean hasChakra() {
        return false;
    }

    public void setFullLife(int fullLife) {
        this.fullLife = fullLife;
        if (infoHub != null) {
            infoHub.notifyChanged(this);
        }
    }

    @Override
    public void bindInfoHub(InfoHub infoHub) {
        this.infoHub = infoHub;
        infoHub.notifyChanged(this);
    }

    public int getLife() {
        return life;
    }

    @Override
    public int getChakra() {
        return 0;
    }

    public void setLife(int life) {
        this.life = life;
        if (infoHub != null) {
            infoHub.notifyChanged(this);
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        if (infoHub != null) {
            infoHub.notifyChanged(this);
        }
    }

    public int getAttackValue() {
        return attackValue;
    }

    public void setAttackValue(int attackValue) {
        this.attackValue = attackValue;
    }

    public String getSoundFile() {
        return soundFile;
    }

    public void setSoundFile(String soundFile) {
        this.soundFile = soundFile;
    }
}
