package com.learn.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
    private static List<String> enumStrs=new ArrayList<String>(){
        {
            this.add("y");
            this.add("n");
            this.add("eof");
        }
    };

    private static Pattern r = Pattern.compile("(\\d{4}-\\d{1,2}-\\d{1,2})|(\\d{4}-\\d{1,2}-\\d{1,2}) To (\\d{4}-\\d{1,2}-\\d{1,2})");

    public static String getInputDateRange(){

        Scanner scan = new Scanner(System.in);
        String  inputStr = null;
        if (scan.hasNextLine()) {
            inputStr = scan.nextLine();
            if ("eof".equals(inputStr)) {
                scan.close();
            }
            if(!enumStrs.contains(inputStr)||!r.matcher(inputStr).matches()){
                System.out.println("do not input illegal character,please reInput");
                getInputDateRange();
            }
        }
        return inputStr;
    }
}
