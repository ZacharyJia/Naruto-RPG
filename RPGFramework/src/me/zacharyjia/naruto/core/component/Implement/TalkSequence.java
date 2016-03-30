package me.zacharyjia.naruto.core.component.Implement;

import me.zacharyjia.naruto.core.component.Interface.IShowable;
import me.zacharyjia.naruto.core.event.Interface.OnMouseClickListener;
import me.zacharyjia.naruto.core.scene.NScene;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by jia19 on 2016/3/26.
 */
public class TalkSequence {
    private static TalkSequence instance = new TalkSequence();

    private TalkSequence(){}
    private ArrayList<String> talkList;

    public void setTalkList(ArrayList<String> list) {
        this.talkList = list;
    }

    public static TalkSequence getInstance() {
        if (instance != null) {
            return instance;
        }
        return null;
    }

    public void start(NScene scene) {
        OnMouseClickListener listener = scene.getOnMouseClickListener();

        ArrayList<IShowable> showables = scene.getChildren();

        Iterator<String> it = talkList.iterator();
        if (talkList.size() == 0) {
            return;
        }
        if (it.hasNext()) {
            TalkBox.getInstance().hide();
            TalkBox.getInstance().show(it.next());
        }
        scene.pauseEvent();
        for (IShowable showable : showables) {
            showable.pauseAnimation();
        }

        scene.setOnMouseClickListener(event -> {
            if (it.hasNext()) {
                TalkBox.getInstance().hide();
                TalkBox.getInstance().show(it.next());
            }
            else {
                scene.setOnMouseClickListener(listener);
                scene.resumeEvent();
                for (IShowable showable : showables) {
                    showable.resumeAnimation();
                }

                TalkBox.getInstance().hide();
            }
        });
    }


}
