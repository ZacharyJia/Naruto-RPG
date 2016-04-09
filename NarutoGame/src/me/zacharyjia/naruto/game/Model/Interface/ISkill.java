package me.zacharyjia.naruto.game.Model.Interface;

/**
 * 技能接口
 * 运用策略模式，通过不同的技能，实现不同的攻击能力
 *
 * Created by jia19 on 2016/3/31.
 */
public abstract class ISkill {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
