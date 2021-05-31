package com.learn.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
        URL resource = getClass().getClassLoader().getResource("properties.properties");
        Properties properties = new Properties();
        assert resource != null;
        File file = new File(resource.getFile());
        if (file.exists()) {
            InputStream inStream = null;
            try {
                inStream = new FileInputStream(file);
                properties.load(inStream);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (inStream != null) {
                    try {
                        inStream.close();//关闭流
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return properties;
    }
}
