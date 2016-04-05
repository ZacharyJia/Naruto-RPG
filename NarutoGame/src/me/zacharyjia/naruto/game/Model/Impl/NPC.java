package me.zacharyjia.naruto.game.Model.Impl;

import javafx.scene.image.Image;

/**
 * Created by jia19 on 2016/3/31.
 */
public class NPC extends me.zacharyjia.naruto.core.component.Implement.NPC {

    private int life;

    public void attacked(int attackValue) {

    }


    public NPC(Image[][] images) {
        super(images);
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
