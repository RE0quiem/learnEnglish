package com.learn.stateMachine;

import com.learn.algorithm.AbstractAlgorithm;
import com.learn.domain.Words;
import com.learn.domain.WordsWrapper;
import com.learn.plugin.Plugin;
import com.learn.plugin.PluginManager;
import com.learn.utils.ConsoleDisplayUtils;
import com.learn.utils.ParseProperties;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
@SuppressWarnings("unchecked")
public class StateMachineManager {
    private StateMachine stateMachine;
    private List<WordsWrapper> startWordsWrapperList;
    private AbstractAlgorithm algorithm;
    private PluginManager pluginManager;
    private List<Words> allWordsInExcel;
    public static int recordWordErrorTimes;

    //StateMachine store error word that error times over special times by properties
    public static Set<WordsWrapper> errorTimesOverSpecialTimesList = new TreeSet<>(Comparator.comparing(e -> e.getWords().getWords()));

    public StateMachineManager(List<Words> wordsList, List<String> rangeBeHandle, AbstractAlgorithm algorithm) {
        // init=>StateMachineManager
        // parse properties.properties
        ParseProperties parseProperties = new ParseProperties();
        recordWordErrorTimes = Integer.parseInt(parseProperties.parseProperties().getProperty("recordWordErrorTimes"));

        // setting algorithm todo here default
        this.algorithm = algorithm;

        // load plugin
        pluginManager = PluginManager.getInstence();

        // setPracticeWords
        allWordsInExcel = wordsList;
        List<Words> practiceWords = wordsList.stream().filter(e -> {
            if (rangeBeHandle.size() == 1) {
                return e.getBuildDate().equals(rangeBeHandle.get(0));
            }
            return e.getBuildDate().compareTo(rangeBeHandle.get(0)) >= 0 && e.getBuildDate().compareTo(rangeBeHandle.get(1)) <= 0;
        }).collect(Collectors.toList());

        this.startWordsWrapperList = practiceWords.stream().map(each -> {
            WordsWrapper wrapper = new WordsWrapper();
            wrapper.setErrorTimes(1);
            wrapper.setWords(each);
            return wrapper;
        }).collect(Collectors.toList());
    }


    public void startPractice() {
        this.stateMachine = new StateMachine(this.algorithm);

        Set<Map.Entry<String, Plugin>> beforePlugins = pluginManager.getBeforePlugins().entrySet();
        for (Map.Entry<String, Plugin> beforePlugin : beforePlugins) {
            if (beforePlugin.getKey().contains("EchoLastPracticeErrorWordsIntoThisTime")) {
                startWordsWrapperList = (List<WordsWrapper>) beforePlugin.getValue().apply(new Object[]{startWordsWrapperList, allWordsInExcel});
            }
        }

        runByTurn(startWordsWrapperList, 1);

        Set<Map.Entry<String, Plugin>> afterPlugins = pluginManager.getAfterPlugins().entrySet();
        for (Map.Entry<String, Plugin> afterPlugin : afterPlugins) {
            afterPlugin.getValue().apply(errorTimesOverSpecialTimesList.stream().map(WordsWrapper::getWords).collect(Collectors.toList()));
        }

        final Object wordsSaveFilePath = new ParseProperties().parseProperties().get("errorWordsSaveFilePath");
        long accuracy = Integer.toUnsignedLong(errorTimesOverSpecialTimesList.size() / allWordsInExcel.size());
        ConsoleDisplayUtils.practiceComplete((String)wordsSaveFilePath,
                recordWordErrorTimes,
                errorTimesOverSpecialTimesList.size(),
                accuracy);
    }


    private void runByTurn(List<WordsWrapper> turnList, int turn) {
        ConsoleDisplayUtils.nextTurn(turn);
        ArrayList<WordsWrapper> nextTurnList = new ArrayList<>();
        WordsWrapper thisTimeWordsResult;
        int i=0;
        for (WordsWrapper wordsWrapper : this.algorithm.shufflePracticeWordsList(turnList)) {
            thisTimeWordsResult = stateMachine.run(wordsWrapper, turn ,pluginManager.getCustomPlugins());
            if (thisTimeWordsResult.getErrorTimes() > 0) {
                nextTurnList.add(wordsWrapper);
            }
            i++;
        }

        if (CollectionUtils.isNotEmpty(nextTurnList)) {
            runByTurn(nextTurnList, turn + 1);
        }
    }
}
