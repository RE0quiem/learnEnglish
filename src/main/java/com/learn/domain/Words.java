package com.learn.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zjz
 * @version 1.0
 * @ClassName Words
 * @description
 * @date 2021/5/16 下午2:25
 * @since JDK 1.8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Words {
    private String buildDate;
    private String words;
    private String phoneticSymbol;
    private List<String> characteristic;
    private List<String> means;
    private String phrase;
    private String exampleSentence;

    @Override
    public String toString() {
        String template = "-----------------------------------------------------------------\n" +
                "words:%s  phoneticSymbol:%s  characteristic:%s  means:%s  phrase:%s  exampleSentence:%s\n" +
                "-----------------------------------------------------------------";
        return String.format(template, words, phoneticSymbol, characteristic, means, phrase, exampleSentence);
    }
}
