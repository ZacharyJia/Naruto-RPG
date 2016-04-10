package me.zacharyjia.naruto;

import me.zacharyjia.naruto.core.Exception.ResourcesNotFoundException;
import me.zacharyjia.naruto.core.Intent;
import me.zacharyjia.naruto.core.utils.ResourcesLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by jia19 on 2016/3/14.
 */
public class Config {

    private static Config instance = null;

    private String gameName;
    private int mapWidth;
    private int mapHeight;
    private int tileSize;
    private int windowWidth;
    private int windowHeight;
    private boolean isFirst = true;

    private String startScene = "me.zacharyjia.naruto.game.StartScene";

    //单例实现
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

        Properties properties = new Properties();
        try {
            //读取配置信息
            InputStream is = ResourcesLoader.getInputStream("/config.properties");
            properties.load(is);
            mapWidth = Integer.parseInt(properties.getProperty("mapWidth"));
            mapHeight = Integer.parseInt(properties.getProperty("mapHeight"));
            tileSize = Integer.parseInt(properties.getProperty("tileSize"));
            windowWidth = Integer.parseInt(properties.getProperty("windowWidth"));
            windowHeight = Integer.parseInt(properties.getProperty("windowHeight"));
            startScene = properties.getProperty("startScene");//启动场景
            gameName = properties.getProperty("gameName");
        } catch (ResourcesNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public String getGameName() {
        return gameName;
    }
}
