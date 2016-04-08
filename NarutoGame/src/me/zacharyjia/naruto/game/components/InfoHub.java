package me.zacharyjia.naruto.game.components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 * Created by jia19 on 2016/4/8.
 */
public class InfoHub extends Label {

    private String name;
    private int life;
    private int fullLife;

    private boolean hasChakra = false;
    private int chakra;
    private int fullChakra;

    public InfoHub() {
        Paint paint = Color.rgb(102, 153, 255, 0.8);
        this.setBackground(new Background(new BackgroundFill(paint, null, null)));
        this.setTextFill(Color.WHITE);
        this.setFont(new Font(18));
        this.setLayoutX(0);
        this.setLayoutY(0);
        this.setMinSize(200, 100);
        this.setPadding(new Insets(20, 20, 20, 20));
    }

    public void updateDisplay() {
        StringBuilder builder = new StringBuilder();
        builder.append(name)
                .append("\n");
        builder.append("生命: ")
                .append(life)
                .append('/')
                .append(fullLife);
        if (hasChakra) {
            builder.append('\n')
                    .append("查克拉：")
                    .append(chakra)
                    .append('/')
                    .append(fullChakra);
        }
        this.setText(builder.toString());
    }

    public void updateDisplay(int life, int chakra) {
        this.life = life;
        this.chakra = chakra;
        updateDisplay();
    }

    public int getFullChakra() {
        return fullChakra;
    }

    public void setFullChakra(int fullChakra) {
        this.fullChakra = fullChakra;
        updateDisplay();
    }

    public int getChakra() {
        return chakra;
    }

    public void setChakra(int chakra) {
        this.chakra = chakra;
        updateDisplay();
    }

    public int getFullLife() {
        return fullLife;
    }

    public void setFullLife(int fullLife) {
        this.fullLife = fullLife;
        updateDisplay();
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
        updateDisplay();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        updateDisplay();
    }

    public boolean hasChakra() {
        return hasChakra;
    }

    public void setHasChakra(boolean hasChakra) {
        this.hasChakra = hasChakra;
        updateDisplay();
    }
}
