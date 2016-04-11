package me.zacharyjia.naruto.core.component.Implement;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import me.zacharyjia.naruto.core.Exception.ResourcesNotFoundException;
import me.zacharyjia.naruto.core.component.Interface.AbstractSprite;
import me.zacharyjia.naruto.core.utils.ResourcesLoader;

/**
 * 怪物
 * 可战斗，不可控制
 * Created by jia19 on 2016/3/11.
 */
public abstract class Monster extends AbstractSprite {

    private Image image;

    public Monster(String name) {
        try {
            image = new Image(ResourcesLoader.getInputStream(name));
            setImageCenterX((int)image.getWidth() / 2);
            setImageCenterY((int)image.getHeight() / 2);
            super.imageView.setImage(image);
        } catch (ResourcesNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pauseAnimation() {

    }

    @Override
    public void resumeAnimation() {
    }

    @Override
    public void borderTest(int offsetX, int offsetY){}

    @Override
    public void entryTest(){}

    @Override
    public void maskTest(int originX, int originY){}



}
