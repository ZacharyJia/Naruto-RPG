package me.zacharyjia.naruto.game.Scene;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import me.zacharyjia.naruto.core.Exception.ResourcesNotFoundException;
import me.zacharyjia.naruto.core.Intent;
import me.zacharyjia.naruto.core.component.Implement.TalkSequence;
import me.zacharyjia.naruto.core.component.Interface.Direction;
import me.zacharyjia.naruto.core.scene.NScene;
import me.zacharyjia.naruto.core.utils.ResourcesLoader;
import me.zacharyjia.naruto.game.Model.Impl.AttackSkill;
import me.zacharyjia.naruto.game.Model.Impl.Hero;
import me.zacharyjia.naruto.game.Model.Impl.Monster;
import me.zacharyjia.naruto.game.Model.Interface.ISkill;
import me.zacharyjia.naruto.game.components.InfoHub;
import me.zacharyjia.naruto.game.utils.SoundManager;

import java.util.ArrayList;

/**
 * Created by jia19 on 2016/3/31.
 */
public class BattleScene extends NScene {

    private Hero hero;
    private Monster monster;
    private int heroOriginX, heroOriginY, monsterOriginX, monsterOriginY;
    private TalkSequence talkSequence = TalkSequence.getInstance();
    private ArrayList<String> list = new ArrayList<>();
    private Button btn_skills[] = new Button[3];
    ISkill[] skills;

    private ImageView leftBlood, rightBlood;

    @Override
    public void init() {

        //背景设置
        try {
            setBackground(new Image(ResourcesLoader.getInputStream("/res/image/battle.jpg")));
        } catch (ResourcesNotFoundException e) {
            e.printStackTrace();
        }

        Intent intent = getIntent();
        hero = (Hero)intent.getExtra("hero", null);
        monster = (Monster)intent.getExtra("monster", null);
        if (hero == null || monster == null) {
            //this.finish();
        }

        //英雄设置
        hero.pause();
        hero.setDirection(Direction.DOWN);
        addShowable(hero);
        addShowable(monster);

        heroOriginX = hero.getX();
        heroOriginY = hero.getY();
        monsterOriginX = monster.getX();
        monsterOriginY = monster.getY();

        hero.setPosition(22, 13);
        monster.setPosition(8, 13);
        try {
            Image bloodImg = new Image(ResourcesLoader.getInputStream("/res/image/blood.png"));
            leftBlood = new ImageView(bloodImg);
            rightBlood = new ImageView(bloodImg);
            leftBlood.setLayoutX(monster.getImageView().getLayoutX());
            leftBlood.setLayoutY(monster.getImageView().getLayoutY());
            rightBlood.setLayoutX(hero.getImageView().getLayoutX());
            rightBlood.setLayoutY(hero.getImageView().getLayoutY());
            leftBlood.setVisible(false);
            rightBlood.setVisible(false);
            addNode(leftBlood);
            addNode(rightBlood);

        } catch (ResourcesNotFoundException e) {
            e.printStackTrace();
        }


        //用户信息HUB显示设置
        InfoHub heroHub = new InfoHub();
        InfoHub monsterHub = new InfoHub();
        heroHub.setLayoutX(821);
        hero.bindInfoHub(heroHub);
        monster.bindInfoHub(monsterHub);

        addNode(heroHub);
        addNode(monsterHub);

        //技能按钮加载
        skills = hero.getSkills();


        for (int i = 0; i < 3; i++) {
            btn_skills[i] = new Button("技能" + (i + 1));
            if (skills != null && skills.length > i && skills[i] != null) {
                btn_skills[i].setText(skills[i].getName());
            }
            else {
                btn_skills[i].setDisable(true);
            }
            btn_skills[i].setMaxWidth(150);
            btn_skills[i].setMinWidth(150);
            btn_skills[i].setFont(new Font(16));
            btn_skills[i].setLayoutX(850);
            btn_skills[i].setLayoutY(350 + i * 50);
            final int finalI = i;
            btn_skills[i].setOnMouseClicked(event -> {
                disableButtons();
                if (skills[finalI] instanceof AttackSkill) {
                    leftBlood.setVisible(true);
                }
                hero.useSkill(monster, skills[finalI], ()->{
                    leftBlood.setVisible(false);
                    if (!monster.isAlive()) {
                        SoundManager.play("/res/sound/success.wav");
                        list.clear();
                        SoundManager.stopBackgroundMusic();
                        list.add("恭喜你取得胜利！");
                        talkSequence.setTalkList(list);
                        talkSequence.start(this);
                        talkSequence.setOnTalkSequenceFinishListener(this::battleFinish);
                    }
                    else
                    {
                        rightBlood.setVisible(true);
                        String soundFile = monster.getSoundFile();
                        if (soundFile != null) {
                            SoundManager.play(soundFile);
                        }
                        monster.attack(hero, monster.getAttackValue(), ()->{
                            rightBlood.setVisible(false);
                            if (!hero.isAlive()) {
                                list.clear();
                                SoundManager.stopBackgroundMusic();
                                SoundManager.play("/res/sound/fail.wav");
                                list.add("十年生死两茫茫，不思量自难忘");
                                talkSequence.setTalkList(list);
                                talkSequence.setOnTalkSequenceFinishListener(this::battleFinish);
                                talkSequence.start(this);
                            }
                            else {
                                enableButtons();
                            }
                        });
                    }
                });
            });
            addNode(btn_skills[i]);
        }
        disableButtons();
        enableButtons();
    }



    private void battleFinish()
    {
        hero.setPosition(heroOriginX, heroOriginY);
        monster.setPosition(monsterOriginX, monsterOriginY);
        this.finish();
        SoundManager.startBackgroundMusic();
    }
    protected void disableButtons() {
        for (Button btn : btn_skills) {
           btn.setDisable(true);
        }
    }

    protected void enableButtons() {
        for (int i = 0; i < btn_skills.length; i++) {
            if (skills[i] != null) {
                if (skills[i] instanceof AttackSkill) {
                    if (((AttackSkill) skills[i]).getCost() <= hero.getChakra()) {
                        btn_skills[i].setDisable(false);
                    }
                }
                else {
                    btn_skills[i].setDisable(false);
                }
            }

        }
    }

}
