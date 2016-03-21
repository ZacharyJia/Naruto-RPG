package me.zacharyjia.naruto.core;

import me.zacharyjia.naruto.core.scene.NScene;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jia19 on 2016/3/21.
 */
public class Intent {

    private Class target;
    private Map<String, Object> extras = new HashMap<>();

    public Intent(Class target) {
        this.target = target;
    }

    public void putExtra(String key, Object value) {
        if (key != null && value != null) {
            this.extras.put(key, value);
        }
        else {
            return;
        }
    }

    public Object getExtra(String key, Object defaultValue) {
        Object obj = extras.get(key);
        if (obj != null) {
            return obj;
        }
        else {
            return defaultValue;
        }
    }

    public Class getTarget() {
        return target;
    }
}
