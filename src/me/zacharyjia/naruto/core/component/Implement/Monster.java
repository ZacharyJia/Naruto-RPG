package me.zacharyjia.naruto.core.component.Implement;

import javafx.scene.image.ImageView;
import me.zacharyjia.naruto.core.component.Interface.IShowable;
import me.zacharyjia.naruto.core.component.Interface.ISprite;

/**
 * Created by jia19 on 2016/3/11.
 */
public class Monster implements ISprite, IShowable{
    @Override
    public void show() {

    }

    @Override
    public void disappear() {

    }

    @Override
    public ImageView getImageView() {
        return null;
    }

    @Override
    public void move(int offsetX, int offsetY) {

    }
}