package com.learn.plugin;

import com.learn.plugin.pluginType.CustomPluginType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhanjingzhi-wb
 * @version 1.0
 * @ClassName CustomPluginAdvice
 * @description
 * @date 2021/6/8 16:10
 * @since JDK 1.8
 */
@Aspect
@SuppressWarnings("unchecked")
public class CustomPluginAdvice {

    // todo 特别重要:这里aspectJ有个bug. 目前我试过了最新的aspectJ依赖版本(和ajc编译器版本不一致但是可以用),
    // 然后如果不加上execution(* *.*(..)) &&,那么在路径target/classes/com/learn/stateMachine里面会生成两
    // 个代理入口类.一个是StateMachine$AjcClosure1.class,一个是StateMachineManager$AjcClosure1.class.按
    // 道理我代理的是StateMachine应该和StateMachineManager没有什么关系.后者生成的副作用就是在我执行切面的时候
    // 会执行重复的两次...断点进入代理类发现target对象中joinPoint属性两次调用不一致,原因未知.
    @Around(value = "execution(* *.*(..)) && !execution(* com.learn.algorithm.AbstractAlgorithm.handleError(*))&& @annotation(com.learn.plugin.pluginType.CustomPluginType)")
    public Object doCustomPlugin(ProceedingJoinPoint joinPoint) throws Throwable {
        Map<String, CustomPlugin> customPlugins = (Map<String, CustomPlugin>) joinPoint.getArgs()[2];
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getName();
        Class[] parameterTypes = signature.getParameterTypes();
        Class<?> clazz = joinPoint.getTarget().getClass();
        Method method = clazz.getMethod(methodName, parameterTypes);
        CustomPluginType customPluginType = method.getAnnotation(CustomPluginType.class);

        List<String> tagPluginNames = Arrays
                .stream(customPluginType.plugins())
                .map(Class::getName)
                .collect(Collectors.toList());

        Map<String, List<CustomPlugin>> customTypeList = customPlugins
                .entrySet()
                .stream()
                .filter(entry -> tagPluginNames
                        .stream()
                        .anyMatch(e -> e.equals(entry.getKey())))
                .map(Map.Entry::getValue)
                .collect(Collectors
                        .groupingBy(CustomPlugin::getRunningTime));
        if (customTypeList.get("before") != null) {
            for (CustomPlugin before : customTypeList.get("before")) {
                before.apply(joinPoint.getArgs());
            }
        }

        Object returnData = joinPoint.proceed();

        if (customTypeList.get("after") != null) {
            for (CustomPlugin after : customTypeList.get("after")) {
                after.apply(joinPoint.getArgs());
            }
        }

        return returnData;
    }
}
