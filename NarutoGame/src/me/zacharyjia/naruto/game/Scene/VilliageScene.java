package me.zacharyjia.naruto.game.Scene;

import javafx.scene.input.KeyCode;
import me.zacharyjia.naruto.core.Intent;
import me.zacharyjia.naruto.core.component.Implement.NPC;
import me.zacharyjia.naruto.core.component.Implement.TalkSequence;
import me.zacharyjia.naruto.core.component.Interface.Direction;
import me.zacharyjia.naruto.core.component.Interface.IMap;
import me.zacharyjia.naruto.core.scene.NScene;
import me.zacharyjia.naruto.core.scene.SceneManager;
import me.zacharyjia.naruto.core.utils.MapReader;
import me.zacharyjia.naruto.game.Model.Impl.Hero;
import me.zacharyjia.naruto.game.components.InfoHub;
import me.zacharyjia.naruto.game.utils.NPCLoader;
import tiled.core.ObjectGroup;

import java.util.ArrayList;
import java.util.Properties;

/**
 * 村子场景1
 * Created by jia19 on 2016/3/21.
 */
public class VilliageScene extends NScene {

    private InfoHub infoHub;
    private Hero hero;
    private NPC npc;

    @Override
    public void init() {

        IMap map = MapReader.readMap("/res/map/village.tmx");
        setMap(map);

        Intent intent = getIntent();
        //初始化主角
        hero = (Hero) intent.getExtra("hero", null);
        if (hero == null) {
            return;
        }
        hero.setScene(this);
        addShowable(hero);


        //初始化NPC
        ObjectGroup objects = (ObjectGroup) map.getUserLayer("npc");
        npc = NPCLoader.loadFromObject(objects.iterator().next());
        npc.startMove();
        npc.setImageCenterY(66);
        addShowable(npc);

        keyEventInit();
        collisionInit();
        entryInit();

        // 主角信息显示框
        infoHub = new InfoHub();
        hero.bindInfoHub(infoHub);
        this.addNode(infoHub);

    }

    @Override
    public void showFinish() {
        super.showFinish();

        //开始对话
        ArrayList<String> list = new ArrayList<>();
        list.add("木叶村");

        TalkSequence.getInstance().setTalkList(list);
        TalkSequence.getInstance().start(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        hero.bindInfoHub(infoHub);
        hero.setScene(this);

        collisionInit();
        entryInit();
        keyEventInit();
    }

    private void collisionInit() {
        //碰撞检测
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
    }

    private void entryInit() {
        // 入口监听事件
        hero.setOnEntryListener(entry -> {
            Properties properties = entry.getProperties();
            String entryName = properties.getProperty("name");
            if ("house".equals(entryName)) {
                Intent intent1 = new Intent(HotelScene.class);
                intent1.putExtra("hero", hero);
                hero.setPosition(15, 23);
                startScene(intent1);
            }
            else if ("outside".equals(entryName)) {
                Intent intent1 = new Intent(OutsideScene.class);
                hero.setPosition(14, 3);
                intent1.putExtra("hero", hero);
                SceneManager.getInstance().switchScene(intent1);
            }
        });
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
}
