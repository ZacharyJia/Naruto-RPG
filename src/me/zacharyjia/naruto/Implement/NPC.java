package me.zacharyjia.naruto.Implement;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import me.zacharyjia.naruto.Interface.IShowable;
import me.zacharyjia.naruto.Interface.ISprite;

/**
 * Created by jia19 on 2016/3/11.
 */
public class NPC implements ISprite, IShowable {

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
}
