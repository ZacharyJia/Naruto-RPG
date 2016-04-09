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

    private MonsterPool() {
        try {
            InputStream is = ResourcesLoader.getInputStream("/monsterList.properties");
            Properties properties = new Properties();
            properties.load(is);
            Set<String> names = properties.stringPropertyNames();
            Iterator<String> it = names.iterator();
            while (it.hasNext()) {
                String name = it.next();
                String imgName = properties.getProperty(name);
                if (imgName != null) {
                    Monster monster = new Monster(imgName);
                    monster.setName(name);
                    monsters.add(monster);
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
        int index = random.nextInt(monsters.size());
        Monster monster = monsters.get(index);
        int life = random.nextInt(maxLife - minLife) + minLife;
        monster.setFullLife(life);
        monster.setLife(life);
        int attackValue = random.nextInt(15) + 5;
        monster.setAttackValue(attackValue);
        monster.setAlive(true);
        return monster;
    }
}
