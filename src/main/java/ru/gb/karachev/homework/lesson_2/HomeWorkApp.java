package ru.gb.karachev.homework.lesson_2;

import java.util.GregorianCalendar;

public class HomeWorkApp {

    public static void main(String[] args) {

    }

    // Написать метод, принимающий на вход два целых числа и проверяющий,
    // что их сумма лежит в пределах от 10 до 20 (включительно),
    // если да – вернуть true, в противном случае – false.

    /**
     * Принимает на вход два целых числа и проверяет,
     * что их сумма лежит в пределах от 10 до 20 (включительно),
     * @param a integer
     * @param b integer
     * @return <code>true</code> if
     */
    private static boolean inRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    // Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль,
    // положительное ли число передали или отрицательное. Замечание: ноль считаем положительным числом.

    /**
     *
     * @param a integer
     */
    private static void printSign(int a) {
        System.out.printf("Число %d: %s%n", a, a < 0 ? "отрицательное" : "положительное");
    }

    // Написать метод, которому в качестве параметра передается целое число.
    // Метод должен вернуть true, если число отрицательное, и вернуть false если положительное.

    /**
     *
     * @param a
     * @return
     */
    private static boolean isNegativeNumber(int a) {
        return a < 0;
    }

    // Написать метод, которому в качестве аргументов передается строка и число,
    // метод должен отпечатать в консоль указанную строку, указанное количество раз;

    /**
     *
     * @param s
     * @param count integer
     */
    private static void printFewTimes(String s, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(s);
        }
    }

    // Написать метод, который определяет, является ли год високосным,
    // и возвращает boolean (високосный - true, не високосный - false).
    // Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.

    private static boolean isLeapYear1(int year) {
       return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
    }

    /**
     *
     * @param year
     * @return
     */
    private static boolean isLeapYear2(int year) {
        return new GregorianCalendar().isLeapYear(year);
    }

}
