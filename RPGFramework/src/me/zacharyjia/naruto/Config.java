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

    private int mapWidth = 32;
    private int mapHeight = 24;
    private int tileSize = 32;
    private int windowWidth = 1024;
    private int windowHeight = 768;
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
            mapWidth = Integer.parseInt(properties.getProperty("mapWidth", "32"));
            mapHeight = Integer.parseInt(properties.getProperty("mapHeight", "24"));
            tileSize = Integer.parseInt(properties.getProperty("tileSize", "32"));
            windowWidth = Integer.parseInt(properties.getProperty("windowWidth", "1024"));
            windowHeight = Integer.parseInt(properties.getProperty("windowHeight", "768"));
            startScene = properties.getProperty("startScene");//启动场景
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
}
