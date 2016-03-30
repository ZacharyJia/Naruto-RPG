package me.zacharyjia.naruto.core.utils;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import me.zacharyjia.naruto.core.Intent;
import me.zacharyjia.naruto.core.component.Implement.NPC;
import me.zacharyjia.naruto.core.component.Implement.NPCFactory;
import me.zacharyjia.naruto.core.component.Interface.Direction;
import tiled.core.MapObject;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by jia19 on 2016/3/30.
 */
public class NPCLoader {

    public static NPC loadFromObject(MapObject mapObject) {
        if (mapObject == null) {
            return null;
        }

        Properties properties = mapObject.getProperties();
        String imgFile = properties.getProperty("imgFile");
        NPCFactory factory = new NPCFactory();
        NPC npc = factory.createSprite(imgFile);
        int x = Integer.valueOf(properties.getProperty("X"));
        int y = Integer.valueOf(properties.getProperty("Y"));
        npc.setPosition(x, y);

        ArrayList<NPCAction> actionList = new ArrayList<>();

        String action = properties.getProperty("action");
        if (action != null) {
            String[] actions = action.split(";");
            for (String s : actions) {
                Scanner scanner = new Scanner(s);
                String operator = scanner.next();
                int value = 0;
                if (scanner.hasNextInt()) {
                     value = scanner.nextInt();
                }
                switch (operator) {
                    case "up":
                        actionList.add(new NPCAction(Direction.UP, value));
                        break;
                    case "down":
                        actionList.add(new NPCAction(Direction.DOWN, value));
                        break;
                    case "left":
                        actionList.add(new NPCAction(Direction.LEFT, value));
                        break;
                    case "right":
                        actionList.add(new NPCAction(Direction.RIGHT, value));
                        break;
                }
            }
        }

        npc.setActions(actionList);

        return npc;
    }
}
