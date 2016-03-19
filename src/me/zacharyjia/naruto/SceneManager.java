package me.zacharyjia.naruto;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import me.zacharyjia.naruto.Implement.NMap;
import tiled.core.Map;
import tiled.core.Tile;
import tiled.core.TileLayer;

import java.awt.image.BufferedImage;

/**
 * Created by jia19 on 2016/3/14.
 */
public class SceneManager {

    private Pane pane;
    private Canvas canvas;
    private GraphicsContext gc;

    private boolean launched = false;

    private static SceneManager instance = new SceneManager();

    private NScene currentScene = null;

    private SceneManager(){
    }

    public static SceneManager getInstance() {
        return instance;
    }

    public void init(Pane pane, Canvas canvas) {
        this.pane = pane;
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();

        pane.setOnKeyPressed(event -> {
            if (currentScene != null) {
                currentScene.onKeyDown(event);
            }
        });
        pane.setOnKeyReleased(event -> {
            if (currentScene != null) {
                currentScene.onKeyUp(event);
            }
        });
        pane.setOnMouseClicked(event -> {
            if (currentScene != null) {
                currentScene.onMouseClick(event);
            }
        });
        pane.setOnMouseMoved(event -> {
            if (currentScene != null) {
                currentScene.onMouseMove(event);
            }
        });
        pane.setOnMousePressed(event -> {
            if (currentScene != null) {
                currentScene.onMouseDown(event);
            }
        });
        pane.setOnMouseReleased(event -> {
            if (currentScene != null) {
                currentScene.onMouseUp(event);
            }
        });

    }

    public void switchScene(NScene scene) {
        if (currentScene != null) {
            currentScene.disappear();
        }
        if (scene == null) {
            System.out.println("Scene is null!");
            return;
        }
        currentScene = scene;
        scene.show(pane, canvas);
    }





}
