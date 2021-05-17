package com.learn.stateMachine;

import com.learn.algorithm.Algorithm;
import com.learn.algorithm.DefaultAlgorithm;
import com.learn.domain.Words;
import com.learn.domain.WordsWrapper;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zjz
 * @version 1.0
 * @ClassName StateMachineManager
 * @description
 * @date 2021/5/16 下午6:12
 * @since JDK 1.8
 */
public class StateMachineManager {
    private StateMachine stateMachine;
    private List<WordsWrapper> startWordsWrapperList;
    private Algorithm algorithm;

    public StateMachineManager(List<Words> words) {
        List<WordsWrapper> wrapperList = words.stream().map(each -> {
            WordsWrapper wrapper = new WordsWrapper();
            wrapper.setErrorTimes(1);
            wrapper.setWords(each);
            return wrapper;
        }).collect(Collectors.toList());
        this.startWordsWrapperList =wrapperList;

        // setting algorithm todo here default
        this.algorithm=new DefaultAlgorithm();
    }


    public void startPractice() {
        this.stateMachine=new StateMachine(this.algorithm);

        runByTurn(startWordsWrapperList,1);
    }

    private void runByTurn(List<WordsWrapper> turnList,int turn) {
        ArrayList<WordsWrapper> nextTurnList = new ArrayList<>();
        WordsWrapper thisTimeWordsResult;
        for (WordsWrapper wordsWrapper : this.algorithm.shufflePracticeWordsList(turnList)) {
            thisTimeWordsResult = stateMachine.run(wordsWrapper, turn);
            if (thisTimeWordsResult.getErrorTimes() > 0) {
                nextTurnList.add(wordsWrapper);
            }
        }

        if (CollectionUtils.isNotEmpty(nextTurnList)) {
            runByTurn(nextTurnList,turn+1);
        }
    }
}
