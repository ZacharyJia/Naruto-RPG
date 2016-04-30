package me.zacharyjia.naruto.game.Scene;

import javafx.scene.input.KeyCode;
import me.zacharyjia.naruto.Config;
import me.zacharyjia.naruto.core.scene.Intent;
import me.zacharyjia.naruto.core.component.Implement.TalkSequence;
import me.zacharyjia.naruto.core.component.Interface.Direction;
import me.zacharyjia.naruto.core.component.Interface.IMap;
import me.zacharyjia.naruto.game.Model.Impl.Monster;
import me.zacharyjia.naruto.core.scene.NScene;
import me.zacharyjia.naruto.core.scene.SceneManager;
import me.zacharyjia.naruto.core.utils.TiledMapReader;
import me.zacharyjia.naruto.game.Model.Impl.Hero;
import me.zacharyjia.naruto.game.Model.MonsterPool;
import me.zacharyjia.naruto.game.components.InfoHub;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

/**
 * 木叶村外场景
 * Created by jia19 on 2016/4/9.
 */
public class OutsideScene extends NScene {

    private Hero hero;
    private InfoHub infoHub;
    private Random random;

    @Override
    public void init() {

        random = new Random(System.currentTimeMillis());
        //加载地图
        IMap map = TiledMapReader.readMap("/res/map/outside.tmx");
        setMap(map);

        hero = (Hero) getIntent().getExtra("hero", null);
        if (hero == null) {
            return;
        }

        hero.setScene(this);
        hero.show();
        addShowable(hero);

        keyEventInit();
        entryInit();


        // 主角信息显示框
        infoHub = new InfoHub();
        hero.bindInfoHub(infoHub);
        this.addNode(infoHub);
    }

    @Override
    public void onResume() {
        hero.bindInfoHub(infoHub);

        if (!hero.isAlive()) {
            Intent intent = new Intent(StartScene.class);
            SceneManager.getInstance().switchScene(intent);
        }

        hero.setScene(this);
    }

    @Override
    public void showFinish() {
        ArrayList<String> list = new ArrayList<>();
        if (Config.getInstance().isFirst()) {
            list.add("欢迎来到火影的世界……");
            list.add("这是一片充满了危险的土地");
            list.add("希望你能够活下去……");
            Config.getInstance().setFirst(false);
        }
        list.add("木叶村外");

        TalkSequence.getInstance().setTalkList(list);
        TalkSequence.getInstance().start(this);

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

            //随机产生怪物

            int monsterPercent = Config.getInstance().getMonsterPercent();
            if (keyCode.isArrowKey()) {
                if (random.nextInt(100) < monsterPercent) {
                    Intent intent = new Intent(BattleScene.class);
                    intent.putExtra("hero", hero);
                    Monster monster = MonsterPool.getInstance().getRandomMonster(50, 100);
                    intent.putExtra("monster", monster);
                    startScene(intent);
                }
            }

        });
    }

    private void entryInit() {
        // 入口监听事件
        hero.setOnEntryListener(entry -> {
            Properties properties = entry.getProperties();
            String entryName = properties.getProperty("name");
            if ("village".equals(entryName)) {
                Intent intent2 = new Intent(VilliageScene.class);
                intent2.putExtra("hero", hero);
                hero.setPosition(13, 23);
                SceneManager.getInstance().switchScene(intent2);
                System.out.println("Enter village!");
            }
        });
    }
}
