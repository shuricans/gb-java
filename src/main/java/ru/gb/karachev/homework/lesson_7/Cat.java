package ru.gb.karachev.homework.lesson_7;

public class Cat {

    private final String name;
    private final int appetite;
    private boolean isFull;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.isFull = false;
    }

    public Cat eat(Plate p) {
        if (p.getFood() >= appetite) {
            p.decreaseFood(appetite);
            isFull = true;
        }
        return this;
    }

    public void info() {
        System.out.printf("The %s is %s%n", name, isFull ? "full" : "hungry");
    }
}
