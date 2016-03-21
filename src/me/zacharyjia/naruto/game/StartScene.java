package me.zacharyjia.naruto.game;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import me.zacharyjia.naruto.core.component.Implement.Hero;
import me.zacharyjia.naruto.core.component.Implement.NMap;
import me.zacharyjia.naruto.core.component.Interface.ISprite;
import me.zacharyjia.naruto.core.scene.NScene;
import me.zacharyjia.naruto.core.scene.SceneManager;
import me.zacharyjia.naruto.core.utils.CharacterImageLoader;
import tiled.core.Map;
import tiled.io.TMXMapReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by jia19 on 2016/3/21.
 */
public class StartScene extends NScene {

    @Override
    public void init() {

        try {
            setBackground(new Image(new FileInputStream("res/image/splash.jpg")));
        } catch (FileNotFoundException e) {
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
            SceneManager.getInstance().pushScene(VilliageScene.class);
        });

        btn_exit.setOnMouseClicked(event -> System.exit(0));

    }
}
