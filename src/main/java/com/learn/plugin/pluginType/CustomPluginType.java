package com.learn.plugin.pluginType;

import com.learn.plugin.CustomPlugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomPluginType {
    Class<? extends CustomPlugin>[] plugins();
}
