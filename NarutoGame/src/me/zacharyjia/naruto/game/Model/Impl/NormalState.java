package me.zacharyjia.naruto.game.Model.Impl;

import me.zacharyjia.naruto.core.event.Interface.OnSkillFinishListener;
import me.zacharyjia.naruto.game.Model.Interface.ISkill;
import me.zacharyjia.naruto.game.Model.Interface.IState;

/**
 * 正常状态
 * Created by jia19 on 2016/4/8.
 */
public class NormalState implements IState {

    private float factor = 1.0f;

    @Override
    public void attack(Monster monster, ISkill skill, OnSkillFinishListener listener) {
        if (skill instanceof AttackSkill) {
            float value = skill.getValue() * factor;
            monster.attacked((int)value, listener);
        }
    }
}
