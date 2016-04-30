package me.zacharyjia.naruto.game.Model.Interface;

/**
 * Created by jia19 on 2016/4/19.
 */
public interface ISkillAggregate {

    ISkillIterator getIterator();

    int length();

    ISkill get(int index);

    void set(int index, ISkill skill);
}
