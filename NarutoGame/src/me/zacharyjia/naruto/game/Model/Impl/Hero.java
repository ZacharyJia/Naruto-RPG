package me.zacharyjia.naruto.game.Model.Impl;

import javafx.scene.image.Image;
import me.zacharyjia.naruto.core.scene.NScene;
import me.zacharyjia.naruto.game.Model.Interface.ISkill;

/**
 * Created by jia19 on 2016/3/31.
 */
public class Hero extends me.zacharyjia.naruto.core.component.Implement.Hero {

    private int life;
    private int chakera;

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
}
