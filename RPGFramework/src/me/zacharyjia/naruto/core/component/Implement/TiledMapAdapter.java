package me.zacharyjia.naruto.core.component.Implement;

import me.zacharyjia.naruto.core.component.Interface.IMap;
import tiled.core.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by jia19 on 2016/3/14.
 */
public class TiledMapAdapter implements IMap {
    private Map map;

    public TiledMapAdapter(Map map) {
        this.map = map;
    }

    public Map getMap() {
        return map;
    }

    public TileLayer getMaskLayer() {
        for (MapLayer layer : map.getLayers()) {
            if ("mask".equals(layer.getName()) && layer instanceof TileLayer) {
                return (TileLayer)layer;
            }
        }
        return null;
    }

    public List<TileLayer> getLayerList() {
        List<TileLayer> list = new ArrayList<>();
        for (MapLayer layer: map.getLayers()) {
            if (layer.getName().startsWith("map_") && layer instanceof TileLayer) {
                list.add((TileLayer) layer);
            }
        }
        return list;
    }

    public ObjectGroup getEntryLayer() {
        for (MapLayer layer : map.getLayers()) {
            if ("entry".equals(layer.getName()) && layer instanceof ObjectGroup) {
                return (ObjectGroup)layer;
            }
        }
        return null;
    }

    public MapLayer getUserLayer(String name) {
        if (name == null) {
            return null;
        }
        for (MapLayer layer : map.getLayers()) {
            if (name.equals(layer.getName()) && layer instanceof ObjectGroup) {
                return (ObjectGroup)layer;
            }
        }
        return null;
    }

    public List<MapLayer> getUserLayerList(String prefix) {
        List<MapLayer> list = new ArrayList<>();
        if (prefix == null) {
            return list;
        }
        for (MapLayer layer: map.getLayers()) {
            if (layer.getName().startsWith(prefix) && layer instanceof TileLayer) {
                list.add(layer);
            }
        }
        return list;
    }
}
