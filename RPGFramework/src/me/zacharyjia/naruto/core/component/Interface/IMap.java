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

    TileLayer getMaskLayer();

    List<TileLayer> getLayerList();

    ObjectGroup getEntryLayer();

    MapLayer getUserLayer(String name);

    List<MapLayer> getUserLayerList(String prefix);

}
