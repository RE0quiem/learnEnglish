package com.learn.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * @author zjz
 * @version 1.0
 * @ClassName CommonLineScan
 * @description
 * @date 2021/5/16 下午4:13
 * @since JDK 1.8
 */
public class CommonLineScan {
    public static String QUIT = ":q";
    public static List<String> enumStrs = new ArrayList<String>() {
        {
            this.add("y");
            this.add("n");
        }
    };

    public static String p = "(\\d{4}-\\d{1,2}-\\d{1,2})|(\\d{4}-\\d{1,2}-\\d{1,2}) To (\\d{4}-\\d{1,2}-\\d{1,2})";

    public static String getInputDateRange(Predicate<String> scanContinuePredicate, Function<String,String>... noInputReplaceInputValue) {

        // !(Pattern.matches(p, inputStr) || enumStrs.contains(inputStr))
        Scanner scan = new Scanner(System.in);
        String inputStr = null;
        if (scan.hasNextLine()) {
            inputStr = scan.nextLine();
            if (noInputReplaceInputValue.length != 0) {
                inputStr=noInputReplaceInputValue[0].apply(inputStr);
            }

            if (QUIT.equals(inputStr)) {
                scan.close();
                interruptedMainThread();
            } else if (scanContinuePredicate.test(inputStr)) {
                System.out.println("do not input illegal character,please reInput");
                getInputDateRange(scanContinuePredicate);
            }
        }
        return inputStr;
    }

    private static void interruptedMainThread() {
        // todo 后面用interrupt改造
        Thread.currentThread().stop();
    }
}
