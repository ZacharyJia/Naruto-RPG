package me.zacharyjia.naruto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //启动窗体
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        Config config = Config.getInstance();
        primaryStage.setTitle(config.getGameName());
        Scene scene = new Scene(root, config.getWindowWidth(), config.getWindowHeight());
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
