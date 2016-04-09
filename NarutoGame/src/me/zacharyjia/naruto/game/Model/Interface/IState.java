package me.zacharyjia.naruto.game.Model.Interface;

import me.zacharyjia.naruto.core.event.Interface.OnSkillFinishListener;
import me.zacharyjia.naruto.game.Model.Impl.Monster;

/**
 * 主角当前的状态
 *
 * 运用状态模式，令主角在不同的状态下，施展出不同的攻击力
 * Created by jia19 on 2016/4/8.
 */
public interface IState {

    void attack(Monster monster, ISkill skill, OnSkillFinishListener listener);
}
