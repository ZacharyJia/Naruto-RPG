package me.zacharyjia.naruto.game;

import javafx.scene.input.KeyCode;
import me.zacharyjia.naruto.core.component.Implement.Hero;
import me.zacharyjia.naruto.core.component.Implement.NMap;
import me.zacharyjia.naruto.core.component.Interface.ISprite;
import me.zacharyjia.naruto.core.scene.NScene;
import me.zacharyjia.naruto.core.utils.CharacterImageLoader;
import tiled.core.Map;
import tiled.io.TMXMapReader;

/**
 * Created by jia19 on 2016/3/21.
 */
public class VilliageScene extends NScene {

    @Override
    public void init() {

        Map map = null;
        try {
            TMXMapReader reader = new TMXMapReader();
            map = reader.readMap("res/map/village.tmx");
            //drawMap(gc, res.map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (map != null) {
            setMap(new NMap(map));
        }

        Hero hero = new Hero(CharacterImageLoader.getImages("res/characters/naruto.png"));
        hero.show();
        addShowable(hero);
        setOnKeyDownListener(keyEvent -> {
            KeyCode keyCode = keyEvent.getCode();
            if (keyCode.equals(KeyCode.UP)) {
                hero.setDirection(ISprite.Direction.UP);
                hero.move(0, -1);
            }
            else if (keyCode.equals(KeyCode.DOWN)) {
                hero.setDirection(ISprite.Direction.DOWN);
                hero.move(0, 1);
            }
            else if (keyCode.equals(KeyCode.LEFT)) {
                hero.setDirection(ISprite.Direction.LEFT);
                hero.move(-1, 0);
            }
            else if (keyCode.equals(KeyCode.RIGHT)) {
                hero.setDirection(ISprite.Direction.RIGHT);
                hero.move(1, 0);
            }
        });

    }
}
