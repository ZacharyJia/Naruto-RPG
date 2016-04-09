package me.zacharyjia.naruto.core.component.Interface;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

/**
 * 可显示内容接口，通过imageView实现
 * Created by jia19 on 2016/3/11.
 */
public interface IShowable {

    void show();
    void disappear();

    ImageView getImageView();

    //暂停动画
    public void pauseAnimation();

    //恢复动画
    public void resumeAnimation();


}
