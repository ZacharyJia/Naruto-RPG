package me.zacharyjia.naruto.core.scene;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import me.zacharyjia.naruto.Config;
import me.zacharyjia.naruto.core.Intent;
import me.zacharyjia.naruto.core.component.Implement.NMap;
import me.zacharyjia.naruto.core.event.Interface.*;
import me.zacharyjia.naruto.core.component.Interface.IShowable;
import tiled.core.Map;
import tiled.core.Tile;
import tiled.core.TileLayer;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jia19 on 2016/3/14.
 */
public abstract class NScene {
    //键盘鼠标事件监听器
    private OnKeyDownListener onKeyDownListener = null;
    private OnKeyUpListener onKeyUpListener = null;
    private OnMouseClickListener onMouseClickListener = null;
    private OnMouseDownListener onMouseDownListener = null;
    private OnMouseMoveListener onMouseMoveListener = null;
    private OnMouseUpListener onMouseUpListener = null;

    private Image background;
    private NMap map;
    private ArrayList<IShowable> showables = new ArrayList<>();
    private ArrayList<Node> nodes = new ArrayList<>();

    private Pane pane;
    private Canvas canvas;
    private boolean isShow = false;
    private boolean isPause = false;

    private Intent intent = null;

    public abstract void init();

    public void showFinish(){}

    public void onFinish(){

    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public Image getBackground() {
        return background;
    }

    public void setBackground(Image background) {
        this.background = background;
    }

    public void setMap(NMap map) {
        this.map = map;
    }

    public NMap getMap() {
        return map;
    }

    /**
     * 返回节点的一份拷贝，获取到的children不能修改！
     * @return 所有添加到该场景的节点
     */
    public ArrayList<IShowable> getChildren() {
        return (ArrayList<IShowable> )showables.clone();
    }

    public boolean removeChild(IShowable showable) {
        boolean result = showables.remove(showable);
        update();
        return result;
    }

    public IShowable removeChild(int index) {
        IShowable result = showables.remove(index);
        update();
        return result;
    }

    public void clearChildren() {
        showables.clear();
        update();
    }

    public void addShowable(IShowable node) {
        showables.add(node);
        update();
    }

    public boolean removeNode(Node node) {
        boolean result =  nodes.remove(node);
        update();
        return result;
    }

    public Node removeNode(int index) {
        Node result =  nodes.remove(index);
        update();
        return result;
    }

    public void clearNode() {
        nodes.clear();
        update();
        if (pane != null) {
            pane.requestFocus();
        }
    }

    public void addNode(Node node) {
        nodes.add(node);
        update();
    }

    public void setOnMouseClickListener(OnMouseClickListener onMouseClickListener) {
        this.onMouseClickListener = onMouseClickListener;
    }

    public void onMouseClick(MouseEvent event) {
        if (onMouseClickListener != null) {
            this.onMouseClickListener.onMouseClick(event);
        }
    }

    public void setOnMouseDownListener(OnMouseDownListener onMouseDownListener) {
        this.onMouseDownListener = onMouseDownListener;
    }

    public void onMouseDown(MouseEvent event) {
        if (onMouseDownListener != null && !isPause) {
            this.onMouseDownListener.onMouseDown(event);
        }
    }

    public void setOnMouseMoveListener(OnMouseMoveListener onMouseMoveListener) {
        this.onMouseMoveListener = onMouseMoveListener;
    }

    public void onMouseMove(MouseEvent event) {
        if (onMouseMoveListener != null && !isPause) {
            this.onMouseMoveListener.onMouseMove(event);
        }
    }

    public void setOnMouseUpListener(OnMouseUpListener onMouseUpListener) {
        this.onMouseUpListener = onMouseUpListener;
    }

    public void onMouseUp(MouseEvent event) {
        if (onMouseUpListener != null && !isPause) {
            this.onMouseUpListener.onMouseUp(event);
        }
    }

    public void setOnKeyUpListener(OnKeyUpListener onKeyUpListener) {
        this.onKeyUpListener = onKeyUpListener;
    }

    public void onKeyUp(KeyEvent event) {
        if (onKeyUpListener != null && !isPause) {
            this.onKeyUpListener.onKeyUp(event);
        }
    }

    public void setOnKeyDownListener(OnKeyDownListener onKeyDownListener) {
        this.onKeyDownListener = onKeyDownListener;
    }

    public void onKeyDown(KeyEvent event) {
        if (onKeyDownListener != null && !isPause) {
            this.onKeyDownListener.onKeyDown(event);
        }
    }

    public OnMouseClickListener getOnMouseClickListener() {
        return this.onMouseClickListener;
    }

    public void pauseEvent() {
        isPause = true;
    }

    public void resumeEvent() {
        isPause = false;
    }

    public void show(Pane pane, Canvas canvas) {

        isShow = true;

        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        pane.getChildren().clear();
        pane.getChildren().add(canvas);
        pane.requestFocus();

        if (background != null) {
            canvas.getGraphicsContext2D().drawImage(background, 0, 0,
                    Config.getInstance().getWindowWidth(), Config.getInstance().getWindowHeight());
        }
        if (map != null) {
            drawMap(canvas.getGraphicsContext2D(), map);
        }

        for (IShowable showable : showables) {
            pane.getChildren().add(showable.getImageView());
        }

        for (Node node : nodes) {
            pane.getChildren().add(node);
        }

        showFinish();
    }
    public void disappear() {
        this.pane = null;
        this.canvas = null;
        isShow = false;
    }

    final public void finish() {
        SceneManager.getInstance().popScene(this);
    }

    final public void startScene(Intent intent) {
        SceneManager.getInstance().pushScene(intent);
    }

    public void update() {
        if (pane != null && canvas != null) {
            show(pane, canvas);
        }
    }

    private void drawMap(GraphicsContext gc, NMap nMap) {
        Map map = nMap.getMap();
        List<TileLayer> layers = nMap.getLayerList();
        for (TileLayer layer : layers) {
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
