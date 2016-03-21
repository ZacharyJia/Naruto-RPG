package me.zacharyjia.naruto.core.component.Interface;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import me.zacharyjia.naruto.Config;
import me.zacharyjia.naruto.core.component.Interface.IShowable;

/**
 * Created by jia19 on 2016/3/21.
 */
public abstract class AbstractSprite implements IShowable {

    protected int x, y;//当前位置
    protected Direction direction = Direction.UP; //当前方向

    protected Image images[][];

    protected ImageView imageView = new ImageView();

    protected boolean isMove = false;


    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public void setImage(Image[][] images) {
        this.images = images;
    }

    @Override
    public void show() {
        imageView.setVisible(true);
    }

    @Override
    public void disappear() {
        imageView.setVisible(false);
    }

    @Override
    public ImageView getImageView() {
        return this.imageView;
    }

    public abstract void move(int offsetX, int offsetY);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        int tileSize = Config.getInstance().getTileSize();

        this.x = x;
        this.y = y;

        imageView.setLayoutX(tileSize * x);
        imageView.setLayoutY(tileSize * y);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}
