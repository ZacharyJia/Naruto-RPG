package me.zacharyjia.naruto.Interface;

/**
 * Created by jia19 on 2016/3/11.
 */
public interface ISprite {

    void move(int offsetX, int offsetY);
    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}