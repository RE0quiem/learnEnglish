package com.learn.main;

import com.learn.domain.Words;
import com.learn.stateMachine.StateMachineManager;
import com.learn.utils.CommonLineScan;
import com.learn.utils.ParseExcel;

import java.util.List;
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
    public static void main(String[] args) {
        ParseExcel parseExcel = new ParseExcel();
        List<Words> wordsList = parseExcel.getWordsList();
        String max = wordsList.stream().map(Words::getBuildDate).max(String::compareTo).get();
        String min = wordsList.stream().map(Words::getBuildDate).min(String::compareTo).get();
        System.out.println("start from " + min + ",end from " + max + ".please set date range to review words! eg (0:no input;1:2021-04-24;2:2021-04-24 To 2021-05-13)");

        String inputDateRange = CommonLineScan.getInputDateRange((inputStr) -> !(Pattern.matches(CommonLineScan.p, inputStr) || CommonLineScan.QUIT.equals(inputStr)),
                (inputStr) -> {
                    if (inputStr == null || "".equals(inputStr)) {
                        return min + " To " + max;
                    }
                    return inputStr;
                });

        String dateRange = inputDateRange.equals("") ? min + " To " + max : inputDateRange;
        List<String> rangeBeHandle = Stream.of(dateRange.split("To")).map(String::trim).collect(Collectors.toList());

        List<Words> practiceWords = wordsList.stream().filter(e -> {
            if (rangeBeHandle.size() == 1) {
                return e.getBuildDate().equals(rangeBeHandle.get(0));
            }
            return e.getBuildDate().compareTo(rangeBeHandle.get(0)) >= 0 && e.getBuildDate().compareTo(rangeBeHandle.get(1)) <= 0;
        }).collect(Collectors.toList());

        StateMachineManager machineManager = new StateMachineManager(practiceWords);
        machineManager.startPractice();
    }

}
