package com.example.parser;

import com.example.entity.*;
import com.example.util.RegexConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends AbstractTextParser {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public TextComponent parse(String text) {
        TextComposite textComposite = new TextComposite(ComponentType.TEXT);
        Pattern pattern = Pattern.compile(RegexConstants.PARAGRAPH_REGEX, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String paragraphText = matcher.group().trim();
            if (!paragraphText.isEmpty()) {
                TextComposite paragraph = new TextComposite(ComponentType.PARAGRAPH);
                TextComponent sentenceComponent = nextParser.parse(paragraphText);
                for (TextComponent sentence : sentenceComponent.getChildren()) {
                    paragraph.add(sentence);
                }
                textComposite.add(paragraph);
            }
        }

        logger.info("Parsed {} paragraphs", textComposite.getChildren().size());
        return textComposite;
    }
}
