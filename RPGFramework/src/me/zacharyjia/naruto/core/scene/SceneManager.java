package me.zacharyjia.naruto.core.scene;

import javafx.event.Event;
import javafx.event.EventDispatchChain;
import javafx.event.EventDispatcher;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import me.zacharyjia.naruto.core.Exception.CallerIsNotPeekException;
import me.zacharyjia.naruto.core.Exception.NotSceneClassException;
import me.zacharyjia.naruto.core.Exception.SceneNullException;
import me.zacharyjia.naruto.core.Intent;

import java.util.Stack;

/**
 * 场景管理器
 * 用于实现场景之间的切换，及场景的生命周期管理
 * 主要实现基于栈的场景管理
 *
 * Created by jia19 on 2016/3/14.
 */
public class SceneManager {

    private static SceneManager instance = null;

    private Pane pane;
    private Canvas canvas;
    private GraphicsContext gc;

    private boolean launched = false;

    private NScene currentScene = null;
    private Stack<NScene> sceneStack = new Stack<>();

    private SceneManager(){
    }

    //单例实现
    public static SceneManager getInstance() {
        if (instance == null) {
            synchronized (SceneManager.class) {
                if (instance == null) {
                    instance = new SceneManager();
                }
            }
        }
        return instance;
    }

    //初始化
    public void init(Pane pane, Canvas canvas) {
        this.pane = pane;
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();

        //实现将pane的事件监听转发到当前的场景上
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

    //创建一个新的场景，将其压到场景栈上
    public void pushScene(Intent intent) {

        //从intent中获取要创建的场景
        Class sceneClass = intent.getTarget();
        if (sceneClass == null) {
            throw new SceneNullException("Scene is null!");
        }
        else {
            if (!NScene.class.isAssignableFrom(sceneClass)) {
                throw new NotSceneClassException("The class is not a scene class!");
            }
        }

        //创建新的场景
        NScene scene = null;
        try {
            scene = (NScene)sceneClass.newInstance();
            scene.setIntent(intent);
            //调用新场景的初始化方法
            scene.init();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //场景压栈
        if (scene != null) {
            sceneStack.push(scene);

            if (currentScene != null) {
                //调用上一个场景的disappear方法
                currentScene.disappear();
            }
            currentScene = scene;
            //分别调用上一个场景的show方法和showFinish方法
            scene.show(pane, canvas);
            scene.showFinish();
        }
    }

    //将当前场景出栈，如果栈中没有其他的场景，则结束游戏
    public void popScene(NScene caller) {
        //确定该方法的调用者只能为栈顶场景
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
            currentScene.onResume();
        }
        else {
            System.exit(0);
        }
    }

    //切换场景，将当前栈清空后再创建新的场景
    public void switchScene(Intent intent) {
        sceneStack.clear();
        pushScene(intent);
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
