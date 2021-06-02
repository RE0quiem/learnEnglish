package com.learn.plugin;

import com.learn.utils.pluginType.AfterType;
import com.learn.utils.pluginType.BeforeType;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MemberUsageScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.MethodParameterNamesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.util.Arrays;
import java.util.List;
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
    private PluginManager instence = new PluginManager();

    private Map<String,Plugin> beforePlugins=new TreeMap<>();

    private Map<String,Plugin> afterPlugins=new TreeMap<>();

    public PluginManager getInstence() {
        return instence;
    }


    public PluginManager() throws Exception{
        // 初始化加载插件
        Reflections reflections = new Reflections("com.learn.plugin.*", Arrays.asList(
                new SubTypesScanner(false)
                , new MethodParameterNamesScanner()
                , new MethodAnnotationsScanner()
                , new MemberUsageScanner()
                , new TypeAnnotationsScanner()
                , new FieldAnnotationsScanner()
        ));
        Set<Class<?>> afterTypeClazzs = reflections.getTypesAnnotatedWith(AfterType.class);
        Set<Class<?>> beforeTypeClazzs = reflections.getTypesAnnotatedWith(BeforeType.class);

        for (Class<?> clazz : beforeTypeClazzs) {
            beforePlugins.put(clazz.getName(),(Plugin) clazz.newInstance());
        }

        for (Class<?> clazz : afterTypeClazzs) {
            afterPlugins.put(clazz.getName(),(Plugin)clazz.newInstance());
        }

    }
}
