package me.zacharyjia.naruto;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import me.zacharyjia.naruto.core.Intent;
import me.zacharyjia.naruto.core.component.Implement.TalkBox;
import me.zacharyjia.naruto.core.scene.SceneManager;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML Canvas canvas;
    @FXML Pane pane;

    @Override
    public final void initialize(URL location, ResourceBundle resources) {

        SceneManager.getInstance().init(pane, canvas);
        TalkBox.getInstance().setPane(pane);
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
            Class startClass = Class.forName(config.getStartScene());
            Intent intent = new Intent(startClass);
            SceneManager.getInstance().pushScene(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
