package me.zacharyjia.naruto.game.Scene;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import me.zacharyjia.naruto.core.Exception.ResourcesNotFoundException;
import me.zacharyjia.naruto.core.Intent;
import me.zacharyjia.naruto.core.component.Interface.Direction;
import me.zacharyjia.naruto.core.scene.NScene;
import me.zacharyjia.naruto.core.utils.ResourcesLoader;
import me.zacharyjia.naruto.game.Model.Impl.AttackSkill;
import me.zacharyjia.naruto.game.Model.Impl.Hero;
import me.zacharyjia.naruto.game.Model.Impl.Monster;
import me.zacharyjia.naruto.game.Model.Interface.ISkill;

/**
 * Created by jia19 on 2016/3/31.
 */
public class BattleScene extends NScene {

    private int heroOriginX, heroOriginY, monsterOriginX, monsterOriginY;

    @Override
    public void init() {

        Intent intent = getIntent();
        Hero hero = (Hero)intent.getExtra("hero", null);
        Monster monster = (Monster)intent.getExtra("monster", null);
        if (hero == null || monster == null) {
            //this.finish();
        }

        heroOriginX = hero.getX();
        heroOriginY = hero.getY();
        monsterOriginX = monster.getX();
        monsterOriginY = monster.getY();

        hero.setPosition(22, 13);
        monster.setPosition(8, 13);

        hero.pause();
        hero.setDirection(Direction.DOWN);
        addShowable(hero);
        addShowable(monster);

        AttackSkill skill = new AttackSkill();
        skill.setName("物理攻击");
        hero.setSkill(1, skill);

        try {
            setBackground(new Image(ResourcesLoader.getInputStream("/res/image/battle.jpg")));
        } catch (ResourcesNotFoundException e) {
            e.printStackTrace();
        }

        ISkill[] skills = hero.getSkills();

        for (int i = 0; i < 3; i++) {
            Button btn_skill = new Button("技能" + (i + 1));
            if (skills != null && skills.length > i && skills[i] != null) {
                btn_skill.setText(skills[i].getName());
            }
            else {
                btn_skill.setDisable(true);
            }
            btn_skill.setMaxWidth(150);
            btn_skill.setMinWidth(150);
            btn_skill.setFont(new Font(16));
            btn_skill.setLayoutX(850);
            btn_skill.setLayoutY(350 + i * 50);
            btn_skill.setOnMouseClicked(event -> {
                monster.attacked();
            });
            addNode(btn_skill);
        }



    }
}
