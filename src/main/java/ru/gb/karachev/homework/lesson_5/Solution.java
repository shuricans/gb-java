package ru.gb.karachev.homework.lesson_5;

import java.util.concurrent.ThreadLocalRandom;

public class Solution {

    public static void main(String[] args) {

        Employee[] employees = new Employee[5];

        for (int i = 0; i < employees.length; i++) {
            employees[i] = new Employee(
                    "Pupkin_" + i,
                    "Driver_" + i,
                    "pupkin_" + i + "@gb.ru",
                    "900123456" + i,
                    ThreadLocalRandom.current().nextInt(70000, 120001),
                    ThreadLocalRandom.current().nextInt(20, 51)
            );
        }

        for (Employee e : employees) {
            if (e.getAge().compareTo(40) >= 0) {
                System.out.println(e);
            }
        }

    }
}
