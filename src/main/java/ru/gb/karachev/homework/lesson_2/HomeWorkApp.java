package ru.gb.karachev.homework.lesson_2;

import java.util.GregorianCalendar;

public class HomeWorkApp {

    private static final GregorianCalendar gC = new GregorianCalendar();

    public static void main(String[] args) {
        /*
        just compare both methods isLeapYear2 & isLeapYear (GregorianCalendar use)
        P.S. since 1582 year
        result out:
        100 200 300 500 600 700 900 1000 1100 1300 1400 1500
        */
        for (int i = 1; i < 3000; i++) {
            if (isLeapYear2(i) != isLeapYear(i)) {
                System.out.printf("%d ", i);
            }
        }
    }

    /* 1. Написать метод, принимающий на вход два целых числа и проверяющий,
    что их сумма лежит в пределах от 10 до 20 (включительно),
    если да – вернуть true, в противном случае – false.*/

    public static boolean inRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    /* 2. Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль,
    положительное ли число передали или отрицательное. Замечание: ноль считаем положительным числом.*/

    public static void printSign(int a) {
        System.out.printf("Число %d: %s%n", a, a < 0 ? "отрицательное" : "положительное");
    }

    /* 3. Написать метод, которому в качестве параметра передается целое число.
    Метод должен вернуть true, если число отрицательное, и вернуть false если положительное.*/

    public static boolean isNegativeNumber(int a) {
        return a < 0;
    }

    /* 4. Написать метод, которому в качестве аргументов передается строка и число,
    метод должен отпечатать в консоль указанную строку, указанное количество раз;*/

    public static void printFewTimes(String s, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(s);
        }
    }

    /* 5. Написать метод, который определяет, является ли год високосным,
    и возвращает boolean (високосный - true, не високосный - false).
    Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.*/

    // Моя реализация, вроде как соответствующая требованям ТЗ
    public static boolean isLeapYear2(int year) {
       return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
    }

    // имхо корректная реализация, т.к. c 1582 год папа Григорий..
    public static boolean isLeapYear(int year) {
        return gC.isLeapYear(year);
    }
}
