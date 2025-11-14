package com.example.text_task.parser;

import com.example.text_task.entity.ComponentType;
import com.example.text_task.entity.TextComponent;
import com.example.text_task.entity.TextLeaf;
import com.example.text_task.util.RegexConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PunctuationParser extends AbstractTextParser {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public TextComponent parse(String text) {
        Pattern pattern = Pattern.compile(RegexConstant.PUNCTUATION_REGEX);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            String punct = matcher.group();
            logger.debug("Parsed punctuation: {}", punct);
            return new TextLeaf(punct, ComponentType.PUNCTUATION);
        }
        return new TextLeaf(text, ComponentType.WORD);
    }
}
