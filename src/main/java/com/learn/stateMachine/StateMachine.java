package com.learn.stateMachine;

import com.learn.algorithm.Algorithm;
import com.learn.domain.WordsWrapper;
import com.learn.utils.CommonLineScan;

/**
 * @author zjz
 * @version 1.0
 * @ClassName StatementMachin
 * @description 状态机 控制每一个word的运行流程
 * @date 2021/5/16 下午6:07
 * @since JDK 1.8
 */
public class StateMachine {
    private Algorithm algorithm;
    private WordsWrapper wordsWrapper;

    public StateMachine(Algorithm algorithm) {
        this.algorithm = algorithm;
    }


    public WordsWrapper run(WordsWrapper wordsWrapper, int turn) {
        System.out.println("=============================================================================================");
        // display the practice words
        String practiceResult = displayThePracticeWords(wordsWrapper);
        // input Y | input N
        if ("y".equals(practiceResult)) {
            algorithm.onSuccess(wordsWrapper);
        } else {
            algorithm.onError(wordsWrapper);
        }
        // complete
        System.out.println("=============================================================================================");
        System.out.println("\n");
        return wordsWrapper;
    }

    private String displayThePracticeWords(WordsWrapper wordsWrapper) {
        String displayStr = "words:%s  phoneticSymbol:%s  practiceTimes:%s";
        System.out.println(String.format(displayStr, wordsWrapper.getWords().getWords(), wordsWrapper.getWords().getPhoneticSymbol(), wordsWrapper.getErrorTimes()));
        System.out.println("what means? eg(y|n)");
        return CommonLineScan.getInputDateRange();
    }
}
