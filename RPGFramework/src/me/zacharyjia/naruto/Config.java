package me.zacharyjia.naruto;

/**
 * Created by jia19 on 2016/3/14.
 */
public class Config {

    private static Config instance = null;

    private int mapWidth = 32;
    private int mapHeight = 24;
    private int tileSize = 32;
    private int windowWidth = 1024;
    private int windowHeight = 768;

    private String startScene = "me.zacharyjia.naruto.game.StartScene";

    public static Config getInstance() {
        if (instance == null) {
            synchronized (Config.class) {
                if (instance == null) {
                    instance = new Config();
                }
            }
        }
        return instance;
    }

    private Config(){

        //从配置文件加载启动场景
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

    public String getStartScene() {
        return startScene;
    }
}
