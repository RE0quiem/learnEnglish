package com.learn.plugin;

import com.learn.plugin.pluginOrder.PluginOrder;

/**
 * @author zhanjingzhi-wb
 * @version 1.0
 * @ClassName CustomPlugin
 * @description custom cut point Plugins
 * @date 2021/6/8 15:08
 * @since JDK 1.8
 */
public abstract class CustomPlugin implements Plugin, PluginOrder {
    final protected static String[] enumRunningTime = new String[]{"before", "after"};
    protected String runningTime;


    public String getRunningTime() {
        return runningTime;
    }

}
