package me.zacharyjia.naruto.game.Model.Impl;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;
import me.zacharyjia.naruto.core.event.Interface.OnSkillFinishListener;
import me.zacharyjia.naruto.core.scene.NScene;
import me.zacharyjia.naruto.game.Model.Interface.*;
import me.zacharyjia.naruto.game.components.InfoHub;

/**
 * Created by jia19 on 2016/3/31.
 */
public class Hero extends me.zacharyjia.naruto.core.component.Implement.Hero implements InfoShowable, ISkillAggregate {

    private InfoHub infoHub= null;

    private String name; //主角名称
    //生命和查克拉
    private int life = 100;
    private int fullLife = 100;
    private int chakra = 100;
    private int fullChakra = 100;

    boolean left = true;
    boolean up = true;

    //遭到攻击时的动画
    private Timeline attackAnimation = new Timeline(new KeyFrame(Duration.millis(100), event -> {
        if (left) {
            super.imageView.setLayoutX(super.imageView.getLayoutX() + 10);
            left = false;
        }
        else {
            super.imageView.setLayoutX(super.imageView.getLayoutX() - 10);
            left = true;
        }
    }));


    //使用恢复技能时的动画
    private Timeline recoverAnimation = new Timeline(new KeyFrame(Duration.millis(100), event -> {
        if (up) {
            super.imageView.setLayoutY(super.imageView.getLayoutY() + 10);
            up = false;
        }
        else {
            super.imageView.setLayoutY(super.imageView.getLayoutY() - 10);
            up = true;
        }
    }));

    //技能列表
    private ISkill[] skills = new ISkill[3];
    //用户的当前状态
    private IState state = new NormalState();

    //获取状态列表
    public ISkill[] getSkills() {
        return skills;
    }

    public void set(int index, ISkill skill) {
        if (index >= 0 && index <= 2)
        {
            skills[index] = skill;
        }
    }

    public Hero(NScene scene, Image[][] images) {
        super(scene, images);
    }

    //使用技能
    public void useSkill(Monster target, ISkill skill, OnSkillFinishListener listener) {
        skill.playSound();
        if (skill instanceof AttackSkill) {

            AttackSkill aSkill = (AttackSkill) skill;
            if (aSkill.getCost() <= this.chakra) {
                state.attack(target, skill, listener);
                setChakra(this.chakra - aSkill.getCost());
            }
        }
        else if (skill instanceof RecoverSkill){
            int life = this.life + skill.getValue();
            int chakra = this.chakra + skill.getValue();
            recover(life, chakra, listener);
        }
    }

    //遭受攻击
    public void attacked(int value, OnSkillFinishListener listener) {
        left = true;
        attackAnimation.setCycleCount(10);
        attackAnimation.play();

        int life = this.life - value;
        if (life <= 0) {
            life = 0;
            this.alive = false;
        }
        setLife(life);

        if (this.life < this.fullLife * 0.3) {
            this.state = new TiredState();
        }

        attackAnimation.setOnFinished(event -> {
            if (listener != null) {
                listener.onSkillFinish();
            }
        });
    }


    //恢复生命和查克拉
    public void recover(int life, int chakra, OnSkillFinishListener listener) {
        up = true;
        recoverAnimation.setCycleCount(6);
        recoverAnimation.play();

        if (life > this.fullLife) {
            life = this.fullLife;
        }
        if (chakra > this.fullChakra) {
            chakra = this.fullChakra;
        }
        setLife(life);
        setChakra(chakra);

        if (life > this.fullLife * 0.3) {
            this.state = new NormalState();
        }

        recoverAnimation.setOnFinished(event -> {
            if (listener != null) {
                listener.onSkillFinish();
            }
        });
    }

    public int getFullChakra() {
        return fullChakra;
    }

    @Override
    public boolean hasChakra() {
        return true;
    }

    public void setFullChakra(int fullChakra) {
        this.fullChakra = fullChakra;
        if (infoHub != null) {
            infoHub.notifyChanged(this);
        }
    }

    public int getFullLife() {
        return fullLife;
    }

    public void setFullLife(int fullLife) {
        this.fullLife = fullLife;
        if (infoHub != null) {
            infoHub.notifyChanged(this);
        }
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
        if (infoHub != null) {
            infoHub.notifyChanged(this);
        }
    }

    public int getChakra() {
        return chakra;
    }

    public void setChakra(int chakra) {
        this.chakra = chakra;
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

    //绑定显示框
    @Override
    public void bindInfoHub(InfoHub infoHub) {
        this.infoHub = infoHub;
        infoHub.notifyChanged(this);
    }

    @Override
    public ISkillIterator getIterator() {
        return new SkillIterator(this);
    }

    @Override
    public int length() {
        return 3;
    }

    @Override
    public ISkill get(int index) {
        if (index < 0 || index > 2) {
            return null;
        }
        return skills[index];
    }
}
