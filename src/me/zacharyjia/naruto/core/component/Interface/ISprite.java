package me.zacharyjia.naruto.core.component.Interface;

/**
 * Created by jia19 on 2016/3/11.
 */
public interface ISprite {

    void move(int offsetX, int offsetY);

    int getX();
    int getY();
    void setPosition(int x, int y);

    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}
