package com.learn.utils;

import cn.hutool.core.io.resource.ClassPathResource;
import org.eclipse.core.runtime.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author zhanjingzhi-wb
 * @version 1.0
 * @ClassName ParseProperties
 * @description
 * @date 2021/5/28 14:45
 * @since JDK 1.8
 */
public class ParseProperties {
    public Properties parseProperties() {
        InputStream inStream = this.getClass().getResourceAsStream("/properties.properties");
        Properties properties = new Properties();
        if (inStream!=null) {
            try {
                properties.load(inStream);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    inStream.close();//关闭流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }
}
