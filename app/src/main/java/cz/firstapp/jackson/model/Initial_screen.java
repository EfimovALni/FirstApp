package cz.firstapp.jackson.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Initial_screen {
    /*{
    "status": "OK",
    "actual_version": 0.01,
    "configuartion": {
        "initial_screen": [
        {
            "color": "#0000ff",
                "text": "software",
                "icon": "data:image/png;base64,iVBORw0KGgoAAAA */

    @SerializedName("color")
    @Expose
    private String color;

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("icon")
    @Expose
    private byte[] icon;

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

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Initial_screen{" +
                "color='" + color + '\'' +
                ", text='" + text + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
