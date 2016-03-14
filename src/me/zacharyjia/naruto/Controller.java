package me.zacharyjia.naruto;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import me.zacharyjia.naruto.utils.CharacterImageLoader;
import tiled.core.*;
import tiled.io.TMXMapReader;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML Canvas canvas;
    @FXML Pane pane;

    Map map = null;
    ImageView iv_hero = new ImageView();

    static int curImage = 0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        final Image img;
        try {
            img = new Image(new FileInputStream("res/image/splash.jpg"));
            gc.drawImage(img, 0, 0, 1024, 768);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        gc.setFill(Color.ORANGERED);
        Font font = new Font("微软雅黑", 72);
        gc.setFont(font);
        gc.fillText("火影传说", 370, 200);

        try {
            TMXMapReader reader = new TMXMapReader();
            map = reader.readMap("res/map/village.tmx");
            //drawMap(gc, res.map);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Button btn_start = new Button("开始游戏");
        btn_start.setFont(new Font(36));
        btn_start.setLayoutX(420);
        btn_start.setLayoutY(300);
        pane.getChildren().add(btn_start);

        final Button btn_exit = new Button("离开游戏");
        btn_exit.setFont(new Font(36));
        btn_exit.setLayoutX(420);
        btn_exit.setLayoutY(420);
        pane.getChildren().add(btn_exit);

        btn_start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                final Image[][] images;
                try {
                    images = CharacterImageLoader.getImages("res/characters/naruto.png");
                    iv_hero.setImage(images[0][0]);

                    final Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            if (curImage == 4) curImage = 0;
                            iv_hero.setImage(images[3][curImage]);
                            curImage++;

                        }
                    }));

                    timeline.setCycleCount(Timeline.INDEFINITE);
                    timeline.play();


                } catch (IOException e) {
                    e.printStackTrace();
                }
                pane.getChildren().clear();
                pane.getChildren().add(canvas);
                pane.getChildren().add(iv_hero);
                drawMap(gc, map);

            }
        });

        btn_exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.exit(0);
            }
        });



    }

    @FXML
    private void onMouseClicked() {
        System.out.println("Canvas is clicked!");
    }

    void drawMap(GraphicsContext gc, Map map) {
        for (int l = 0; l < map.getLayerCount(); l++) {
            TileLayer layer = (TileLayer) map.getLayer(l);
            int x = layer.getWidth();
            int y = layer.getHeight();
            for (int i = 0; i < y; i++) {
                for (int j = 0; j < x; j++) {
                    Tile t = layer.getTileAt(j, i);
                    if (t == null) continue;
                    BufferedImage bImg = (BufferedImage) t.getImage();
                    WritableImage writableImage = new WritableImage(32, 32);
                    writableImage = SwingFXUtils.toFXImage(bImg, writableImage);
                    gc.drawImage(writableImage, j * 32, i * 32);
                }
            }
        }
    }
}
