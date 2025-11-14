package com.example.text_task;

import com.example.text_task.entity.TextComponent;
import com.example.text_task.exception.TextException;
import com.example.text_task.reader.TextReader;
import com.example.text_task.parser.*;
import com.example.text_task.service.TextService;
import com.example.text_task.service.impl.TextServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            TextReader reader = new TextReader();
            String text = reader.readFile("src/main/resources/input.txt");


            AbstractTextParser parser = buildParserChain();
            TextComponent parsedText = parser.parse(text);

            logger.info("Original text restored:\n{}", parsedText.toPlainText());

            TextService service = new TextServiceImpl();


            int maxSame = service.findMaxSentencesWithSameWords(parsedText);
            System.out.println("1. Max sentences with same words: " + maxSame);


            var sorted = service.sortSentencesByLexemeCount(parsedText);
            System.out.println("\n2. Sentences sorted by lexeme count:");
            sorted.forEach(s -> System.out.println("  [" + s.getChildren().size() + "] " + s.toPlainText()));


            TextComponent swapped = service.swapFirstAndLastLexemeInSentences(parsedText);
            System.out.println("\n3. After swapping first and last lexeme:");
            System.out.println(swapped.toPlainText());

        } catch (TextException e) {
            logger.error("Application error", e);
        }
    }

    private static AbstractTextParser buildParserChain() {
        PunctuationParser punctuationParser = new PunctuationParser();
        WordParser wordParser = new WordParser();
        wordParser.setNext(punctuationParser);

        LexemeParser lexemeParser = new LexemeParser();
        lexemeParser.setNext(wordParser);

        SentenceParser sentenceParser = new SentenceParser();
        sentenceParser.setNext(lexemeParser);

        ParagraphParser paragraphParser = new ParagraphParser();
        paragraphParser.setNext(sentenceParser);

        return paragraphParser;
    }
}