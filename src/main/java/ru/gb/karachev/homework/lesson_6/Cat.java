package ru.gb.karachev.homework.lesson_6;

import java.util.concurrent.atomic.AtomicInteger;

public class Cat extends Animal {

    private static final AtomicInteger count = new AtomicInteger();

    private static final int LIM_RUN = 200;
    private static final int LIM_SWIM = 0;

    public Cat(String name) {
        super(name, LIM_RUN, LIM_SWIM);
        count.incrementAndGet();
    }

    @Override
    public void swim(int length) {
        System.out.printf("%s cannot swim.%n", getName());
    }

    public static int getCount() {
        return count.get();
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + getName() + '\'' +
                ", limRun=" + getLimRun() +
                ", limSwim=" + getLimSwim() +
                '}';
    }
}
