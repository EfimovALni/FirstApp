package cz.firstapp.jackson.model;

import java.util.List;


public class Configuartion {
    private List<InitialScreen> initial_screen;

    public List<InitialScreen> getInitial_screen() {
        return initial_screen;
    }

    public void setInitial_screen(List<InitialScreen> initial_screen) {
        this.initial_screen = initial_screen;
    }

    @Override
    public String toString() {
        return "Configuartion{" +
                "initial_screen=" + initial_screen +
                '}';
    }
}