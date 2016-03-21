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
import me.zacharyjia.naruto.game.StartScene;
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

        Config config = Config.getInstance();
        try {
            SceneManager.getInstance().pushScene(Class.forName(config.getStartScene()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
