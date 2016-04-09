package me.zacharyjia.naruto.core.component.Implement;

import me.zacharyjia.naruto.core.component.Interface.IShowable;
import me.zacharyjia.naruto.core.event.Interface.OnMouseClickListener;
import me.zacharyjia.naruto.core.event.Interface.OnTalkSequenceFinishListener;
import me.zacharyjia.naruto.core.scene.NScene;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 对话序列工具类
 *
 * 用于启动一系列的用户对话框，实现信息显示
 *
 * Created by jia19 on 2016/3/26.
 */
public class TalkSequence {
    private static TalkSequence instance;

    private OnTalkSequenceFinishListener onTalkSequenceFinishListener = null;
    private TalkSequence(){}
    private ArrayList<String> talkList;

    public TalkSequence setTalkList(ArrayList<String> list) {
        this.talkList = list;
        return this;
    }

    //单例实现
    public static TalkSequence getInstance() {
        if (instance == null) {
            synchronized (TalkSequence.class) {
                if (instance == null) {
                    instance = new TalkSequence();
                }
            }
        }
        return instance;
    }

    //开始显示对话框
    public void start(NScene scene) {
        //替换对应场景的鼠标点击事件
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

        //暂停场景事件，暂停所有动画
        scene.pauseEvent();
        for (IShowable showable : showables) {
            showable.pauseAnimation();
        }

        //替换鼠标事件为自己的点击事件
        scene.setOnMouseClickListener(event -> {
            if (it.hasNext()) {
                //如果还有没展示的，则继续展示聊天内容
                TalkBox.getInstance().hide();
                TalkBox.getInstance().show(it.next());
            }
            else {
                //如果已经显示完成所有的聊天内容，则恢复事件，回复动画
                scene.setOnMouseClickListener(listener);
                scene.resumeEvent();
                for (IShowable showable : showables) {
                    showable.resumeAnimation();
                }

                TalkBox.getInstance().hide();

                //调用聊天框完成回调事件
                if (onTalkSequenceFinishListener != null) {
                    onTalkSequenceFinishListener.onTalkSequenceFinish();
                }
                onTalkSequenceFinishListener = null;
            }
        });
    }

    //设置聊天完成回调
    public void setOnTalkSequenceFinishListener(OnTalkSequenceFinishListener onTalkSequenceFinishListener) {
        this.onTalkSequenceFinishListener = onTalkSequenceFinishListener;
    }


}
