package ru.gb.karachev.homework_2.lesson_1;

import ru.gb.karachev.homework_2.lesson_1.obstracles.Obstacle;
import ru.gb.karachev.homework_2.lesson_1.runners.Runner;

import java.util.Map;

public class Course {
    private final Obstacle[] obstacles;

    public Course(Obstacle... obstacles) {
        this.obstacles = obstacles;
    }

    public void doIt(Team team) {
        team.resetResults();
        Map<Runner, Boolean> results = team.getResults();
        for(Runner runner : team.getRunners()) {
            for(Obstacle obstacle : obstacles) {
                if(results.get(runner) == null || results.get(runner)) {
                    results.put(runner, obstacle.tryPass(runner));
                }
            }
        }
    }
}
