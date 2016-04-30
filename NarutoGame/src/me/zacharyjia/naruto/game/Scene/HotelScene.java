package me.zacharyjia.naruto.game.Scene;

import javafx.scene.input.KeyCode;
import me.zacharyjia.naruto.core.component.Implement.NPC;
import me.zacharyjia.naruto.core.component.Implement.TalkSequence;
import me.zacharyjia.naruto.core.component.Interface.Direction;
import me.zacharyjia.naruto.core.component.Interface.IMap;
import me.zacharyjia.naruto.core.scene.NScene;
import me.zacharyjia.naruto.core.utils.TiledMapReader;
import me.zacharyjia.naruto.game.Model.Impl.Hero;
import me.zacharyjia.naruto.game.components.InfoHub;
import me.zacharyjia.naruto.game.utils.NPCLoader;
import tiled.core.ObjectGroup;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by jia19 on 2016/4/9.
 */
public class HotelScene extends NScene {

    private Hero hero;
    private NPC npc;

    @Override
    public void init() {

        IMap map = TiledMapReader.readMap("/res/map/hotel.tmx");
        setMap(map);

        hero = (Hero) getIntent().getExtra("hero", null);
        if (hero == null) {
            return;
        }
        hero.setScene(this);
        addShowable(hero);

        //初始化NPC
        ObjectGroup objects = (ObjectGroup) map.getUserLayer("npc");
        npc = NPCLoader.loadFromObject(objects.iterator().next());
        npc.setImageCenterY(66);
        npc.startMove();
        addShowable(npc);

        InfoHub infoHub = new InfoHub();
        hero.bindInfoHub(infoHub);
        addNode(infoHub);

        keyEventInit();
        entryInit();
        collisonInit();

    }

    private void keyEventInit() {
        // 方向键检测
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
    }

    private void collisonInit() {
        //碰撞检测
        hero.setOnMoveListener(((x, y) -> {
            if (hero.hitTest(npc)) {
                ArrayList<String> list = new ArrayList<String>();
                list.add("旅店老板娘：你好，请问有什么事？");
                list.add("鸣人：我想休息一下");
                list.add("旅店老板娘：好的，请随意休息吧");
                list.add("一夜之后……");
                list.add("生命恢复……");
                list.add("查克拉恢复……");

                TalkSequence.getInstance().setOnTalkSequenceFinishListener(()->{
                    hero.setLife(hero.getFullLife());
                    hero.setChakra(hero.getFullChakra());
                });
                TalkSequence.getInstance()
                        .setTalkList(list)
                        .start(this);
            }
        }));
    }

    private void entryInit() {
        // 入口监听事件
        hero.setOnEntryListener(entry -> {
            Properties properties = entry.getProperties();
            String entryName = properties.getProperty("name");
            if ("village".equals(entryName)) {
                hero.setPosition(19, 18);
                this.finish();
            }
        });
    }
}
