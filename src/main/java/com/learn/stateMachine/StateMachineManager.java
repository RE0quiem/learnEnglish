package com.learn.stateMachine;

import com.learn.algorithm.Algorithm;
import com.learn.algorithm.DefaultAlgorithm;
import com.learn.domain.Words;
import com.learn.domain.WordsWrapper;
import com.learn.utils.ParseProperties;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
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
    public static int recordWordErrorTimes;

    //StateMachine store error word that error times over special times by properties
    public static Set<WordsWrapper> errorTimesOverSpecialTimesList=new TreeSet<>(Comparator.comparing(e -> e.getWords().getWords()));

    public StateMachineManager(List<Words> words) {
        // parse properties.properties
        ParseProperties parseProperties = new ParseProperties();
        recordWordErrorTimes= Integer.parseInt(parseProperties.parseProperties().getProperty("recordWordErrorTimes"));

        this.startWordsWrapperList = words.stream().map(each -> {
            WordsWrapper wrapper = new WordsWrapper();
            wrapper.setErrorTimes(1);
            wrapper.setWords(each);
            return wrapper;
        }).collect(Collectors.toList());

        // setting algorithm todo here default
        this.algorithm = new DefaultAlgorithm();
    }


    public void startPractice() {
        this.stateMachine = new StateMachine(this.algorithm);

        runByTurn(startWordsWrapperList, 1);

        System.out.println("practice success! here are error words that error times over "+recordWordErrorTimes+" times!");
    }

    private void runByTurn(List<WordsWrapper> turnList, int turn) {
        System.out.println("             ===============>the "+turn+" times turn<===============            ");

        ArrayList<WordsWrapper> nextTurnList = new ArrayList<>();
        WordsWrapper thisTimeWordsResult;
        for (WordsWrapper wordsWrapper : this.algorithm.shufflePracticeWordsList(turnList)) {
            thisTimeWordsResult = stateMachine.run(wordsWrapper, turn);
            if (thisTimeWordsResult.getErrorTimes() > 0) {
                nextTurnList.add(wordsWrapper);
            }
        }

        if (CollectionUtils.isNotEmpty(nextTurnList)) {
            runByTurn(nextTurnList, turn + 1);
        }

        System.out.println(errorTimesOverSpecialTimesList.stream().map(WordsWrapper::getWords).collect(Collectors.toList()));
    }
}
