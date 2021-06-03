package com.learn.utils;

import com.learn.domain.WordsWrapper;

/**
 * @author zhanjingzhi-wb
 * @version 1.0
 * @ClassName ConsoleDisplayUtils
 * @description
 * @date 2021/6/3 17:09
 * @since JDK 1.8
 */
public class  ConsoleDisplayUtils {
    final private static String practiceBoxTop =
            ".=============================================================================================.\n" +
            "||                                         words:%s +\n" +
            "||                             phoneticSymbol:%s  practiceTimes:%s +\n" +
            "|'---------------------------------------------------------------------------------------------'|\n" +
            "||                                      \t\t\t\t\t\t\t\t\t\t\t\t\t   ||\n" +
            "||\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   ||\n" +
            "||\t\t\t\t\t\twhat means?                                 eg(y|n|:q) \t\t\t\t   ||\n" +
            "||\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   ||\n" +
            ".'============================================================================================='.";

    final private static String nextStep=
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t  (next step)\n" +
            "\n";

    final private static String nextLoop=
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t(the %s loop final, go next)\n" +
            "\n";

    final private static String completeDisplay=
            "|'`````````````````````````````````````````````````````````````````````````````````````````````'|\n" +
            "||    practice complete, you can see collected words in '%s' which error time over %s times !  \n" +
            "||    in addtion to,there are altogether %s error words in this practice. Accuracy is %s  .    \n" +
            "||                                                                                             ||\n" +
            ".'`````````````````````````````````````````````````````````````````````````````````````````````'.";


    public static void wordsSuccessDisplay(WordsWrapper wordsWrapper){
        System.out.println(wordsWrapper.toStringOnSuccess());
    }

    public static void wordsErrorDisplay(WordsWrapper wordsWrapper){
        System.out.println(wordsWrapper.getWords());
    }

    public static void illegalInput(){
        System.out.println("do not input illegal character,please reInput");
    }

    public static void choosePracticePeriod(String min,String max){
        System.out.println("start from " + min + ",end from " + max + ".please set date range to review words! eg (0:no input;1:2021-04-24;2:2021-04-24 To 2021-05-13)");
    }

    public static void displayThePracticeWords(WordsWrapper wordsWrapper) {
        System.out.println(String.format(practiceBoxTop,wordsWrapper.getWords().getWords(), wordsWrapper.getWords().getPhoneticSymbol(), wordsWrapper.getErrorTimes()));
    }

    public static void nextStep(){
        System.out.println(nextStep);
    }

    public static void nextTurn(int turn){
        System.out.println(String.format(nextLoop,turn));
    }

    public static void practiceComplete(String errorWordsSaveFilePath, int propertiesErrorTime, int totalErrorTimes, long accuracy) {
        System.out.println(String.format(completeDisplay,errorWordsSaveFilePath,propertiesErrorTime,totalErrorTimes,accuracy));
    }
}
