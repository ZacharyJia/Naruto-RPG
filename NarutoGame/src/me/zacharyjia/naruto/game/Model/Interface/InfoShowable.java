package me.zacharyjia.naruto.game.Model.Interface;

import me.zacharyjia.naruto.game.components.InfoHub;

/**
 * Created by jia19 on 2016/4/8.
 */
public interface InfoShowable {
    void bindInfoHub(InfoHub infoHub);

    int getLife();
    int getChakra();
    int getFullLife();
    int getFullChakra();
    String getName();

    boolean hasChakra();
}
