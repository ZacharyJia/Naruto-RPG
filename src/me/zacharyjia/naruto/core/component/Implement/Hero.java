package me.zacharyjia.naruto.core.component.Implement;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import me.zacharyjia.naruto.Config;
import me.zacharyjia.naruto.core.component.Interface.IShowable;
import me.zacharyjia.naruto.core.component.Interface.ISprite;

/**
 * Created by jia19 on 2016/3/11.
 */
public class Hero implements ISprite, IShowable {

    private int life; //生命值
    private int chakra; //查克拉

    private Direction direction = Direction.UP; //当前方向
    private boolean isMove = false;

    private int x, y;//当前位置

    private Timeline timeline;

    private Image images[][];

    private ImageView imageView = new ImageView();
    private int currentImageIndex = 0;

    public Hero(Image[][] images) {
        this.images = images;

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

        imageView.setLayoutX(tileSize * x);
        imageView.setLayoutY(tileSize * y);
        timeline.play();

    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setPosition(int x, int y) {
        int tileSize = Config.getInstance().getTileSize();

        this.x = x;
        this.y = y;

        imageView.setLayoutX(tileSize * x);
        imageView.setLayoutY(tileSize * y);
    }

    @Override
    public void show() {
        imageView.setVisible(true);
    }

    @Override
    public void disappear() {
        imageView.setVisible(false);
    }

    @Override
    public ImageView getImageView() {
        return imageView;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
