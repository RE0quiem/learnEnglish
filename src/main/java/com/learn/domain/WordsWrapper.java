package com.learn.domain;

import lombok.Data;

/**
 * @author zjz
 * @version 1.0
 * @ClassName WordsWrapper
 * @description
 * @date 2021/5/16 下午2:31
 * @since JDK 1.8
 */
@Data
public class WordsWrapper {
    private int errorTimes;
    private Words words;
}
