package me.zacharyjia.naruto.game.Scene;

import javafx.scene.input.KeyCode;
import me.zacharyjia.naruto.core.Intent;
import me.zacharyjia.naruto.core.component.Implement.Hero;
import me.zacharyjia.naruto.game.Model.HeroFactory;
import me.zacharyjia.naruto.core.component.Implement.NPC;
import me.zacharyjia.naruto.core.component.Implement.TalkSequence;
import me.zacharyjia.naruto.core.component.Interface.Direction;
import me.zacharyjia.naruto.core.component.Interface.IMap;
import me.zacharyjia.naruto.core.component.Interface.SpriteFactory;
import me.zacharyjia.naruto.core.scene.NScene;
import me.zacharyjia.naruto.core.utils.MapReader;
import me.zacharyjia.naruto.game.Model.Impl.Monster;
import me.zacharyjia.naruto.game.Model.MonsterFactory;
import me.zacharyjia.naruto.game.utils.NPCLoader;
import tiled.core.ObjectGroup;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by jia19 on 2016/3/21.
 */
public class VilliageScene extends NScene {

    @Override
    public void init() {

        IMap map = MapReader.readMap("/res/map/village.tmx");
        setMap(map);

        SpriteFactory factory = new HeroFactory();
        Hero hero = (Hero)factory.createSprite(this, "/res/characters/naruto.png");

        ObjectGroup objects = (ObjectGroup) map.getUserLayer("npc");
        NPC npc = NPCLoader.loadFromObject(objects.iterator().next());
        npc.startMove();
        npc.setImageCenterY(66);
        addShowable(npc);

        hero.setOnMoveListener(((x, y) -> {
            if (hero.hitTest(npc)) {
                ArrayList<String> list = new ArrayList<String>();
                list.add("卡卡西老师：我今天又在人生的道路上迷失了...");
                list.add("鸣人：......");
                list.add("卡卡西老师：鸣人你要去哪？");
                list.add("鸣人：我要去村子里接任务");

                TalkSequence.getInstance()
                        .setTalkList(list)
                        .start(this);
            }
        }));

        hero.setImageCenterY(66);
        hero.setPosition(13, 23);
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
            //

        });

        hero.setOnEntryListener(entry -> {
            Properties properties = entry.getProperties();
            String entryName = properties.getProperty("name");
            if ("house".equals(entryName)) {
                Intent intent = new Intent(BattleScene.class);
                intent.putExtra("hero", hero);
                Monster monster = new MonsterFactory().createSprite(this, "/res/characters/asima.png");
                intent.putExtra("monster", monster);
                startScene(intent);
                System.out.println("Enter room house!");
            }
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
