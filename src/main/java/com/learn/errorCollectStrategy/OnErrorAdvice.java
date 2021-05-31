package com.learn.errorCollectStrategy;

import com.learn.domain.WordsWrapper;
import com.learn.stateMachine.StateMachineManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;

/**
 * @author zhanjingzhi-wb
 * @version 1.0
 * @ClassName OnErrorAdvice
 * @description
 * @date 2021/5/28 13:55
 * @since JDK 1.8
 */
@Aspect
public class OnErrorAdvice {


    @Around(value = "execution(* com.learn.algorithm.Algorithm.onError(*))")
    public Object onErrorCollectStrategy(ProceedingJoinPoint joinPoint) throws Throwable {
        WordsWrapper wordsWrapper = (WordsWrapper) joinPoint.getArgs()[0];
        System.out.println(wordsWrapper);
        if (wordsWrapper.getErrorTimes() >= StateMachineManager.recordWordErrorTimes-1) {
            StateMachineManager.errorTimesOverSpecialTimesList.add(wordsWrapper);
        }
        return joinPoint.proceed();
    }
}
