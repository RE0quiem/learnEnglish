package com.learn.algorithm;

import com.learn.domain.WordsWrapper;

import java.util.List;

public interface Algorithm {
    void onError(WordsWrapper wordsWrapper);

    void onSuccess(WordsWrapper wordsWrapper);

    List<WordsWrapper> shufflePracticeWordsList(List<WordsWrapper> list);
}
