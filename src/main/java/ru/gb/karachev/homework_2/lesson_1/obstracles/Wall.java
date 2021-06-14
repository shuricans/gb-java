package ru.gb.karachev.homework_2.lesson_1.obstracles;

import ru.gb.karachev.homework_2.lesson_1.runners.Runner;

public class Wall implements Obstacle {
    private final int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public boolean tryPass(Runner runner) {
        return runner.jump(height);
    }
}
