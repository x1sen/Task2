package com.example.text_task.parser;

import com.example.text_task.entity.TextComponent;

public abstract class AbstractTextParser {
    protected AbstractTextParser nextParser;

    public void setNext(AbstractTextParser nextParser) {
        this.nextParser = nextParser;
    }

    public abstract TextComponent parse(String text);
}
