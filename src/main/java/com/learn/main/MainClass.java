package com.learn.main;

import com.learn.algorithm.DefaultAlgorithm;
import com.learn.domain.Words;
import com.learn.stateMachine.StateMachineManager;
import com.learn.utils.CommonLineScan;
import com.learn.utils.ConsoleDisplayUtils;
import com.learn.utils.ParseExcel;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zjz
 * @version 1.0
 * @ClassName MainClass
 * @description
 * @date 2021/5/16 下午2:00
 * @since JDK 1.8
 */
public class MainClass {
    public static ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1,
            5L, TimeUnit.MINUTES,
            new LinkedBlockingQueue<Runnable>());

    public static void main(String[] args) {
        ParseExcel parseExcel = new ParseExcel(args);
        List<Words> wordsList = parseExcel.getWordsList();
        String max = wordsList.stream().map(Words::getBuildDate).max(String::compareTo).get();
        String min = wordsList.stream().map(Words::getBuildDate).min(String::compareTo).get();

        ConsoleDisplayUtils.displayPeriod(wordsList);
//        ConsoleDisplayUtils.choosePracticePeriod(min, max);

        String inputDateRange = CommonLineScan.getInputDateRange((inputStr) -> !(Pattern.matches(CommonLineScan.p, inputStr) || CommonLineScan.QUIT.equals(inputStr)),
                (inputStr) -> {
                    if (inputStr == null || "".equals(inputStr)) {
                        return wordsList.stream().map(Words::getBuildDate).min(String::compareTo).get() + " To " + wordsList.stream().map(Words::getBuildDate).max(String::compareTo).get();
                    }
                    return inputStr;
                });

        String dateRange = inputDateRange.equals("") ? min + " To " + max : inputDateRange;
        List<String> rangeBeHandle = Stream.of(dateRange.split("To")).map(String::trim).collect(Collectors.toList());


        StateMachineManager machineManager = new StateMachineManager(wordsList, rangeBeHandle, new DefaultAlgorithm());
        machineManager.startPractice();

        shutDownPool();
    }

    public static void shutDownPool() {
        if (!executor.isShutdown()) {
            executor.shutdownNow();
        }
    }

}
