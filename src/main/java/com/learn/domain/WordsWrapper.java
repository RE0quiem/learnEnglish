package com.learn.domain;

import com.learn.domain.WordsFunction.WordsFunction;

/**
 * @author zjz
 * @version 1.0
 * @ClassName WordsWrapper
 * @description
 * @date 2021/5/16 下午2:31
 * @since JDK 1.8
 */
public class WordsWrapper extends WordsFunction {
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

    public String toStringOnSuccess() {
        String template =
                "\n" +
                "|'~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~'|\n" +
                "||                                        words:%s  means:%s \n" +
                ".'~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~'.\n" +
                "\n";
        return String.format(template, words.getWords(), words.getMeans());
    }

    public static WordsWrapperBuilder builder() {
        return new WordsWrapperBuilder();
    }

    public static class WordsWrapperBuilder {
        private int errorTimes;
        private Words words;


        public WordsWrapperBuilder errorTimes(int errorTimes) {
            this.errorTimes = errorTimes;
            return this;
        }

        public WordsWrapperBuilder words(Words words) {
            this.words = words;
            return this;
        }

        public WordsWrapper build() {
            WordsWrapper wordsWrapper = new WordsWrapper();
            wordsWrapper.setErrorTimes(this.errorTimes);
            wordsWrapper.setWords(this.words);
            return wordsWrapper;
        }
    }

}


