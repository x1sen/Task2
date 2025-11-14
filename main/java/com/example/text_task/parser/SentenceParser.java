package com.example.text_task.parser;

import com.example.text_task.entity.ComponentType;
import com.example.text_task.entity.TextComponent;
import com.example.text_task.entity.TextComposite;
import com.example.text_task.util.RegexConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractTextParser {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public TextComponent parse(String text) {
        TextComposite paragraph = new TextComposite(ComponentType.PARAGRAPH);
        Pattern pattern = Pattern.compile(RegexConstant.SENTENCE_REGEX);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String sentenceText = matcher.group().trim();
            TextComposite sentence = new TextComposite(ComponentType.SENTENCE);
            TextComponent lexemeComponent = nextParser.parse(sentenceText);
            for (TextComponent lexeme : lexemeComponent.getChildren()) {
                sentence.add(lexeme);
            }
            paragraph.add(sentence);
        }

        logger.info("Parsed {} sentences in paragraph", paragraph.getChildren().size());
        return paragraph;
    }
}
