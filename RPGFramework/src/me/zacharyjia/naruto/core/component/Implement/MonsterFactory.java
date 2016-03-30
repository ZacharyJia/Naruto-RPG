package me.zacharyjia.naruto.core.component.Implement;

import javafx.scene.image.Image;
import me.zacharyjia.naruto.core.component.Interface.SpriteFactory;
import me.zacharyjia.naruto.core.scene.NScene;

/**
 * Created by jia19 on 2016/3/28.
 */
public class MonsterFactory implements SpriteFactory {
    @Override
    public Monster createSprite(NScene scene, String imgName) {
        return new Monster(imgName);
    }
}
