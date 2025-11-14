package com.example.text_task.service;

import com.example.text_task.entity.TextComponent;
import com.example.text_task.exception.TextException;

import java.util.List;

public interface TextService {
    int findMaxSentencesWithSameWords(TextComponent text) throws TextException;
    List<TextComponent> sortSentencesByLexemeCount(TextComponent text) throws TextException;
    TextComponent swapFirstAndLastLexemeInSentences(TextComponent text) throws TextException;
}
