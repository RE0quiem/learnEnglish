package com.learn.plugin;

/**
 * @author zhanjingzhi-wb
 * @version 1.0
 * @ClassName PlayMp3
 * @description
 * @date 2021/6/8 15:24
 * @since JDK 1.8
 */
public class PlayMp3 extends CustomPlugin {

    public PlayMp3() {
        runningTime=enumRunningTime[0];
    }

    @Override
    public Object apply(Object o) {
        System.out.println("play mp3!");
        return null;
    }
}
