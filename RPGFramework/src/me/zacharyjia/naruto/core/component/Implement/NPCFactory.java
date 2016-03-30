package me.zacharyjia.naruto.core.component.Implement;

import me.zacharyjia.naruto.core.component.Interface.AbstractSprite;
import me.zacharyjia.naruto.core.component.Interface.SpriteFactory;
import me.zacharyjia.naruto.core.utils.CharacterImageLoader;

/**
 * Created by jia19 on 2016/3/28.
 */
public class NPCFactory implements SpriteFactory {

    @Override
    public NPC createSprite(String imgName) {
        NPC npc = new NPC(CharacterImageLoader.getImages(imgName));
        return npc;
    }
}
