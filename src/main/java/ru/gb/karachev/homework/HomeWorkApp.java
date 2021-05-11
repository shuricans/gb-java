package ru.gb.karachev.homework;

public class HomeWorkApp {

    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
    }

    public static void printThreeWords() {
        System.out.printf("Orange%nBanana%nApple%n");
    }

    public static void checkSumSign() {
        final int a = 0, b = 0;
        System.out.printf("Сумма %s%n", a + b >= 0 ? "положительная" : "отрицательная");
    }

    public static void printColor() {
        final int value = 0;
        if(value <= 0)
            System.out.println("Красный");
        else if (value <= 100)
            System.out.println("Желтый");
        else if (value > 100)
            System.out.println("Зеленый");
    }

    public static void compareNumbers() {
        final int a = 0, b = 0;
        System.out.printf("a %s b", a < b ? "<" : ">=");
    }
}
