package com.example.text_task.service.impl;

import com.example.text_task.exception.TextException;
import com.example.text_task.entity.ComponentType;
import com.example.text_task.entity.TextComponent;
import com.example.text_task.entity.TextComposite;
import com.example.text_task.service.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public int findMaxSentencesWithSameWords(TextComponent text) throws TextException {
        List<TextComponent> sentences = extractSentences(text);
        Map<Set<String>, Integer> wordSetToCount = new HashMap<>();

        for (TextComponent sentence : sentences) {
            Set<String> words = extractWords(sentence).stream()
                    .map(String::toLowerCase)
                    .collect(Collectors.toSet());
            wordSetToCount.merge(words, 1, Integer::sum);
        }

        int max = wordSetToCount.values().stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);

        logger.info("Max sentences with same words: {}", max);
        return max;
    }

    @Override
    public List<TextComponent> sortSentencesByLexemeCount(TextComponent text) throws TextException {
        List<TextComponent> sentences = extractSentences(text);
        sentences.sort(Comparator.comparingInt(s -> s.getChildren().size()));

        logger.info("Sorted {} sentences by lexeme count", sentences.size());
        return sentences;
    }

    @Override
    public TextComponent swapFirstAndLastLexemeInSentences(TextComponent text) throws TextException {
        TextComposite result = new TextComposite(ComponentType.TEXT);

        for (TextComponent paragraph : text.getChildren()) {
            TextComposite newParagraph = new TextComposite(ComponentType.PARAGRAPH);
            for (TextComponent sentence : paragraph.getChildren()) {
                TextComposite newSentence = swapLexemesInSentence(sentence);
                newParagraph.add(newSentence);
            }
            result.add(newParagraph);
        }

        logger.info("Swapped first and last lexemes in all sentences");
        return result;
    }

    private TextComposite swapLexemesInSentence(TextComponent sentence) {
        List<TextComponent> lexemes = sentence.getChildren();
        if (lexemes.size() <= 1) {
            return (TextComposite) sentence;
        }

        TextComposite newSentence = new TextComposite(ComponentType.SENTENCE);
        TextComponent first = lexemes.get(0);
        TextComponent last = lexemes.get(lexemes.size() - 1);

        newSentence.add(last);
        for (int i = 1; i < lexemes.size() - 1; i++) {
            newSentence.add(lexemes.get(i));
        }
        newSentence.add(first);

        return newSentence;
    }

    private List<TextComponent> extractSentences(TextComponent text) {
        List<TextComponent> sentences = new ArrayList<>();
        for (TextComponent paragraph : text.getChildren()) {
            sentences.addAll(paragraph.getChildren());
        }
        return sentences;
    }

    private List<String> extractWords(TextComponent sentence) {
        List<String> words = new ArrayList<>();
        for (TextComponent lexeme : sentence.getChildren()) {
            for (TextComponent child : lexeme.getChildren()) {
                if (child.getType() == ComponentType.WORD) {
                    words.add(child.toPlainText());
                }
            }
        }
        return words;
    }
}
