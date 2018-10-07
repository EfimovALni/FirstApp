package cz.firstapp.jackson.model;

import java.util.List;

public class InitialScreen {
    private String color;
    private String text;
    private String icon;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    @Override
    public String toString() {
        return "InitialScreen{" +
                "color='" + color + '\'' +
                ", text='" + text + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}