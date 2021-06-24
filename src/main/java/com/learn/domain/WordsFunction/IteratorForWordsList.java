package com.learn.domain.WordsFunction;

import com.learn.domain.WordsWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhanjingzhi-wb
 * @version 1.0
 * @ClassName IteratorForWordsList
 * @description
 * @date 2021/6/24 11:43
 * @since JDK 1.8
 */
public class IteratorForWordsList {
    private WordsWrapper currentWords;
    private WordsWrapper prev;
    private WordsWrapper next;
    private List<WordsWrapper> datasourceList;

    public IteratorForWordsList(List<WordsWrapper> datasourceList) {
        List<WordsWrapper> clearLinkedRelation = datasourceList.stream().peek(WordsFunction::clearLinkedWords).collect(Collectors.toList());
        WordsWrapper wrapper;
        List<WordsWrapper> addLinkedReleation = new ArrayList<>();
        for (int i = 0; i < clearLinkedRelation.size(); i++) {
            wrapper = clearLinkedRelation.get(i);
            wrapper.setPrev(i - 1 >= 0 ? clearLinkedRelation.get(i - 1) : null);
            wrapper.setNext(i + 1 < clearLinkedRelation.size() ? clearLinkedRelation.get(i + 1) : null);
            addLinkedReleation.add(wrapper);
        }

        currentWords = datasourceList.get(0);
        next=currentWords.next();
        this.datasourceList = addLinkedReleation;
    }

    public boolean hasNext() {
        return next!=null;
    }

    public WordsWrapper next() {
        if (currentWords == null) {
            currentWords = datasourceList.get(0);
            next = currentWords.next();
        } else {
            // preHandle
            prev= currentWords;
            currentWords=(next==null?prev:next);
            next=currentWords.next();
        }

        return currentWords;
    }

    public WordsWrapper jumpPreved() {
        next=currentWords;
        currentWords = (prev==null?currentWords:prev);
        prev=currentWords.prev();
        return currentWords;
    }
}
