package me.zacharyjia.naruto.game.Scene;

import javafx.scene.input.KeyCode;
import me.zacharyjia.naruto.core.Intent;
import me.zacharyjia.naruto.game.Model.HeroFactory;
import me.zacharyjia.naruto.core.component.Implement.NPC;
import me.zacharyjia.naruto.core.component.Implement.TalkSequence;
import me.zacharyjia.naruto.core.component.Interface.Direction;
import me.zacharyjia.naruto.core.component.Interface.IMap;
import me.zacharyjia.naruto.core.component.Interface.SpriteFactory;
import me.zacharyjia.naruto.core.scene.NScene;
import me.zacharyjia.naruto.core.utils.MapReader;
import me.zacharyjia.naruto.game.Model.Impl.AttackSkill;
import me.zacharyjia.naruto.game.Model.Impl.Hero;
import me.zacharyjia.naruto.game.Model.Impl.Monster;
import me.zacharyjia.naruto.game.Model.Impl.RecoverSkill;
import me.zacharyjia.naruto.game.Model.MonsterPool;
import me.zacharyjia.naruto.game.components.InfoHub;
import me.zacharyjia.naruto.game.utils.NPCLoader;
import tiled.core.ObjectGroup;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by jia19 on 2016/3/21.
 */
public class VilliageScene extends NScene {

    private InfoHub infoHub;
    private Hero hero;

    @Override
    public void init() {

        IMap map = MapReader.readMap("/res/map/village.tmx");
        setMap(map);

        //初始化主角
        SpriteFactory factory = new HeroFactory();
        hero = (Hero)factory.createSprite(this, "/res/characters/naruto.png");
        hero.setName("漩涡鸣人");

        AttackSkill skill = new AttackSkill();
        skill.setName("物理攻击");
        skill.setAttackValue(10);
        skill.setCost(10);
        hero.setSkill(1, skill);

        RecoverSkill recoverSkill = new RecoverSkill();
        recoverSkill.setChakraValue(20);
        recoverSkill.setLifeValue(20);
        recoverSkill.setName("回复忍术");
        hero.setSkill(0, recoverSkill);

        hero.setImageCenterY(66);
        hero.setPosition(13, 23);
        hero.show();
        addShowable(hero);


        //初始化NPC
        ObjectGroup objects = (ObjectGroup) map.getUserLayer("npc");
        NPC npc = NPCLoader.loadFromObject(objects.iterator().next());
        npc.startMove();
        npc.setImageCenterY(66);
        addShowable(npc);

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

        this.setOnMouseClickListener(event -> {
            //

        });

        // 入口监听事件
        hero.setOnEntryListener(entry -> {
            Properties properties = entry.getProperties();
            String entryName = properties.getProperty("name");
            if ("house".equals(entryName)) {
                Intent intent = new Intent(BattleScene.class);
                intent.putExtra("hero", hero);
                Monster monster = MonsterPool.getInstance().getRandomMonster(50, 100);
                intent.putExtra("monster", monster);
                startScene(intent);
                System.out.println("Enter room house!");
            }
        });

        // 主角信息显示框
        infoHub = new InfoHub();
        infoHub.setName(hero.getName());
        infoHub.setLife(hero.getLife());
        infoHub.setFullLife(hero.getFullLife());
        infoHub.setChakra(hero.getChakra());
        infoHub.setFullChakra(hero.getFullChakra());
        infoHub.setHasChakra(true);

        this.addNode(infoHub);


    }

    @Override
    public void showFinish() {
        super.showFinish();

        //开始对话
        ArrayList<String> list = new ArrayList<>();
        list.add("欢迎来到火影的世界……");
        list.add("这是一片充满了危险的土地");
        list.add("希望你能够活下去……");

        TalkSequence.getInstance().setTalkList(list);
        TalkSequence.getInstance().start(this);

    }

    @Override
    public void onResume() {
        hero.bindInfoHub(infoHub);
    }
}
