package me.zacharyjia.naruto.game.Model;

import javafx.scene.image.Image;
import me.zacharyjia.naruto.core.component.Interface.AbstractSprite;
import me.zacharyjia.naruto.core.component.Interface.SpriteFactory;
import me.zacharyjia.naruto.core.scene.NScene;
import me.zacharyjia.naruto.core.utils.CharacterImageLoader;
import me.zacharyjia.naruto.game.Model.Impl.Hero;

/**
 * Created by jia19 on 2016/3/28.
 */
public class HeroFactory implements SpriteFactory {

    @Override
    public Hero createSprite(NScene scene, String imgName) {
        Hero hero = new Hero(scene, CharacterImageLoader.getImages(imgName));
        return hero;
    }
}
