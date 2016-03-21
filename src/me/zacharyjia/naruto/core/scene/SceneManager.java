package me.zacharyjia.naruto.core.scene;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

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
        if (scene == null) {
            System.out.println("Scene is null!");
            return;
        }
        if (currentScene != null) {
            currentScene.disappear();
        }
        currentScene = scene;
        scene.show(pane, canvas);
    }

    public void switchScene(String sceneClassName) {
        if(sceneClassName == null) {
            System.out.println("Scene Class Name is null!");
        }

        try {
            currentScene = (NScene) Class.forName(sceneClassName).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (currentScene != null) {
            currentScene.disappear();
        }

        currentScene.show(pane, canvas);

    }

}
