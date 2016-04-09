package me.zacharyjia.naruto.game.Model.Interface;

import me.zacharyjia.naruto.core.event.Interface.OnSkillFinishListener;
import me.zacharyjia.naruto.game.Model.Impl.Monster;

/**
 * Created by jia19 on 2016/4/8.
 */
public interface IState {

    void attack(Monster monster, ISkill skill, OnSkillFinishListener listener);
}
