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
import me.zacharyjia.naruto.core.component.Interface.Direction;
import me.zacharyjia.naruto.core.event.Interface.OnEntryListener;
import me.zacharyjia.naruto.core.scene.NScene;
import tiled.core.MapObject;
import tiled.core.ObjectGroup;
import tiled.core.TileLayer;

import java.util.Properties;

/**
 * 英雄
 * 可控制，可战斗
 * Created by jia19 on 2016/3/11.
 */
public abstract class Hero extends AbstractSprite {

    private Timeline timeline;
    private NScene scene;
    private TileLayer maskLayer;
    private ObjectGroup entryLayer;

    private OnEntryListener onEntryListener;

    private int currentImageIndex = 0;

    public Hero(NScene scene, Image[][] images) {
        if (scene == null)
        {
            throw new SceneNullException("Scene cannot be null!");
        }
        setImage(images);
        this.scene = scene;
        if (scene.getMap() != null) {
            maskLayer = scene.getMap().getMaskLayer();
            entryLayer = scene.getMap().getEntryLayer();
        }

        //四方向切换动画
        timeline = new Timeline(new KeyFrame(Duration.millis(200), event -> {
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
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @Override
    public void pause() {
        super.pause();
        timeline.pause();
    }

    @Override
    public void resume() {
        super.resume();
        timeline.play();
    }

    @Override
    public void pauseAnimation() {
        timeline.pause();
    }

    @Override
    public void resumeAnimation() {
        timeline.play();
    }

    public void setOnEntryListener(OnEntryListener onEntryListener) {
        this.onEntryListener = onEntryListener;
    }

    @Override
    public void setDirection(Direction direction) {
        super.setDirection(direction);
        switch (direction) {
            case UP:
                imageView.setImage(images[3][0]);
                break;
            case DOWN:
                imageView.setImage(images[0][0]);
                break;
            case LEFT:
                imageView.setImage(images[1][0]);
                break;
            case RIGHT:
                imageView.setImage(images[2][0]);
                break;
        }
    }

    public void setScene(NScene scene) {
        this.scene = scene;
        this.entryLayer = scene.getMap().getEntryLayer();
        this.maskLayer = scene.getMap().getMaskLayer();
    }

    @Override
    public void borderTest(int offsetX, int offsetY) {

        int mapWidth = Config.getInstance().getMapWidth();
        int mapHeight = Config.getInstance().getMapHeight();

        x += offsetX;
        y += offsetY;

        //边界判断
        if (x >= mapWidth) {
            x = mapWidth - offsetX;
        }
        else if (x < 0) {
            x = 0;
        }

        if (y >= mapHeight) {
            y = mapHeight - offsetY;
        }
        else if (y < 0) {
            y = 0;
        }
    }

    @Override
    public void maskTest(int originX, int originY) {
        //遮罩层判断
        if (maskLayer != null) {
            if(maskLayer.getTileAt(x, y) == null){
                x = originX;
                y = originY;
            }
        }
    }

    @Override
    public void entryTest() {
        if (entryLayer != null) {
            //entry判断
            for (MapObject entry : entryLayer) {
                if (x == entry.getX() && y == entry.getY()) {
                    if (onEntryListener != null) {
                        onEntryListener.onEntry(entry);
                    }
                }
            }
        }
    }
}
