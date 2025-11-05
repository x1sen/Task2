package service;

import com.example.entity.TextComponent;
import com.example.exception.TextException;
import com.example.parser.ParagraphParser;
import com.example.reader.TextReader;
import com.example.service.TextService;
import com.example.service.TextServiceImpl;

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

        // ВАЖНО: создаём ВСЮ цепочку парсеров!
        var parser = new ParagraphParser();
        var sentenceParser = new com.example.parser.SentenceParser();
        var lexemeParser   = new com.example.parser.LexemeParser();
        var wordParser     = new com.example.parser.WordParser();
        var punctParser    = new com.example.parser.PunctuationParser();

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

        // ComponentType берём напрямую из enum-класса
        var TEXT = com.example.entity.ComponentType.TEXT;
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