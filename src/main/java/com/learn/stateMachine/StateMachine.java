package com.learn.stateMachine;

import com.learn.algorithm.AbstractAlgorithm;
import com.learn.domain.WordsWrapper;
import com.learn.utils.CommonLineScan;
import com.learn.utils.ConsoleDisplayUtils;

/**
 * @author zjz
 * @version 1.0
 * @ClassName StatementMachin
 * @description 状态机 控制每一个word的运行流程
 * @date 2021/5/16 下午6:07
 * @since JDK 1.8
 */
public class StateMachine {
    private AbstractAlgorithm algorithm;
    public StateMachine(AbstractAlgorithm algorithm) {
        this.algorithm = algorithm;
    }


    public WordsWrapper run(WordsWrapper wordsWrapper, int turn) {
        // display the practice words
        String practiceResult = displayThePracticeWords(wordsWrapper);
        // input Y | input N
        if ("y".equals(practiceResult)) {
            algorithm.handleSuccess(wordsWrapper);
        } else {
            algorithm.handleError(wordsWrapper);
        }
        // complete
       ConsoleDisplayUtils.nextStep();
        return wordsWrapper;
    }

    private String displayThePracticeWords(WordsWrapper wordsWrapper) {
        ConsoleDisplayUtils.displayThePracticeWords(wordsWrapper);
        return CommonLineScan.getInputDateRange((inputStr)-> !CommonLineScan.enumStrs.contains(inputStr));
    }
}
