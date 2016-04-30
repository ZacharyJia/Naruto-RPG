package me.zacharyjia.naruto.game.Model.Impl;

import me.zacharyjia.naruto.game.Model.Interface.ISkill;
import me.zacharyjia.naruto.game.Model.Interface.ISkillAggregate;
import me.zacharyjia.naruto.game.Model.Interface.ISkillIterator;

/**
 * Created by jia19 on 2016/4/19.
 */
public class SkillIterator implements ISkillIterator {

    private ISkillAggregate aggregate;

    private int currentIndex = 0;

    public SkillIterator(ISkillAggregate aggregate)
    {
        if (aggregate == null) {
            throw new NullPointerException();
        }
        this.aggregate = aggregate;
    }

    @Override
    public ISkill next() {
        currentIndex++;
        if (currentIndex < aggregate.length()) {
            return aggregate.get(currentIndex);
        }
        else {
            return null;
        }
    }

    @Override
    public ISkill first() {
        currentIndex = 0;
        if (currentIndex < aggregate.length()) {
            return aggregate.get(currentIndex);
        }
        else {
            return null;
        }
    }

    @Override
    public boolean isOver() {
        return currentIndex >= aggregate.length();
    }
}
