package me.zacharyjia.naruto.core.component.Implement;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import me.zacharyjia.naruto.core.component.Interface.AbstractSprite;

/**
 * Created by jia19 on 2016/3/11.
 */
public class NPC extends AbstractSprite {

    Image image[];
    ImageView imageView = new ImageView();
    Direction direction = Direction.UP;

    @Override
    public void show() {

    }

    @Override
    public void disappear() {

    }

    @Override
    public ImageView getImageView() {
        return imageView;
    }

    @Override
    public void move(int offsetX, int offsetY) {

    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public void setPosition(int x, int y) {

    }
}
