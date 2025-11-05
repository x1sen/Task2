package com.example.entity;

import java.util.Collections;
import java.util.List;

public class TextLeaf implements TextComponent {
    private final String content;
    private final ComponentType type;

    public TextLeaf(String content, ComponentType type) {
        this.content = content;
        this.type = type;
    }

    @Override
    public void add(TextComponent component) {
        throw new UnsupportedOperationException("Cannot add to leaf");
    }

    @Override
    public void remove(TextComponent component) {
        throw new UnsupportedOperationException("Cannot remove from leaf");
    }

    @Override
    public List<TextComponent> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public String toPlainText() {
        return content;
    }

    @Override
    public String toString() {
        return content;
    }
}
