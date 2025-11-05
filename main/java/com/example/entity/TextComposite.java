package com.example.entity;

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
            if (component.getType() == ComponentType.PARAGRAPH) {
                sb.append("\n\n");
            } else if (component.getType() == ComponentType.SENTENCE) {
                sb.append(" ");
            } else if (component.getType() == ComponentType.LEXEME) {
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    @Override
    public String toString() {
        return toPlainText();
    }
}
