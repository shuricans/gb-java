package ru.gb.karachev.homework_2.lesson_1.obstracles;

import ru.gb.karachev.homework_2.lesson_1.runners.Runner;

public interface Obstacle {
    boolean tryPass(Runner runner);
}
