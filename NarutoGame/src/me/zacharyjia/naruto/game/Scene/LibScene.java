package me.zacharyjia.naruto.game.Scene;

import javafx.scene.input.KeyCode;
import me.zacharyjia.naruto.core.component.Interface.Direction;
import me.zacharyjia.naruto.core.component.Interface.IMap;
import me.zacharyjia.naruto.core.scene.NScene;
import me.zacharyjia.naruto.core.utils.MapReader;
import me.zacharyjia.naruto.game.Model.Impl.Hero;
import me.zacharyjia.naruto.game.components.InfoHub;

import java.util.Properties;

/**
 * Created by jia19 on 2016/4/9.
 */
public class LibScene extends NScene {

    private Hero hero;

    @Override
    public void init() {

        IMap map = MapReader.readMap("/res/map/lib.tmx");
        setMap(map);

        hero = (Hero) getIntent().getExtra("hero", null);
        if (hero == null) {
            return;
        }
        hero.setScene(this);
        addShowable(hero);

        InfoHub infoHub = new InfoHub();
        hero.bindInfoHub(infoHub);
        addNode(infoHub);

        keyEventInit();
        entryInit();
        collisonInit();

    }

    private void keyEventInit() {
        // 方向键检测
        setOnKeyDownListener(keyEvent -> {
            KeyCode keyCode = keyEvent.getCode();
            if (keyCode.equals(KeyCode.UP)) {
                hero.setDirection(Direction.UP);
                hero.move(0, -1);
            }
            else if (keyCode.equals(KeyCode.DOWN)) {
                hero.setDirection(Direction.DOWN);
                hero.move(0, 1);
            }
            else if (keyCode.equals(KeyCode.LEFT)) {
                hero.setDirection(Direction.LEFT);
                hero.move(-1, 0);
            }
            else if (keyCode.equals(KeyCode.RIGHT)) {
                hero.setDirection(Direction.RIGHT);
                hero.move(1, 0);
            }
        });
    }

    private void collisonInit() {
        //碰撞检测
        hero.setOnMoveListener(null);
    }

    private void entryInit() {
        // 入口监听事件
        hero.setOnEntryListener(entry -> {
            Properties properties = entry.getProperties();
            String entryName = properties.getProperty("name");
            if ("door".equals(entryName)) {
                hero.setPosition(7, 16);
                this.finish();
            }
        });
    }
}
