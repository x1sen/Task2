package com.example.text_task.parser;

import com.example.text_task.entity.ComponentType;
import com.example.text_task.entity.TextComponent;
import com.example.text_task.entity.TextLeaf;
import com.example.text_task.util.RegexConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParser extends AbstractTextParser {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public TextComponent parse(String text) {
        Pattern pattern = Pattern.compile(RegexConstant.WORD_REGEX);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            String word = matcher.group();
            logger.debug("Parsed word: {}", word);
            return new TextLeaf(word, ComponentType.WORD);
        }
        if (nextParser != null) {
            return nextParser.parse(text);
        }
        return new TextLeaf(text, ComponentType.PUNCTUATION);
    }
}
