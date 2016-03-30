package me.zacharyjia.naruto.core.component.Interface;

import javafx.scene.image.Image;
import me.zacharyjia.naruto.core.scene.NScene;

/**
 * Created by jia19 on 2016/3/28.
 */
public interface SpriteFactory {

    public AbstractSprite createSprite(NScene scene, String imgName);
}
