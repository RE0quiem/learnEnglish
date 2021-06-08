package com.learn.plugin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.domain.WordsWrapper;
import com.learn.utils.ParseProperties;
import com.learn.utils.WriteFile;
import com.learn.plugin.pluginType.AfterType;

import java.util.List;

/**
 * @author zhanjingzhi-wb
 * @version 1.0
 * @ClassName CollectErrorWordsIntoFile
 * @description
 * @date 2021/6/2 17:04
 * @since JDK 1.8
 */
@AfterType
@SuppressWarnings("unchecked")
// todo 后面再加加载顺序功能
public class CollectErrorWordsIntoFile implements Plugin {

    @Override
    public Object apply(Object o) {
        List<WordsWrapper> errorWords = (List<WordsWrapper>) o;
        ObjectMapper objectMapper = new ObjectMapper();
        ParseProperties parseProperties = new ParseProperties();
        String errorWordsSaveFilePath = parseProperties.parseProperties().getProperty("errorWordsSaveFilePath");
        try {
            String jsonStr = objectMapper.writeValueAsString(errorWords);
            WriteFile writeFile = new WriteFile();
            writeFile.writeFile(errorWordsSaveFilePath, jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
