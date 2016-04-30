package me.zacharyjia.naruto.game.Model;

import me.zacharyjia.naruto.core.utils.ResourcesLoader;
import me.zacharyjia.naruto.game.Model.Impl.Monster;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by jia19 on 2016/3/28.
 */
public class MonsterPool {

    private Random random = new Random(System.currentTimeMillis());
    private static MonsterPool instance;

    private ArrayList<Monster> monsters = new ArrayList<>();
    private Map<Monster, Boolean> used = new HashMap<>();


    private MonsterPool() {
        try {
            InputStream is = ResourcesLoader.getInputStream("/monsterList.properties");
            Properties properties = new Properties();
            properties.load(is);
            Set<String> names = properties.stringPropertyNames();
            for (String name : names) {
                String imgName = properties.getProperty(name);
                if (imgName != null) {
                    Monster monster = new Monster(imgName);
                    monster.setName(name);
                    monsters.add(monster);
                    used.put(monster, false);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MonsterPool getInstance() {
        if (instance == null) {
            synchronized (MonsterPool.class) {
                if (instance == null) {
                    instance = new MonsterPool();
                }
            }
        }
        return instance;
    }

    public Monster getRandomMonster(int minLife, int maxLife) {
        if (monsters.size() == 0) {
            return null;
        }
        int index;
        Monster monster;
        while(true) {
            index = random.nextInt(monsters.size());
            monster = monsters.get(index);
            if (!used.get(monster))
            {
                break;
            }
        }

        int life = random.nextInt(maxLife - minLife) + minLife;
        monster.setFullLife(life);
        monster.setLife(life);
        int attackValue = random.nextInt(15) + 5;
        monster.setAttackValue(attackValue);
        monster.setAlive(true);
        monster.setSoundFile("/res/sound/attack.wav");
        return monster;
    }

    public void releaseMonster(Monster monster)
    {
        used.put(monster, false);
    }
}
