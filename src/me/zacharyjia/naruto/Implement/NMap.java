package me.zacharyjia.naruto.Implement;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import me.zacharyjia.naruto.Interface.IShowable;
import tiled.core.Map;
import tiled.core.MapLayer;
import tiled.core.TileLayer;

import java.util.ArrayList;
import java.util.List;

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

    public MapLayer getMaskLayer() {
        for (MapLayer layer : map.getLayers()) {
            if ("mast".equals(layer.getName())) {
                return layer;
            }
        }

        return null;
    }

    public List<TileLayer> getLayerList() {
        List<TileLayer> list = new ArrayList<>();
        for (MapLayer layer: map.getLayers()) {
            if (!"mask".equals(layer.getName())) {
                list.add((TileLayer) layer);
            }
        }
        return list;
    }
}
