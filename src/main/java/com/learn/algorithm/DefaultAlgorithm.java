package com.learn.algorithm;

import com.learn.domain.WordsWrapper;

/**
 * @author zjz
 * @version 1.0
 * @ClassName DefaultAlgorithm
 * @description
 * @date 2021/5/16 下午6:35
 * @since JDK 1.8
 */
public class DefaultAlgorithm extends AbstractAlgorithm{
    @Override
    public void onError(WordsWrapper wordsWrapper) {
        wordsWrapper.setErrorTimes(wordsWrapper.getErrorTimes()+1);
    }

    @Override
    public void onSuccess(WordsWrapper wordsWrapper) {
        wordsWrapper.setErrorTimes(wordsWrapper.getErrorTimes()-1);
    }
}
