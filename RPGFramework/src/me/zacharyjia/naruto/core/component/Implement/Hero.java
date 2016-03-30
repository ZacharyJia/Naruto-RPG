package me.zacharyjia.naruto.core.component.Implement;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.util.Duration;
import me.zacharyjia.naruto.Config;
import me.zacharyjia.naruto.core.Exception.SceneNullException;
import me.zacharyjia.naruto.core.component.Interface.AbstractSprite;
import me.zacharyjia.naruto.core.scene.NScene;
import tiled.core.MapLayer;
import tiled.core.TileLayer;

/**
 * Created by jia19 on 2016/3/11.
 */
public class Hero extends AbstractSprite {

    private Timeline timeline;
    private NScene scene;
    private MapLayer maskLayer;

    private int currentImageIndex = 0;

    public Hero(NScene scene, Image[][] images) {
        if (scene == null)
        {
            throw new SceneNullException("Scene cannot be null!");
        }
        setImage(images);
        this.scene = scene;
        maskLayer = scene.getMap().getMaskLayer();
        timeline = new Timeline(new KeyFrame(Duration.millis(200), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(currentImageIndex == 4) currentImageIndex = 0;
                switch (direction) {
                    case UP:
                        imageView.setImage(images[3][currentImageIndex]);
                        break;
                    case DOWN:
                        imageView.setImage(images[0][currentImageIndex]);
                        break;
                    case LEFT:
                        imageView.setImage(images[1][currentImageIndex]);
                        break;
                    case RIGHT:
                        imageView.setImage(images[2][currentImageIndex]);
                        break;
                }
                currentImageIndex++;
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @Override
    public void move(int offsetX, int offsetY) {
        isMove = true;

        int mapWidth = Config.getInstance().getMapWidth();
        int mapHeight = Config.getInstance().getMapHeight();
        int tileSize = Config.getInstance().getTileSize();

        int originX = x;
        int originY = y;
        x += offsetX;
        y += offsetY;

        //边界判断
        if (x >= mapWidth) {
            x = mapWidth - 1;
        }
        else if (x < 0) {
            x = 0;
        }

        if (y >= mapHeight) {
            y = mapHeight - 1;
        }
        else if (y < 0) {
            y = 0;
        }
        if (maskLayer != null && maskLayer instanceof TileLayer) {
            if(((TileLayer) maskLayer).getTileAt(x, y) == null){
                x = originX;
                y = originY;
            }
        }
        setPosition(x, y);

    }


    @Override
    public void pauseAnimation() {
        timeline.pause();
    }

    @Override
    public void resumeAnimation() {
        timeline.play();
    }

}
