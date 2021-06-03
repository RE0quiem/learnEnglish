package com.learn.utils;

import java.io.File;
import java.io.FileWriter;
import java.util.Objects;

/**
 * @author zhanjingzhi-wb
 * @version 1.0
 * @ClassName WriteFile
 * @description
 * @date 2021/6/3 11:51
 * @since JDK 1.8
 */
public class WriteFile {

    public void writeFile(String path, String jsonStr) {
        if (path == null) {
            path = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath()+ "errorWordsRecord.txt";
        }
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
