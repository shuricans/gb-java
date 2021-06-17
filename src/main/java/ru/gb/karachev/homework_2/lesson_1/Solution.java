package ru.gb.karachev.homework_2.lesson_1;

import ru.gb.karachev.homework_2.lesson_1.obstracles.Racetrack;
import ru.gb.karachev.homework_2.lesson_1.obstracles.Wall;
import ru.gb.karachev.homework_2.lesson_1.runners.Cat;
import ru.gb.karachev.homework_2.lesson_1.runners.Human;
import ru.gb.karachev.homework_2.lesson_1.runners.Robot;

public class Solution {
    public static void main(String[] args) {

        Course course = new Course(
                new Wall(10),
                new Racetrack(100),
                new Wall(15),
                new Racetrack(150),
                new Wall(20),
                new Racetrack(200)
        );

        Team losers = new Team("Losers",
                new Human("Dmitry Rogozin", 170, 20),
                new Robot("Fedor", 10, 150),
                new Cat("Tom", 200, 20)
        );

        System.out.println("*****************************************************************");
        losers.printAllRunners();
        System.out.println("*****************************************************************");
        System.out.println("Course starting...");
        course.doIt(losers);
        System.out.println("Course end...");
        System.out.println("*****************************************************************");
        losers.printPassedRunners();

    }
}
