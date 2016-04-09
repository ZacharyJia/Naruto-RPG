package me.zacharyjia.naruto.game.Scene;

import me.zacharyjia.naruto.core.component.Interface.IMap;
import me.zacharyjia.naruto.core.scene.NScene;
import me.zacharyjia.naruto.core.utils.MapReader;

/**
 * Created by jia19 on 2016/4/9.
 */
public class HotelScene extends NScene {

    @Override
    public void init() {

        IMap map = MapReader.readMap("/res/map/hotel.tmx");
        setMap(map);

    }
}
