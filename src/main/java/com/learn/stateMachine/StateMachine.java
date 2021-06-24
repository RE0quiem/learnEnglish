package com.learn.stateMachine;

import com.learn.algorithm.AbstractAlgorithm;
import com.learn.domain.WordsWrapper;
import com.learn.plugin.CustomPlugin;
import com.learn.plugin.InputPractice;
import com.learn.plugin.PlayMp3Plugin;
import com.learn.plugin.pluginType.CustomPluginType;
import com.learn.utils.CommonLineScan;
import com.learn.utils.ConsoleDisplayUtils;

import java.util.Map;

/**
 * @author zjz
 * @version 1.0
 * @ClassName StatementMachin
 * @description 状态机 控制每一个word的运行流程
 * @date 2021/5/16 下午6:07
 * @since JDK 1.8
 */
public class StateMachine {
    private AbstractAlgorithm algorithm;
    public StateMachine(AbstractAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    //todo 由于没有依赖注入,本方法的切面是无法拿到pluginManager中的customPlugins的.所以在这个地方添加这个作为参数传入切面
    @CustomPluginType(plugins = {PlayMp3Plugin.class, InputPractice.class})
    public WordsWrapper run(WordsWrapper wordsWrapper, int turn , Map<String, ? super CustomPlugin> customPlugins) {
        // display the practice words
        String practiceResult = displayThePracticeWords(wordsWrapper);
        // input Y | input N | input back
        if ("y".equals(practiceResult)) {
            algorithm.handleSuccess(wordsWrapper);
        } else if ("n".equals(practiceResult)) {
            algorithm.handleError(wordsWrapper);
        } else {
            // forward prev wordWrapper
            return null;
        }
        return wordsWrapper;
    }

    private String displayThePracticeWords(WordsWrapper wordsWrapper) {
        ConsoleDisplayUtils.displayThePracticeWords(wordsWrapper);
        return CommonLineScan.getInputDateRange((inputStr)-> !CommonLineScan.enumStrs.contains(inputStr));
    }
}
