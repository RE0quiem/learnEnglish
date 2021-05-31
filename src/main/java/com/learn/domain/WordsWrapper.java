package com.learn.domain;

/**
 * @author zjz
 * @version 1.0
 * @ClassName WordsWrapper
 * @description
 * @date 2021/5/16 下午2:31
 * @since JDK 1.8
 */
public class WordsWrapper {
    private int errorTimes;
    private Words words;

    public int getErrorTimes() {
        return errorTimes;
    }

    public void setErrorTimes(int errorTimes) {
        this.errorTimes = errorTimes;
    }

    public Words getWords() {
        return words;
    }

    public void setWords(Words words) {
        this.words = words;
    }
}
