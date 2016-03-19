package me.zacharyjia.naruto;

/**
 * Created by jia19 on 2016/3/14.
 */
public class Config {

    private static Config instance = new Config();

    private int mapWidth = 32;
    private int mapHeight = 24;
    private int tileSize = 32;
    private int windowWidth = 1024;
    private int windowHeight = 768;

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

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }
}
