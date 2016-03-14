package me.zacharyjia.naruto;

/**
 * Created by jia19 on 2016/3/14.
 */
public class Config {

    private static Config instance = new Config();

    private int mapWidth = 32;
    private int mapHeight = 24;
    private int tileSize = 32;

    public static Config getInstance() {
        return instance;
    }

    private Config(){

    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getTileSize() {
        return tileSize;
    }


}
