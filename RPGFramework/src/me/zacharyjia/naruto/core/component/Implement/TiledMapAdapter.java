package me.zacharyjia.naruto.core.component.Implement;

import me.zacharyjia.naruto.core.component.Interface.IMap;
import tiled.core.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 地图适配器
 * 用来适配不同的地图类
 *
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

    //获取遮罩层
    public TileLayer getMaskLayer() {
        if(map == null) {
            return null;
        }
        for (MapLayer layer : map.getLayers()) {
            if ("mask".equals(layer.getName()) && layer instanceof TileLayer) {
                return (TileLayer)layer;
            }
        }
        return null;
    }

    //获取普通地图层
    public List<TileLayer> getLayerList() {
        if (map == null){
            return null;
        }
        List<TileLayer> list = new ArrayList<>();
        for (MapLayer layer: map.getLayers()) {
            if (layer.getName().startsWith("map_") && layer instanceof TileLayer) {
                list.add((TileLayer) layer);
            }
        }
        return list;
    }

    //获取入口层
    public ObjectGroup getEntryLayer() {
        if(map == null) {
            return null;
        }
        for (MapLayer layer : map.getLayers()) {
            if ("entry".equals(layer.getName()) && layer instanceof ObjectGroup) {
                return (ObjectGroup)layer;
            }
        }
        return null;
    }

    //获取用户指定的图层
    public MapLayer getUserLayer(String name) {
        if (name == null || map == null) {
            return null;
        }
        for (MapLayer layer : map.getLayers()) {
            if (name.equals(layer.getName()) && layer instanceof ObjectGroup) {
                return (ObjectGroup)layer;
            }
        }
        return null;
    }

    //获取用户指定图层列表
    public List<MapLayer> getUserLayerList(String prefix) {
        List<MapLayer> list = new ArrayList<>();
        if (prefix == null || map == null) {
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
