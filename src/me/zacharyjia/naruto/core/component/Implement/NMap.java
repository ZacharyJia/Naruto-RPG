package me.zacharyjia.naruto.core.component.Implement;

import tiled.core.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            if ("object".equals(layer.getName()) && layer instanceof ObjectGroup) {
                return (ObjectGroup)layer;
            }
        }
        return null;
    }

    public MapLayer getUserTile(String name) {
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

    public List<MapLayer> getUserTileList(String prefix) {
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
