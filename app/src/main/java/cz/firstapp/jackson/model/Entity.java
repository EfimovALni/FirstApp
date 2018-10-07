package cz.firstapp.jackson.model;

public class Entity {

    volatile int id;
    String name;
    transient long random;

    public Entity(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
