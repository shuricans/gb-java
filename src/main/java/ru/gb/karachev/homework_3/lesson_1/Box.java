package ru.gb.karachev.homework_3.lesson_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Box<T extends Fruit> {

    private final List<T> fruits;

    public Box() {
        fruits = new ArrayList<>();
    }

    public Box(List<T> fruits) {
        this.fruits = new ArrayList<>(fruits);
    }

    @SafeVarargs
    public Box(T... fruits) {
        this.fruits = new ArrayList<>();
        Collections.addAll(this.fruits, fruits);
    }

    public boolean add(T fruit) {
        return fruits.add(fruit);
    }

    public float getWeight() {
        if (fruits.isEmpty()) {
            return 0;
        }
        return fruits.size() * fruits.get(0).getWeight();
    }

    public boolean compare(Box<?> box) {
        return this.getWeight() == box.getWeight();
    }

    public void transfer(Box<T> box) {
        box.fruits.addAll(this.fruits);
        this.fruits.clear();
    }

}
