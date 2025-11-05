package com.example.service;

import com.example.entity.TextComponent;
import com.example.exception.TextException;

import java.util.List;

public interface TextService {
    int findMaxSentencesWithSameWords(TextComponent text) throws TextException;
    List<TextComponent> sortSentencesByLexemeCount(TextComponent text) throws TextException;
    TextComponent swapFirstAndLastLexemeInSentences(TextComponent text) throws TextException;
}
