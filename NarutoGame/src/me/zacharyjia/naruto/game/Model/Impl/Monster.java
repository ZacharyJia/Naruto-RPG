package me.zacharyjia.naruto.game.Model.Impl;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * Created by jia19 on 2016/4/5.
 */
public class Monster extends me.zacharyjia.naruto.core.component.Implement.Monster {

    private int life;
    private int fullLife;
    private String name;

    private boolean left = true;
    private Timeline attackAnimation = new Timeline(new KeyFrame(Duration.millis(100), event -> {
        if (left) {
            super.imageView.setX(super.imageView.getX() + 10);
            left = false;
        } else {
            super.imageView.setX(super.imageView.getX() - 10);
            left = true;
        }
    }));

    public Monster(String imgName) {
        super(imgName);
    }

    // TODO: Need to be finished
    public void attacked() {
        left = true;
        attackAnimation.setCycleCount(6);
        attackAnimation.play();
    }

    public int getFullLife() {
        return fullLife;
    }

    public void setFullLife(int fullLife) {
        this.fullLife = fullLife;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
