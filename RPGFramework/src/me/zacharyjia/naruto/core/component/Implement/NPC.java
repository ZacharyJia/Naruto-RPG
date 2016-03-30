package me.zacharyjia.naruto.core.component.Implement;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import me.zacharyjia.naruto.Config;
import me.zacharyjia.naruto.core.component.Interface.AbstractSprite;
import me.zacharyjia.naruto.core.component.Interface.Direction;
import me.zacharyjia.naruto.core.utils.CharacterImageLoader;
import me.zacharyjia.naruto.core.utils.NPCAction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static me.zacharyjia.naruto.core.component.Interface.Direction.*;

/**
 * Created by jia19 on 2016/3/11.
 */
public class NPC extends AbstractSprite {

    private Timeline timeline;
    private Timeline moveTimeline;

    private List<NPCAction> actions;
    private ListIterator<NPCAction> it;
    private volatile int valueLeft = 0;
    private volatile Direction currentDirection = DOWN;

    private int currentImageIndex = 0;

    public NPC(Image[][] images) {
        setImage(images);

        EventHandler handler = event -> {
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
        };

        timeline = new Timeline(new KeyFrame(Duration.millis(200), handler));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        moveTimeline = new Timeline(new KeyFrame(Duration.millis(400), event -> {
            if (valueLeft > 0) {
                switch (currentDirection) {
                    case UP:
                        move(0, -1);
                        break;
                    case DOWN:
                        move(0, 1);
                        break;
                    case LEFT:
                        move(-1, 0);
                        break;
                    case RIGHT:
                        move(1, 0);
                        break;
                }
                valueLeft--;
            }
            else {
                if (!it.hasNext()) {
                    it = actions.listIterator(0);
                }
                NPCAction action = it.next();
                currentDirection = action.getAction();
                setDirection(action.getAction());
                valueLeft = action.getValue();
            }
        }));

        moveTimeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void setActions(List<NPCAction> actions) {
        this.actions = actions;
        it = actions.listIterator(0);
    }

    public void startMove() {
        if (this.moveTimeline != null) {
            this.moveTimeline.play();
        }
    }

    public void stopMove() {
        if (this.moveTimeline != null) {
            this.moveTimeline.pause();
        }
    }

    @Override
    public void move(int offsetX, int offsetY) {
        int mapWidth = Config.getInstance().getMapWidth();
        int mapHeight = Config.getInstance().getMapHeight();
        int tileSize = Config.getInstance().getTileSize();

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
        setPosition(x, y);

    }

    @Override
    public void pauseAnimation() {
        timeline.pause();
        moveTimeline.pause();
    }

    @Override
    public void resumeAnimation() {
        timeline.play();
        moveTimeline.play();
    }

}
