package me.zacharyjia.naruto.game.Scene;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import me.zacharyjia.naruto.core.Exception.ResourcesNotFoundException;
import me.zacharyjia.naruto.core.Intent;
import me.zacharyjia.naruto.core.scene.NScene;
import me.zacharyjia.naruto.core.scene.SceneManager;
import me.zacharyjia.naruto.core.utils.ResourcesLoader;
import me.zacharyjia.naruto.game.components.InfoHub;

/**
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
            Intent intent = new Intent(VilliageScene.class);
            intent.putExtra("key", "Hello!!!");
            SceneManager.getInstance().pushScene(intent);
        });

        btn_exit.setOnMouseClicked(event -> System.exit(0));

    }
}
