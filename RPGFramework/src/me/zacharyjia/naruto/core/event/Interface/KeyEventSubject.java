package me.zacharyjia.naruto.core.event.Interface;

import javafx.scene.input.KeyEvent;

/**
 * Created by jia19 on 2016/4/19.
 */
public interface KeyEventSubject {

    void setOnKeyUpListener(OnKeyUpListener onKeyUpListener);

    void onKeyUp(KeyEvent event);

    void setOnKeyDownListener(OnKeyDownListener onKeyDownListener);

    void onKeyDown(KeyEvent event);
}
