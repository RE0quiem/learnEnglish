package com.learn.plugin;

import cn.hutool.core.lang.Assert;
import com.learn.domain.WordsWrapper;
import com.learn.utils.CommonLineScan;
import com.learn.utils.ConsoleDisplayUtils;

/**
 * @author zhanjingzhi-wb
 * @version 1.0
 * @ClassName InputPractice
 * @description
 * @date 2021/6/21 10:15
 * @since JDK 1.8
 */
public class InputPractice extends CustomPlugin {
    public InputPractice() {
        runningTime = enumRunningTime[1];
    }
    public static boolean isEnableInputWordsPractice=false;

    @Override
    public Object apply(Object o) {
        if (isEnableInputWordsPractice) {
            Object[] params = (Object[]) o;
            WordsWrapper wordsWrapper = (WordsWrapper) params[0];
            ConsoleDisplayUtils.spellWordsInput();
            CommonLineScan.getInputDateRange((inputStr)->{
                Assert.isTrue(inputStr!=null);
                if (inputStr.toLowerCase().trim().equals(wordsWrapper.getWords().getWords().toLowerCase().trim())) {
                    return false;
                }
                return true;
            });
        }

        return null;
    }

    public static void isEnableWordsInput(){
        ConsoleDisplayUtils.isEnableWordsInput();
        CommonLineScan.getInputDateRange((inputStr)->{
            Assert.isTrue(inputStr!=null);
            isEnableInputWordsPractice=false;
            if (inputStr.equals(CommonLineScan.enumStrs.get(0))) {
                isEnableInputWordsPractice=true;
            }
            return false;
        });
    };

    @Override
    public Integer get() {
        return 2;
    }
}
