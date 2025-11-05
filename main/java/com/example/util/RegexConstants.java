package com.example.util;

public final class RegexConstants {
    public static final String PARAGRAPH_REGEX = "(?m)^\\s*\\S.*$";
    public static final String SENTENCE_REGEX = "[^.!?]+[.!?]+";
    public static final String LEXEME_REGEX = "\\s+";
    public static final String WORD_REGEX = "[\\p{L}'-]+";
    public static final String PUNCTUATION_REGEX = "[^\\p{L}\\s'-]+";

    private RegexConstants() {}
}
