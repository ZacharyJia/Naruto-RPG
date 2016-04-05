package me.zacharyjia.naruto.game.Model;

import me.zacharyjia.naruto.core.component.Interface.SpriteFactory;
import me.zacharyjia.naruto.core.scene.NScene;
import me.zacharyjia.naruto.core.utils.CharacterImageLoader;
import me.zacharyjia.naruto.game.Model.Impl.NPC;

/**
 * Created by jia19 on 2016/3/28.
 */
public class NPCFactory implements SpriteFactory {

    @Override
    public NPC createSprite(NScene scene, String imgName) {
        NPC npc = new NPC(CharacterImageLoader.getImages(imgName));
        return npc;
    }
}
