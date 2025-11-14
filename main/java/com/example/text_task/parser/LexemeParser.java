package com.example.text_task.parser;

import com.example.text_task.entity.ComponentType;
import com.example.text_task.entity.TextComponent;
import com.example.text_task.entity.TextComposite;
import com.example.text_task.util.RegexConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LexemeParser extends AbstractTextParser {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public TextComponent parse(String text) {
        TextComposite sentence = new TextComposite(ComponentType.SENTENCE);
        String[] parts = text.split(RegexConstant.LEXEME_REGEX);

        for (String part : parts) {
            if (part.isEmpty()) continue;
            TextComponent lexemeComponent = nextParser.parse(part.trim());
            sentence.add(lexemeComponent);
        }

        logger.info("Parsed {} lexemes in sentence", sentence.getChildren().size());
        return sentence;
    }
}
