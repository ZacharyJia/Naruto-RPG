package me.zacharyjia.naruto.Implement;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import me.zacharyjia.naruto.Interface.IShowable;
import tiled.core.Map;

/**
 * Created by jia19 on 2016/3/14.
 */
public class NMap{
    private Map map;

    public NMap(Map map) {
        this.map = map;
    }

    public Map getMap() {
        return map;
    }
}
