package me.zacharyjia.naruto;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import me.zacharyjia.naruto.core.scene.AbstractIntent;
import me.zacharyjia.naruto.core.scene.Intent;
import me.zacharyjia.naruto.core.component.Implement.TalkBox;
import me.zacharyjia.naruto.core.scene.SceneManager;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML Canvas canvas;
    @FXML Pane pane;

    @Override
    public final void initialize(URL location, ResourceBundle resources) {
        //初始化窗体管理器
        SceneManager.getInstance().init(pane, canvas);
        TalkBox.getInstance().setPane(pane);
        pane.setPadding(new Insets(0,0,0,0));
        start();
    }

    public void start() {

        //通过配置文件启动第一个场景
        Config config = Config.getInstance();
        try {
            Class startClass = Class.forName(config.getStartScene());
            AbstractIntent intent = new Intent(startClass);
            SceneManager.getInstance().pushScene(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
