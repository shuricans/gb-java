package ru.gb.karachev.homework_3.lesson_1;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {

    private final List<T> fruits;

    public Box() {
        fruits = new ArrayList<>();
    }

    public boolean add(T fruit) {
        if (fruits.size() == 0) {
            return fruits.add(fruit);
        }
        if (fruits.get(0).getClass().equals(fruit.getClass())) {
            return fruits.add(fruit);
        } else {
            throw new IllegalArgumentException("The box can only contain objects class "
                    + fruits.get(0).getClass().getSimpleName()
                    + ". You are trying add " + fruit.getClass().getSimpleName()
            );
        }
    }

    public float getWeight() {
        if (fruits.isEmpty()) {
            return 0;
        }
        return fruits.size() * fruits.get(0).getWeight();
    }

    public boolean compare(Box<? extends Fruit> box) {
        return 0 == Float.compare(this.getWeight(), box.getWeight());
    }

    public void transfer(Box<T> box) {
        if (this.fruits.isEmpty()) {
            return;
        }
        box.fruits.addAll(this.fruits);
        this.fruits.clear();
    }
}
