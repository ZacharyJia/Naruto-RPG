package me.zacharyjia.naruto.game;

import javafx.scene.input.KeyCode;
import me.zacharyjia.naruto.core.component.Implement.HeroFactory;
import me.zacharyjia.naruto.core.component.Implement.NPC;
import me.zacharyjia.naruto.core.component.Implement.TiledMapAdapter;
import me.zacharyjia.naruto.core.component.Implement.TalkSequence;
import me.zacharyjia.naruto.core.component.Interface.AbstractSprite;
import me.zacharyjia.naruto.core.component.Interface.Direction;
import me.zacharyjia.naruto.core.component.Interface.IMap;
import me.zacharyjia.naruto.core.component.Interface.SpriteFactory;
import me.zacharyjia.naruto.core.scene.NScene;
import me.zacharyjia.naruto.core.utils.MapReader;
import me.zacharyjia.naruto.core.utils.NPCLoader;
import tiled.core.ObjectGroup;

import java.util.ArrayList;

/**
 * Created by jia19 on 2016/3/21.
 */
public class VilliageScene extends NScene {

    @Override
    public void init() {

        IMap map = MapReader.readMap("/res/map/village.tmx");
        setMap(map);

        SpriteFactory factory = new HeroFactory();
        AbstractSprite hero = factory.createSprite("/res/characters/naruto.png");
        ObjectGroup objects = (ObjectGroup) map.getUserLayer("npc");
        NPC npc = NPCLoader.loadFromObject(objects.iterator().next());
        npc.startMove();
        addShowable(npc);
        hero.show();
        addShowable(hero);
        setOnKeyDownListener(keyEvent -> {
            KeyCode keyCode = keyEvent.getCode();
            if (keyCode.equals(KeyCode.UP)) {
                hero.setDirection(Direction.UP);
                hero.move(0, -1);
            }
            else if (keyCode.equals(KeyCode.DOWN)) {
                hero.setDirection(Direction.DOWN);
                hero.move(0, 1);
            }
            else if (keyCode.equals(KeyCode.LEFT)) {
                hero.setDirection(Direction.LEFT);
                hero.move(-1, 0);
            }
            else if (keyCode.equals(KeyCode.RIGHT)) {
                hero.setDirection(Direction.RIGHT);
                hero.move(1, 0);
            }
        });

        this.setOnMouseClickListener(event -> {
            System.out.println("clicked!");
            this.finish();
        });


    }

    @Override
    public void showFinish() {
        super.showFinish();

        ArrayList<String> list = new ArrayList<>();

        list.add("欢迎来到火影的世界……");
        list.add("这是一片充满了危险的土地");
        list.add("希望你能够活下去……");

        TalkSequence.getInstance().setTalkList(list);
        TalkSequence.getInstance().start(this);

    }
}
