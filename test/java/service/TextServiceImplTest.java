package service;

import com.example.text_task.entity.TextComponent;
import com.example.text_task.exception.TextException;
import com.example.text_task.parser.*;
import com.example.text_task.reader.TextReader;
import com.example.text_task.entity.ComponentType;
import com.example.text_task.service.TextService;
import com.example.text_task.service.impl.TextServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextServiceImplTest {

    private TextService service;
    private TextComponent text;

    @BeforeEach
    void setUp() throws Exception {
        service = new TextServiceImpl();
        TextReader reader = new TextReader();
        String content = reader.readFile("src/main/resources/input.txt");


        var parser = new ParagraphParser();
        var sentenceParser = new SentenceParser();
        var lexemeParser   = new LexemeParser();
        var wordParser     = new WordParser();
        var punctParser    = new PunctuationParser();

        wordParser.setNext(punctParser);
        lexemeParser.setNext(wordParser);
        sentenceParser.setNext(lexemeParser);
        parser.setNext(sentenceParser);

        text = parser.parse(content);
    }

    @Test
    void findMaxSentencesWithSameWords() throws TextException {
        int result = service.findMaxSentencesWithSameWords(text);
        assertTrue(result >= 1);
    }

    @Test
    void sortSentencesByLexemeCount() throws TextException {
        var sorted = service.sortSentencesByLexemeCount(text);
        assertEquals(sorted.size(), getSentenceCount(text));
        for (int i = 1; i < sorted.size(); i++) {
            assertTrue(sorted.get(i).getChildren().size() >=
                    sorted.get(i - 1).getChildren().size());
        }
    }

    @Test
    void swapFirstAndLastLexemeInSentences() throws TextException {
        TextComponent result = service.swapFirstAndLastLexemeInSentences(text);


        var TEXT = ComponentType.TEXT;
        assertEquals(TEXT, result.getType());
    }

    private int getSentenceCount(TextComponent text) {
        int count = 0;
        for (TextComponent p : text.getChildren()) {
            count += p.getChildren().size();
        }
        return count;
    }
}