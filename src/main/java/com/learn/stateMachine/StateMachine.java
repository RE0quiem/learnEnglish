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
    private String practiceBoxTop =".================================================================.\n" +
            "||                            words:%s\n" +
            "||             phoneticSymbol:%s  practiceTimes:%s\n" +
            "|'--------------------------------------------------------------'|\n" +
            "||           what means?                  eg(y|n|:q)            ||\n" +
            ".'=============================================================='.";

    private String nextStepStrTemplate="\t\t\t\t\t↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓";

    public StateMachine(Algorithm algorithm) {
        this.algorithm = algorithm;
    }


    public WordsWrapper run(WordsWrapper wordsWrapper, int turn) {
        // display the practice words
        String practiceResult = displayThePracticeWords(wordsWrapper);
        // input Y | input N
        if ("y".equals(practiceResult)) {
            algorithm.onSuccess(wordsWrapper);
        } else {
            algorithm.onError(wordsWrapper);
        }
        // complete
        System.out.println(nextStepStrTemplate+"\n");
        return wordsWrapper;
    }

    private String displayThePracticeWords(WordsWrapper wordsWrapper) {
        System.out.println(String.format(practiceBoxTop,wordsWrapper.getWords().getWords(), wordsWrapper.getWords().getPhoneticSymbol(), wordsWrapper.getErrorTimes()));
        return CommonLineScan.getInputDateRange((inputStr)-> !CommonLineScan.enumStrs.contains(inputStr));
    }
}
