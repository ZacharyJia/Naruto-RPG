package me.zacharyjia.naruto;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML Canvas canvas;
    @FXML Pane pane;

    @Override
    public final void initialize(URL location, ResourceBundle resources) {

        SceneManager.getInstance().init(pane, canvas);
        start();

        /*
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.ORANGERED);
        Font font = new Font("微软雅黑", 72);
        gc.setFont(font);
        gc.fillText("火影传说", 370, 200);
        */
    }

    public void start() {
        NScene scene = new NScene();
        try {
            scene.setBackground(new Image(new FileInputStream("res/image/splash.jpg")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Button btn_start = new Button("开始游戏");
        btn_start.setFont(new Font(36));
        btn_start.setLayoutX(420);
        btn_start.setLayoutY(300);
        scene.addNode(btn_start);

        final Button btn_exit = new Button("离开游戏");
        btn_exit.setFont(new Font(36));
        btn_exit.setLayoutX(420);
        btn_exit.setLayoutY(420);
        scene.addNode(btn_exit);

        Map map = null;
        try {
            TMXMapReader reader = new TMXMapReader();
            map = reader.readMap("res/map/village.tmx");
            //drawMap(gc, res.map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final Map finalMap = map;
        btn_start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                NScene scene = new NScene();
                scene.setMap(new NMap(finalMap));

                Hero hero = new Hero(CharacterImageLoader.getImages("res/characters/naruto.png"));
                hero.show();
                scene.addShowable(hero);
                scene.setOnKeyDownListener(keyEvent -> {
                    KeyCode keyCode = keyEvent.getCode();
                    if (keyCode.equals(KeyCode.UP)) {
                        hero.setDirection(ISprite.Direction.UP);
                        hero.move(0, -1);
                    }
                    else if (keyCode.equals(KeyCode.DOWN)) {
                        hero.setDirection(ISprite.Direction.DOWN);
                        hero.move(0, 1);
                    }
                    else if (keyCode.equals(KeyCode.LEFT)) {
                        hero.setDirection(ISprite.Direction.LEFT);
                        hero.move(-1, 0);
                    }
                    else if (keyCode.equals(KeyCode.RIGHT)) {
                        hero.setDirection(ISprite.Direction.RIGHT);
                        hero.move(1, 0);
                    }
                });

                SceneManager.getInstance().switchScene(scene);

            }
        });

        btn_exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.exit(0);
            }
        });

        SceneManager.getInstance().switchScene(scene);

    }
}
