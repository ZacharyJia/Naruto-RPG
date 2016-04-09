package me.zacharyjia.naruto.game.utils;

import me.zacharyjia.naruto.core.component.Implement.NPC;
import me.zacharyjia.naruto.game.Model.NPCFactory;
import me.zacharyjia.naruto.core.component.Interface.Direction;
import me.zacharyjia.naruto.core.utils.NPCAction;
import tiled.core.MapObject;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

/**
 * NPC加载工具
 * 利用解释器模式，解析在地图中定义的NPC的运动
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
        NPC npc = factory.createSprite(null, imgFile);
        int x = Integer.valueOf(properties.getProperty("X"));
        int y = Integer.valueOf(properties.getProperty("Y"));
        npc.setPosition(x, y);

        ArrayList<NPCAction> actionList = new ArrayList<>();

        String action = properties.getProperty("action");
        if (action != null) {
            //解析运动模式
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
