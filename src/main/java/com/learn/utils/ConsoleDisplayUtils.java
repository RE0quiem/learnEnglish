package com.learn.utils;

import com.learn.domain.Words;
import com.learn.domain.WordsWrapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhanjingzhi-wb
 * @version 1.0
 * @ClassName ConsoleDisplayUtils
 * @description
 * @date 2021/6/3 17:09
 * @since JDK 1.8
 */
public class ConsoleDisplayUtils {
    final private static String practiceBoxTop =
            ".=============================================================================================.\n" +
                    "||                                         words:%s \n" +
                    "||            phoneticSymbol:%s  practiceTimes:%s  characteristic:%s\n" +
                    "||            exampleSentence:%s\n" +
                    "|'---------------------------------------------------------------------------------------------'|\n" +
                    "||                                      \t\t\t\t\t\t\t\t\t\t\t\t\t   ||\n" +
                    "||\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   ||\n" +
                    "||\t\t\t\t\t\twhat means?                            eg(y|n|back|:q) \t\t\t\t   ||\n" +
                    "||\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   ||\n" +
                    ".'============================================================================================='.";

    final private static String nextStep =
            "\n" +
                    "\t\t\t\t\t\t\t\t\t\t  (next step)\n" +
                    "\n";

    final private static String nextLoop =
            "\n" +
                    "\t\t\t\t\t\t\t\t\t\t(the %s loop final, go next)\n" +
                    "\n";

    final private static String completeDisplay =
            "|'`````````````````````````````````````````````````````````````````````````````````````````````'|\n" +
                    "||    practice complete, you can see collected words in '%s' which error time over %s times !  \n" +
                    "||    in addtion to,there are altogether %s error words in this practice. Accuracy is %s  .    \n" +
                    "||                                                                                             ||\n" +
                    ".'`````````````````````````````````````````````````````````````````````````````````````````````'.";

    final private static String periodTitle =
            "|'---------------------------------------------------------------------------------------------'|\n" +
            "                                        choose period                \t\t\t\t\t\t     ";


    final private static String periodFooter =
            ".'============================================================================================='.";

    public static void wordsSuccessDisplay(WordsWrapper wordsWrapper) {
        System.out.println(wordsWrapper.toStringOnSuccess());
    }

    public static void wordsErrorDisplay(WordsWrapper wordsWrapper) {
        System.out.println(wordsWrapper.getWords());
    }

    public static void illegalInput() {
        System.out.println("do not input illegal character,please reInput");
    }

    public static void displayThePracticeWords(WordsWrapper wordsWrapper) {
        System.out.println(String.format(practiceBoxTop, wordsWrapper.getWords().getWords(), wordsWrapper.getWords().getPhoneticSymbol(), wordsWrapper.getErrorTimes(),wordsWrapper.getWords().getCharacteristic(),wordsWrapper.getWords().getExampleSentence()));
    }

    public static void nextStep() {
        System.out.println(nextStep);
    }

    public static void nextTurn(int turn) {
        System.out.println(String.format(nextLoop, turn));
    }

    public static void practiceComplete(String errorWordsSaveFilePath, int propertiesErrorTime, int totalErrorTimes, double accuracy) {
        System.out.println(String.format(completeDisplay, errorWordsSaveFilePath, propertiesErrorTime, totalErrorTimes, accuracy));
    }

    public static void displayPeriod(List<Words> words) {
        final List<String> periods = words.stream()
                .map(Words::getBuildDate)
                .distinct()
                .sorted(String::compareTo)
                .collect(Collectors
                        .collectingAndThen(Collectors
                                .toList(), list -> {
                            String lastPeriod = list.get(list.size() - 1);
                            String lastThreeDay = list.get(list.size() - 3);
                            String lastWeek = list.get(list.size() - 7);
                            String alternativeOne = lastThreeDay + " To " + lastPeriod;
                            String alternativeTwo = lastWeek + " To " + lastPeriod;
                            list.add(alternativeOne);
                            list.add(alternativeTwo);
                            list.add("no input (this represent select all)");
                            return list;
                        }));


        int lineNum = periods.size() % 3 == 0 ? periods.size() / 3 : periods.size() / 3 + 1;
        Iterator<String> iterator = periods.iterator();
        StringBuilder next;
        System.out.println(periodTitle);
        int j;
        int k=36;
        List<String> lineStr = new ArrayList<>();
        for (int i = 0; i < lineNum; i++) {
            j = 0;
            while (iterator.hasNext()) {
                next = new StringBuilder(iterator.next());
                for (int l = 0; l < k - next.length(); l++) {
                    next.append("       ");
                }
                lineStr.add(next.toString());
                if (j == 2) {
                    break;
                }
                j++;
            }
            System.out.println(String.join("", lineStr));
            lineStr.clear();
        }

        System.out.println(periodFooter);
    }

    public static void spellWordsInput() {
        System.out.print("Spell words:");
    }

    public static void isEnableWordsInput() {
        System.out.print("isEnableWordsInput:");
    }
}
