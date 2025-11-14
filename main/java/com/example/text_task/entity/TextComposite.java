package com.example.text_task.entity;

import com.example.text_task.util.RegexConstant;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {
    private final List<TextComponent> components = new ArrayList<>();
    private final ComponentType type;

    public TextComposite(ComponentType type) {
        this.type = type;
    }

    @Override
    public void add(TextComponent component) {
        components.add(component);
    }

    @Override
    public void remove(TextComponent component) {
        components.remove(component);
    }

    @Override
    public List<TextComponent> getChildren() {
        return new ArrayList<>(components);
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public String toPlainText() {
        StringBuilder sb = new StringBuilder();
        for (TextComponent component : components) {
            sb.append(component.toPlainText());
            switch (component.getType()) {
                case PARAGRAPH -> sb.append(RegexConstant.PARAGRAPH_SEPARATOR);
                case SENTENCE -> sb.append(RegexConstant.SENTENCE_SEPARATOR);
                case LEXEME -> sb.append(RegexConstant.LEXEME_SEPARATOR);
                default -> { }
            }
        }
        return sb.toString().trim();
    }
}
