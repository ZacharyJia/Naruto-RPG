package me.zacharyjia.naruto.core.event.Interface;

import javafx.scene.input.MouseEvent;

/**
 * Created by jia19 on 2016/4/19.
 */
public interface MouseEventSubject {
    void setOnMouseClickListener(OnMouseClickListener onMouseClickListener);

    void onMouseClick(MouseEvent event);

    void setOnMouseDownListener(OnMouseDownListener onMouseDownListener);

    void onMouseDown(MouseEvent event);

    void setOnMouseMoveListener(OnMouseMoveListener onMouseMoveListener);

    void onMouseMove(MouseEvent event);

    void setOnMouseUpListener(OnMouseUpListener onMouseUpListener);

    void onMouseUp(MouseEvent event);

}
