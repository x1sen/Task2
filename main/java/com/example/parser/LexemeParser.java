package com.example.parser;

import com.example.entity.*;
import com.example.util.RegexConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends AbstractTextParser {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public TextComponent parse(String text) {
        TextComposite sentence = new TextComposite(ComponentType.SENTENCE);
        String[] parts = text.split(RegexConstants.LEXEME_REGEX);

        for (String part : parts) {
            if (part.isEmpty()) continue;
            TextComponent lexemeComponent = nextParser.parse(part.trim());
            sentence.add(lexemeComponent);
        }

        logger.info("Parsed {} lexemes in sentence", sentence.getChildren().size());
        return sentence;
    }
}
