package me.zacharyjia.naruto.core.scene;

import me.zacharyjia.naruto.core.scene.AbstractIntent;

import java.util.HashMap;
import java.util.Map;

/**
 * Intent类，用于在启动窗体时，在窗体之间传递数据
 *
 * 2016年4月9日13:41:38
 * by Zachary
 */
public class Intent implements AbstractIntent {

    private Class target;
    private Map<String, Object> extras = new HashMap<>();

    public Intent(Class target) {
        this.target = target;
    }

    /**
     * 向Intent中添加附加数据
     * @param key
     * @param value
     */
    public void putExtra(String key, Object value) {
        if (key != null && value != null) {
            this.extras.put(key, value);
        }
    }

    /**
     * 从Intent中获取附加数据
     * @param key
     * @param defaultValue
     * @return
     */
    public Object getExtra(String key, Object defaultValue) {
        Object obj = extras.get(key);
        if (obj != null) {
            return obj;
        }
        else {
            return defaultValue;
        }
    }

    /**
     * 获取要启动的场景的类
     * @return
     */
    public Class getTarget() {
        return target;
    }
}
