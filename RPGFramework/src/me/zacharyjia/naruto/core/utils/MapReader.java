package me.zacharyjia.naruto.core.utils;

import me.zacharyjia.naruto.core.component.Implement.NMap;
import tiled.core.Map;
import tiled.io.TMXMapReader;

/**
 * Created by jia19 on 2016/3/28.
 */
public class MapReader {

    public static NMap readMap(String name) {
        Map map = null;
        try {
            TMXMapReader reader = new TMXMapReader();
            map = reader.readMap(ResourcesLoader.getInputStream(name));
            //drawMap(gc, res.map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (map != null) {
            return new NMap(map);
        }
        else {
            return null;
        }
    }
}
