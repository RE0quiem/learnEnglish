package com.learn.algorithm;

import com.learn.domain.WordsWrapper;
import com.learn.utils.ConsoleDisplayUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhanjingzhi-wb
 * @version 1.0
 * @ClassName AbstractAlgorithm
 * @description
 * @date 2021/6/3 15:02
 * @since JDK 1.8
 */
public abstract class AbstractAlgorithm implements Algorithm {
    public void handleSuccess(WordsWrapper wordsWrapper) {
        onSuccess(wordsWrapper);
        ConsoleDisplayUtils.wordsSuccessDisplay(wordsWrapper);
    }

    public void handleError(WordsWrapper wordsWrapper) {
        onError(wordsWrapper);
        ConsoleDisplayUtils.wordsErrorDisplay(wordsWrapper);
    }

    public List<WordsWrapper> shufflePracticeWordsList(List<WordsWrapper> list) {
        return list.stream().sorted((words1, words2) -> {
            WordsWrapperCompare compare = new WordsWrapperCompare();
            return compare.compare(words1, words2);
        }).collect(Collectors.toList());
    }

    public static class WordsWrapperCompare implements Comparator<WordsWrapper> {
        @Override
        public int compare(WordsWrapper words1, WordsWrapper words2) {
            if (words1.getErrorTimes() - words2.getErrorTimes() > 0) {
                return -1;
            } else if (words1.getErrorTimes() - words2.getErrorTimes() == 0) {
                return words1.getWords().getWords().compareToIgnoreCase(words2.getWords().getWords());
            } else {
                return 1;
            }
        }
    }
}
