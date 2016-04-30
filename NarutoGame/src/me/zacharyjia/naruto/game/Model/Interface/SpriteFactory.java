package me.zacharyjia.naruto.game.Model.Interface;

import javafx.scene.image.Image;
import me.zacharyjia.naruto.core.component.Interface.AbstractSprite;
import me.zacharyjia.naruto.core.scene.NScene;

/**
 * Created by jia19 on 2016/3/28.
 */
public interface SpriteFactory {

    AbstractSprite createSprite(NScene scene, String imgName);
}
