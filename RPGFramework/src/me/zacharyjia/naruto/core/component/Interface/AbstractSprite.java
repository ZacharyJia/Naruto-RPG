package me.zacharyjia.naruto.core.component.Interface;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import me.zacharyjia.naruto.Config;
import me.zacharyjia.naruto.core.component.Interface.IShowable;
import me.zacharyjia.naruto.core.event.Interface.OnMoveListener;

/**
 * Created by jia19 on 2016/3/21.
 */
public abstract class AbstractSprite implements IShowable {

    protected int x, y;//当前位置
    protected Direction direction = Direction.UP; //当前方向
    protected int imageCenterX = 0, imageCenterY = 0;

    protected Image images[][];

    protected ImageView imageView = new ImageView();

    protected OnMoveListener onMoveListener;

    protected boolean isMove = false;

    public void setImage(Image[][] images) {
        this.images = images;
        if (images[0][0] != null) {
            imageCenterX = (int)images[0][0].getWidth() / 2;
            imageCenterY = (int)images[0][0].getHeight() / 2;
        }
    }

    public void setOnMoveListener(OnMoveListener onMoveListener) {
        this.onMoveListener = onMoveListener;
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
        return this.imageView;
    }

    public void move(int offsetX, int offsetY){}

    public void pause() {
        isMove = false;
    }

    public void resume() {
        isMove = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        int tileSize = Config.getInstance().getTileSize();

        this.x = x;
        this.y = y;

        imageView.setLayoutX(tileSize * x + tileSize / 2 - imageCenterX);
        imageView.setLayoutY(tileSize * y + tileSize / 2 - imageCenterY);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setImageCenterY(int imageCenterY) {
        this.imageCenterY = imageCenterY;
    }

    public void setImageCenterX(int imageCenterX) {
        this.imageCenterX = imageCenterX;
    }

    public boolean hitTest(AbstractSprite sprite) {
        if (this.x == sprite.x && this.y == sprite.y) {
            return true;
        }
        else {
            return false;
        }
    }

    protected boolean alive = true;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
