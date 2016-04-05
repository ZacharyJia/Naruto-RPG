package me.zacharyjia.naruto.game.Model.Impl;


import me.zacharyjia.naruto.game.Model.Interface.ISkill;

/**
 * Created by jia19 on 2016/3/31.
 */
public class AttackSkill extends ISkill {

    private int attackValue;
    private int cost;

    public void attack(NPC npc) {
        npc.attacked(attackValue);
    }
}
