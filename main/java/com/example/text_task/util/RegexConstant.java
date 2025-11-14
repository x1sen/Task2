package com.example.text_task.util;

public final class RegexConstant {
    public static final String PARAGRAPH_REGEX = "(?m)^\\s*\\S.*$";
    public static final String SENTENCE_REGEX = "[^.!?]+[.!?]+";
    public static final String LEXEME_REGEX = "\\s+";
    public static final String WORD_REGEX = "[\\p{L}'-]+";
    public static final String PUNCTUATION_REGEX = "[^\\p{L}\\s'-]+";
    public static final String PARAGRAPH_SEPARATOR = "\n\n";
    public static final String SENTENCE_SEPARATOR = " ";
    public static final String LEXEME_SEPARATOR = " ";

    private RegexConstant() {}
}
