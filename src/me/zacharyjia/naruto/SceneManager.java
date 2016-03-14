package me.zacharyjia.naruto;

/**
 * Created by jia19 on 2016/3/14.
 */
public class SceneManager {

    private SceneManager instance = new SceneManager();

    private SceneManager(){

    }

    public SceneManager getInstance() {
        return instance;
    }

    public void load(String name) {

    }
}
