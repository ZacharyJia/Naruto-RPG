package me.zacharyjia.naruto.core.utils;

import me.zacharyjia.naruto.core.component.Implement.TiledMapAdapter;
import tiled.core.Map;
import tiled.io.TMXMapReader;

/**
 * 地图读取器
 * 将TMX文件读取成为TiledMapAdapter类
 *
 * Created by jia19 on 2016/3/28.
 */
public class MapReader {

    public static TiledMapAdapter readMap(String name) {
        Map map = null;
        try {
            TMXMapReader reader = new TMXMapReader();
            map = reader.readMap(ResourcesLoader.getInputStream(name));
            //drawMap(gc, res.map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (map != null) {
            return new TiledMapAdapter(map);
        }
        else {
            return null;
        }
    }
}
