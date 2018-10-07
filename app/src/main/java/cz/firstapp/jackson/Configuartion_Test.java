package cz.firstapp.jackson;

public class Configuartion_Test {
    private String initialScreen;

    public String getInitialScreen(){
        return initialScreen;
    }
    public void setInitialScreen(String input){
        this.initialScreen = input;
    }

    @Override
    public String toString() {
        return "Configuartion_Test{" +
                "initialScreen='" + initialScreen + '\'' +
                '}';
    }
}
