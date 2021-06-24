package com.learn.domain.WordsFunction;

import com.learn.domain.WordsWrapper;

/**
 * 〈〉
 *
 * @author zhanjingzhi-wb-zjz
 * @create 2021/6/24
 */
public abstract class WordsFunction {
    protected WordsWrapper prev;
    protected WordsWrapper next;

    public void setPrev(WordsWrapper prev) {
        this.prev = prev;
    }

    public void setNext(WordsWrapper next) {
        this.next = next;
    }

    public WordsWrapper next() {
        return next;
    }

    public WordsWrapper prev() {
        return prev;
    }

    public void clearLinkedWords() {
        next=null;
        prev=null;
    }
}
