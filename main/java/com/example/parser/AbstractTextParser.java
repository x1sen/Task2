package com.example.parser;

import com.example.entity.TextComponent;

public abstract class AbstractTextParser {
    protected AbstractTextParser nextParser;

    public void setNext(AbstractTextParser nextParser) {
        this.nextParser = nextParser;
    }

    public abstract TextComponent parse(String text);
}
