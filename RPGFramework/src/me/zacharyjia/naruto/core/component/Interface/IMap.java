package me.zacharyjia.naruto.core.component.Interface;

import tiled.core.MapLayer;
import tiled.core.ObjectGroup;
import tiled.core.TileLayer;

import java.util.ArrayList;
import java.util.List;

/**
 * 地图接口
 *
 * Created by jia19 on 2016/3/28.
 */
public interface IMap {

    public TileLayer getMaskLayer();

    public List<TileLayer> getLayerList();

    public ObjectGroup getEntryLayer();

    public MapLayer getUserLayer(String name);

    public List<MapLayer> getUserLayerList(String prefix);
    
}
