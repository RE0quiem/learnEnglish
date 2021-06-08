package com.learn.plugin;

import com.learn.plugin.pluginType.AfterType;
import com.learn.plugin.pluginType.BeforeType;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MemberUsageScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.MethodParameterNamesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author zhanjingzhi-wb
 * @version 1.0
 * @ClassName PluginManager
 * @description
 * @date 2021/6/2 17:10
 * @since JDK 1.8
 */
public class PluginManager {
    private static PluginManager instence = new PluginManager();

    private Map<String,Plugin> beforePlugins=new TreeMap<>();

    private Map<String,Plugin> afterPlugins=new TreeMap<>();

    private Map<String,? super CustomPlugin> customPluginMap=new TreeMap<>();

    public static PluginManager getInstence() {
        return instence;
    }


    public PluginManager(){
        // 初始化加载插件
        Reflections reflections = new Reflections("com.learn.*", Arrays.asList(
                new SubTypesScanner(false)
                , new MethodParameterNamesScanner()
                , new MethodAnnotationsScanner()
                , new MemberUsageScanner()
                , new TypeAnnotationsScanner()
                , new FieldAnnotationsScanner()
        ));
        Set<Class<?>> afterTypeClazzs = reflections.getTypesAnnotatedWith(AfterType.class);
        Set<Class<?>> beforeTypeClazzs = reflections.getTypesAnnotatedWith(BeforeType.class);
        Set<Class<? extends CustomPlugin>> customPlugins = reflections.getSubTypesOf(CustomPlugin.class);


        try {
            for (Class<?> clazz : beforeTypeClazzs) {
                beforePlugins.put(clazz.getName(),(Plugin) clazz.newInstance());
            }

            for (Class<?> clazz : afterTypeClazzs) {
                afterPlugins.put(clazz.getName(),(Plugin)clazz.newInstance());
            }

            for (Class<? extends CustomPlugin> customPluginClazz : customPlugins) {
                customPluginMap.put(customPluginClazz.getName(),customPluginClazz.newInstance());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Map<String, Plugin> getBeforePlugins() {
        return beforePlugins;
    }

    public Map<String, Plugin> getAfterPlugins() {
        return afterPlugins;
    }

    public Map<String, ? super CustomPlugin> getCustomPlugins() {
        return customPluginMap;
    }
}
