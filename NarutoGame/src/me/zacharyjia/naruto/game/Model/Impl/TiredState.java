package me.zacharyjia.naruto.game.Model.Impl;

import me.zacharyjia.naruto.core.event.Interface.OnSkillFinishListener;
import me.zacharyjia.naruto.game.Model.Interface.ISkill;
import me.zacharyjia.naruto.game.Model.Interface.IState;

/**
 * Created by jia19 on 2016/4/8.
 */
public class TiredState implements IState {

    private float factor = 0.8f;

    @Override
    public void attack(Monster monster, ISkill skill, OnSkillFinishListener listener) {
        if (skill instanceof AttackSkill) {
            float value = ((AttackSkill) skill).getAttackValue() * factor;
            monster.attacked((int)value, listener);
        }
    }

}
