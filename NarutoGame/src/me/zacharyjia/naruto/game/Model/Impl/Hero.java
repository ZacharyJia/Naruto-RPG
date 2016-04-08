package me.zacharyjia.naruto.game.Model.Impl;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;
import me.zacharyjia.naruto.core.scene.NScene;
import me.zacharyjia.naruto.game.Model.Interface.ISkill;

/**
 * Created by jia19 on 2016/3/31.
 */
public class Hero extends me.zacharyjia.naruto.core.component.Implement.Hero {

    private String name;
    private int life = 100;
    private int fullLife = 100;
    private int chakra = 100;
    private int fullChakra = 100;

    boolean left = true;

    private Timeline attackAnimation = new Timeline(new KeyFrame(Duration.millis(100), event -> {
        if (left) {
            super.imageView.setX(super.imageView.getX() + 10);
            left = false;
        }
        else {
            super.imageView.setX(super.imageView.getX() - 10);
            left = true;
        }
    }));

    private ISkill[] skills = new ISkill[3];

    public ISkill[] getSkills() {
        return skills;
    }

    public void setSkill(int index, ISkill skill) {
        if (index >= 0 && index <= 2)
        {
            skills[index] = skill;
        }
    }

    public Hero(NScene scene, Image[][] images) {
        super(scene, images);
    }

    public void attacked() {
        left = true;
        attackAnimation.setCycleCount(6);
        attackAnimation.play();
    }

    public int getFullChakra() {
        return fullChakra;
    }

    public void setFullChakra(int fullChakra) {
        this.fullChakra = fullChakra;
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

    public int getChakra() {
        return chakra;
    }

    public void setChakra(int chakra) {
        this.chakra = chakra;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
