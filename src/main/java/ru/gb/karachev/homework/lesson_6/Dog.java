package ru.gb.karachev.homework.lesson_6;

import java.util.concurrent.atomic.AtomicInteger;

public class Dog extends Animal {

    private static final AtomicInteger count = new AtomicInteger();

    private static final int LIM_RUN = 500;
    private static final int LIM_SWIM = 10;

    public Dog(String name) {
        super(name, LIM_RUN, LIM_SWIM);
        count.incrementAndGet();
    }

    public static int getCount() {
        return count.get();
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + getName() + '\'' +
                ", limRun=" + getLimRun() +
                ", limSwim=" + getLimSwim() +
                '}';
    }
}
