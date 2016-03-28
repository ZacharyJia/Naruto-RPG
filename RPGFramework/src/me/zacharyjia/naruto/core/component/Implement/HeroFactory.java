package me.zacharyjia.naruto.core.component.Implement;

import javafx.scene.image.Image;
import me.zacharyjia.naruto.core.component.Interface.AbstractSprite;
import me.zacharyjia.naruto.core.component.Interface.SpriteFactory;
import me.zacharyjia.naruto.core.utils.CharacterImageLoader;

/**
 * Created by jia19 on 2016/3/28.
 */
public class HeroFactory implements SpriteFactory {

    @Override
    public Hero createSprite(String imgName) {

        Hero hero = new Hero(CharacterImageLoader.getImages(imgName));
        return hero;

    }
}
