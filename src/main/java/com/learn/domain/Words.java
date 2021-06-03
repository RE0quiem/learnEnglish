package com.learn.domain;

import java.util.List;

/**
 * @author zjz
 * @version 1.0
 * @ClassName Words
 * @description
 * @date 2021/5/16 下午2:25
 * @since JDK 1.8
 */
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
        String template =
                "\n" +
                "|'~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~'|\n" +
                "||    words:%s  phoneticSymbol:%s  characteristic:%s  means:%s  phrase:%s  exampleSentence:%s \n" +
                ".'~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~'.\n" +
                "\n";
        return String.format(template, words, phoneticSymbol, characteristic, means, phrase, exampleSentence);
    }

    public String getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(String buildDate) {
        this.buildDate = buildDate;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getPhoneticSymbol() {
        return phoneticSymbol;
    }

    public void setPhoneticSymbol(String phoneticSymbol) {
        this.phoneticSymbol = phoneticSymbol;
    }

    public List<String> getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(List<String> characteristic) {
        this.characteristic = characteristic;
    }

    public List<String> getMeans() {
        return means;
    }

    public void setMeans(List<String> means) {
        this.means = means;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getExampleSentence() {
        return exampleSentence;
    }

    public void setExampleSentence(String exampleSentence) {
        this.exampleSentence = exampleSentence;
    }
}
