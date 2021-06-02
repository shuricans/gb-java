package ru.gb.karachev.homework.lesson_7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Solution {

    public static void main(String[] args) {

        Plate plate = new Plate(100);

        List<Cat> cats = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            cats.add(new Cat("cat" + i,
                    ThreadLocalRandom.current().nextInt(3, 40)));
        }

        plate.info();
        cats.forEach(cat -> cat.eat(plate).info());
        plate.info();
    }
}
