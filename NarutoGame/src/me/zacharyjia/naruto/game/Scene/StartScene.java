package me.zacharyjia.naruto.game.Scene;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import me.zacharyjia.naruto.Config;
import me.zacharyjia.naruto.core.Exception.ResourcesNotFoundException;
import me.zacharyjia.naruto.core.Intent;
import me.zacharyjia.naruto.core.scene.NScene;
import me.zacharyjia.naruto.core.scene.SceneManager;
import me.zacharyjia.naruto.core.utils.ResourcesLoader;
import me.zacharyjia.naruto.game.Model.HeroFactory;
import me.zacharyjia.naruto.game.Model.Impl.AttackSkill;
import me.zacharyjia.naruto.game.Model.Impl.Hero;
import me.zacharyjia.naruto.game.Model.Impl.RecoverSkill;
import me.zacharyjia.naruto.game.Model.Interface.SpriteFactory;
import me.zacharyjia.naruto.game.utils.SoundManager;

/**
 * 游戏初始画面
 * Created by jia19 on 2016/3/21.
 */
public class StartScene extends NScene {

    @Override
    public void init() {

        try {
            setBackground(new Image(ResourcesLoader.getInputStream("/res/image/splash.jpg")));
        } catch (ResourcesNotFoundException e) {
            e.printStackTrace();
        }

        Button btn_start = new Button("开始游戏");
        btn_start.setFont(new Font(36));
        btn_start.setLayoutX(420);
        btn_start.setLayoutY(300);
        addNode(btn_start);

        final Button btn_exit = new Button("离开游戏");
        btn_exit.setFont(new Font(36));
        btn_exit.setLayoutX(420);
        btn_exit.setLayoutY(420);
        addNode(btn_exit);


        btn_start.setOnMouseClicked(event -> {
            Config.getInstance().setFirst(true);

            //初始化主角
            SpriteFactory factory = new HeroFactory();
            Hero hero = (Hero)factory.createSprite(this, "/res/characters/naruto.png");
            hero.setName("漩涡鸣人");

            AttackSkill attackSkill = new AttackSkill();
            attackSkill.setName("普通攻击");
            attackSkill.setAttackValue(10);
            attackSkill.setCost(0);
            hero.setSkill(1, attackSkill);

            RecoverSkill recoverSkill = new RecoverSkill();
            recoverSkill.setChakraValue(20);
            recoverSkill.setLifeValue(20);
            recoverSkill.setName("回复忍术");
            hero.setSkill(0, recoverSkill);

            AttackSkill skill  = new AttackSkill();
            skill.setName("螺旋丸");
            skill.setSoundFile("/res/sound/luoxuanwan.wav");
            skill.setAttackValue(20);
            skill.setCost(40);
            hero.setSkill(2, skill);


            hero.setImageCenterY(66);
            hero.setPosition(13, 23);

            Intent intent = new Intent(OutsideScene.class);
            intent.putExtra("hero", hero);
            SceneManager.getInstance().pushScene(intent);
        });

        btn_exit.setOnMouseClicked(event -> System.exit(0));

        SoundManager.setBackground("/res/sound/backgroud.wav");
        SoundManager.startBackgroundMusic();

    }
}
