package com.learn.algorithm;

import com.learn.domain.WordsWrapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zjz
 * @version 1.0
 * @ClassName DefaultAlgorithm
 * @description
 * @date 2021/5/16 下午6:35
 * @since JDK 1.8
 */
public class DefaultAlgorithm implements Algorithm{
    @Override
    public void onError(WordsWrapper wordsWrapper) {
        wordsWrapper.setErrorTimes(wordsWrapper.getErrorTimes()+1);
        // disPlay all infos of this words
        System.out.println(wordsWrapper.getWords());

    }

    @Override
    public void onSuccess(WordsWrapper wordsWrapper) {
        wordsWrapper.setErrorTimes(wordsWrapper.getErrorTimes()-1);
    }

    @Override
    public List<WordsWrapper> shufflePracticeWordsList(List<WordsWrapper> list) {
        return list.stream().sorted((words1,words2)->{
            if (words1.getErrorTimes() - words2.getErrorTimes() > 0) {
                return -1;
            } else if (words1.getErrorTimes() - words2.getErrorTimes() == 0) {
                return words1.getWords().getWords().compareToIgnoreCase(words2.getWords().getWords());
            } else {
                return 1;
            }
        }).collect(Collectors.toList());
    }

}
