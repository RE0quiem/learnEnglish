package com.learn.plugin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.algorithm.AbstractAlgorithm;
import com.learn.domain.Words;
import com.learn.domain.WordsWrapper;
import com.learn.utils.ParseProperties;
import com.learn.plugin.pluginType.BeforeType;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhanjingzhi-wb
 * @version 1.0
 * @ClassName EchoLastPracticeErrorWordsIntoThisTime
 * @description
 * @date 2021/6/2 17:05
 * @since JDK 1.8
 */
// todo 后面再加加载顺序功能
@BeforeType
@SuppressWarnings("unchecked")
public class EchoLastPracticeErrorWordsIntoThisTime implements Plugin {
    @Override
    public Object apply(Object o) {
        Object[] params = (Object[]) o;
        List<WordsWrapper> startWordsWrapperList = (List<WordsWrapper>) params[0];
        List<Words> allWordsInExcel = (List<Words>) params[1];

        ObjectMapper objectMapper = new ObjectMapper();
        String path = new ParseProperties().parseProperties().getProperty("errorWordsSaveFilePath");
        File file = new File(path);
        if(!file.exists()){
            return startWordsWrapperList;
        }
        List<Words> lastErrorWords = null;

        try {
            // todo  com.fasterxml.jackson.databind.JsonMappingException: No content to map due to end-of-input
            lastErrorWords = objectMapper.readValue(file, new TypeReference<List<Words>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Stream.concat(lastErrorWords
                        .stream()
                        .map(words -> WordsWrapper.builder().words(words).errorTimes(3).build()),
                startWordsWrapperList.stream())
                .sorted((word1, word2) -> {
                    AbstractAlgorithm.WordsWrapperCompare wrapperCompare = new AbstractAlgorithm.WordsWrapperCompare();
                    return wrapperCompare.compare(word1, word2);
                })
                .collect(Collectors.toList());
    }
}
