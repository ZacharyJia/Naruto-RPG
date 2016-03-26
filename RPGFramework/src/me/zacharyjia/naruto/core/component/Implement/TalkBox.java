package me.zacharyjia.naruto.core.component.Implement;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import me.zacharyjia.naruto.Config;
import me.zacharyjia.naruto.core.Exception.PaneNullException;
import me.zacharyjia.naruto.core.event.Interface.OnMouseClickListener;

/**
 * Created by jia19 on 2016/3/21.
 */
public class TalkBox {

    private static TalkBox instance = null;

    private OnMouseClickListener listener;

    private String text = null; //Text to be show
    private Pane pane = null;
    private Label label = new Label();
    private Config config = Config.getInstance();

    private TalkBox(){
        Paint paint = Color.rgb(102, 153, 255, 0.8);
        label.setBackground(new Background(new BackgroundFill(paint, null, null)));
        label.setFont(new Font(24));
        label.setTextFill(Color.WHITE);
        label.setLayoutX(0);
        label.setLayoutY(config.getWindowHeight() / 3 * 2);
        label.setMinSize(config.getWindowWidth(), config.getWindowHeight() / 3);
        label.setPadding(new Insets(20, 20, 20, 20));
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public static TalkBox getInstance() {
        if (instance == null) {
            synchronized (TalkBox.class) {
                if (instance == null) {
                    instance = new TalkBox();
                }
            }
        }

        return instance;
    }


    public void show(String text) {
        if (pane != null) {
            label.setText(text);
            pane.getChildren().add(label);
        }
        else {
            throw new PaneNullException("Pane is null!");
        }
    }

    public void setOnMouseClickListener(OnMouseClickListener listener) {
        this.listener = listener;
        label.setOnMouseClicked(event -> {
            this.listener.onMouseClick(event);
        });
    }

    public void hide() {
        if (pane != null) {
            pane.getChildren().remove(label);
        }
        else {
            throw new PaneNullException("Pane is Null!");
        }
    }

}
