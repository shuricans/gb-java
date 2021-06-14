package ru.gb.karachev.homework_2.lesson_1.obstracles;

import ru.gb.karachev.homework_2.lesson_1.runners.Runner;

public class Racetrack implements Obstacle {
    private final int length;

    public Racetrack(int length) {
        this.length = length;
    }

    @Override
    public boolean tryPass(Runner runner) {
        return runner.run(length);
    }
}
