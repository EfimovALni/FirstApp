package cz.firstapp.jackson.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Configuartion {
    /*{
    "status": "OK",
    "actual_version": 0.01,
    "configuartion": {
        "initial_screen": [
        {
            "color": "#0000ff",
                "text": "software",
                "icon": "data:image/png;base64,iVBORw0KGgoAAAA */

    @SerializedName("initial_screen")
    @Expose
    private ArrayList<Initial_screen> initial_screen;

    public ArrayList<Initial_screen> getInitial_screen() {
        return initial_screen;
    }

    public void setInitial_screen(ArrayList<Initial_screen> initial_screen) {
        this.initial_screen = initial_screen;
    }

    @Override
    public String toString() {
        return "Configuartion{" +
                "initial_screen=" + initial_screen +
                '}';
    }
}
