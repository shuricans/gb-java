package ru.gb.karachev.homework.lesson_6;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Animal {

    private static final AtomicInteger count = new AtomicInteger();

    private final String name;
    private final int limRun;
    private final int limSwim;

    public Animal(String name, int limRun, int limSwim) {
        this.name = name;
        this.limRun = limRun;
        this.limSwim = limSwim;
        count.incrementAndGet();
    }

    public void run(int length) {
        if (length < 0) {
            length = 0;
        } else if (length > limRun) {
            length = limRun;
        }
        System.out.printf("%s run - %d m.%n", name, length);
    }

    public void swim(int length) {
        if (length < 0) {
            length = 0;
        } else if (length > limSwim) {
            length = limSwim;
        }
        System.out.printf("%s swim - %d m.%n", name, length);
    }

    public static int getCount() {
        return count.get();
    }

    public String getName() {
        return name;
    }

    public int getLimRun() {
        return limRun;
    }

    public int getLimSwim() {
        return limSwim;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", limRun=" + limRun +
                ", limSwim=" + limSwim +
                '}';
    }
}
