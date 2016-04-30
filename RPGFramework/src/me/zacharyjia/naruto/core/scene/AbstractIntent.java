package me.zacharyjia.naruto.core.scene;

/**
 * Created by jia19 on 2016/4/23.
 */
public interface AbstractIntent {

    void putExtra(String key, Object value) ;
    /**
     * 从Intent中获取附加数据
     * @param key
     * @param defaultValue
     * @return
     */
    Object getExtra(String key, Object defaultValue);

    /**
     * 获取要启动的场景的类
     * @return
     */
    Class getTarget();

}
