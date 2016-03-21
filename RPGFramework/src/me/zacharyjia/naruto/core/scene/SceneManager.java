package me.zacharyjia.naruto.core.scene;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import me.zacharyjia.naruto.core.Exception.CallerIsNotPeekException;
import me.zacharyjia.naruto.core.Exception.NotSceneClassException;
import me.zacharyjia.naruto.core.Exception.SceneNullException;
import me.zacharyjia.naruto.core.Intent;

import java.util.Stack;

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
    private Stack<NScene> sceneStack = new Stack<>();

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

    public void pushScene(Intent intent) {
        Class sceneClass = intent.getTarget();
        if (sceneClass == null) {
            throw new SceneNullException("Scene is null!");
        }
        else {
            if (!NScene.class.isAssignableFrom(sceneClass)) {
                throw new NotSceneClassException("The class is not a scene class!");
            }
        }
        NScene scene = null;
        try {
            scene = (NScene)sceneClass.newInstance();
            scene.setIntent(intent);
            scene.init();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (scene != null) {
            sceneStack.push(scene);

            if (currentScene != null) {
                currentScene.disappear();
            }
            currentScene = scene;
            scene.show(pane, canvas);
        }
    }

    public void popScene(NScene caller) {
        if (!caller.equals(sceneStack.peek())) {
            throw new CallerIsNotPeekException("This caller is not the peek scene in scene stack");
        }
        currentScene.disappear();
        currentScene.onFinish();
        currentScene = null;
        if (!sceneStack.isEmpty()) {
            sceneStack.pop();
        }
        if (!sceneStack.isEmpty()) {
            currentScene = sceneStack.peek();
            currentScene.show(pane, canvas);
        }
        else {
            System.exit(0);
        }
    }
    /*
    public void switchScene(NScene scene) {
        if (scene == null) {
            throw new SceneNullException("Scene is null!");
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

        NScene scene = null;
        try {
            scene = (NScene) Class.forName(sceneClassName).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        switchScene(scene);
    }
    */
}
