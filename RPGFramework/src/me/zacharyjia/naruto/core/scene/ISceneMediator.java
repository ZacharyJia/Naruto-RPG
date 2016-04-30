package me.zacharyjia.naruto.core.scene;

/**
 * Created by jia19 on 2016/4/23.
 */
public interface ISceneMediator {

    void pushScene(AbstractIntent intent);

    void popScene(NScene caller);

    void switchScene(AbstractIntent intent);

}
