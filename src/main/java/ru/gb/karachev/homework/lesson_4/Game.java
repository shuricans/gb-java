package ru.gb.karachev.homework.lesson_4;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 2048 (4 x 4)
 * https://play2048.co/
 * <p>
 * 1. В каждом раунде появляется плитка номинала «2» (с вероятностью 90 %) или «4» (с вероятностью 10 %)
 * <p>
 * 2. Нажатием стрелки игрок может скинуть все плитки игрового поля в одну из 4 сторон.
 * Если при сбрасывании две плитки одного номинала «налетают» одна на другую, то они превращаются в одну,
 * номинал которой равен сумме соединившихся плиток.
 * После каждого хода на свободной секции поля появляется новая плитка номиналом «2» или «4».
 * Если при нажатии кнопки местоположение плиток или их номинал не изменится, то ход не совершается.
 * <p>
 * 3. Если в одной строчке или в одном столбце находится более двух плиток одного номинала,
 * то при сбрасывании они начинают соединяться с той стороны, в которую были направлены.
 * Например, находящиеся в одной строке плитки (4, 4, 4)
 * после хода влево превратятся в (8, 4),
 * а после хода вправо — в (4, 8).
 * Данная обработка неоднозначности позволяет более точно формировать стратегию игры.
 * За каждое соединение игровые очки увеличиваются на номинал получившейся плитки.
 * <p>
 * 4. Игра заканчивается поражением, если после очередного хода невозможно совершить действие.
 */
public class Game {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int SIZE = 4;
    private static final int WIN_SCORE = 2048;

    private static final int EMPTY = 0;

    public static void main(String[] args) {

//        final boolean isGameOver = play();
//        if(isGameOver) {
//            System.out.println("game over...");
//        }

//        int[][] arr = {
//                {0, 4, 8, 4},
//                {4, 2, 16, 32},
//                {32, 128, 64, 2},
//                {4, 2, 8, 16}
//        };
//
        int[] a = {2, 2, 2, 8};


        Direction direction = Direction.LEFT;
        System.out.println(Arrays.toString(a));
        calculate(a, direction);
        System.out.println(Arrays.toString(a));

    }

    public static boolean play() {

        int[][] freeCells = new int[2][SIZE * SIZE]; // storage for empty cells
        int[] line = new int[SIZE];
        int[][] field = generateField(SIZE, freeCells);
        int score = 0;
        Direction direction;

        while (canMove(field)) {
            draw(field, score);
            direction = askDirection();
            score += move(field, direction, line);
            addNewValue(field, freeCells);

            if (score == WIN_SCORE) {
                System.out.println(score + " - DAMN, your good!");
                System.out.println("Let's go! We need MORE SCORE!!!");
            }
        }
        //TODO Call draw() here, it's necessary?
        //mb do while
        draw(field, score); // last draw?
        return true; // means game over
    }

    private static Direction askDirection() {
        while (true) {
            System.out.print("WASD: ");
            switch (SCANNER.nextLine().trim().toUpperCase()) {
                case "W":
                    return Direction.UP;
                case "A":
                    return Direction.LEFT;
                case "S":
                    return Direction.DOWN;
                case "D":
                    return Direction.RIGHT;
                default:
                    System.out.println("invalid move, try again.");
            }
        }
    }

    private static int[][] generateField(int size, int[][] freeCells) {
        int[][] field = new int[size][size];
        addNewValue(field, freeCells);
        addNewValue(field, freeCells);
        return field;
    }

    private static boolean canMove(int[][] field) {

        // if we found empty cell, we can move
        for (int[] inner : field) {
            for (int val : inner) {
                if (val == EMPTY) {
                    return true;
                }
            }
        }

        // check horizontal
        // {2, 2, 4, 16} true
        // {32, 2, 4, 16} false
        int prev;
        for (int i = 0; i < SIZE; i++) {
            prev = field[i][0];
            for (int j = 1; j < SIZE; j++) {
                if (field[i][j] == prev) {
                    return true;
                }
                prev = field[i][j];
            }
        }

        // check vertical > same logic (check horizontal)
        for (int i = 0; i < SIZE; i++) {
            prev = field[0][i];
            for (int j = 1; j < SIZE; j++) {
                if (field[j][i] == prev) {
                    return true;
                }
                prev = field[j][i];
            }
        }

        return false;
    }

    /**
     * Add new value on field if it's possible.
     *
     * @param field
     * @param freeCells
     */
    private static void addNewValue(int[][] field, int[][] freeCells) {
        clearArrayWithFreeCells(freeCells);
        int k = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (field[i][j] == EMPTY) {
                    freeCells[0][k] = i;
                    freeCells[1][k] = j;
                    k++;
                }
            }
        }
        if (k > 0) {
            int index = ThreadLocalRandom.current().nextInt(k);
            field[freeCells[0][index]][freeCells[1][index]] = getNextValue();
        }
    }

    private static void clearArrayWithFreeCells(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = 0;
            }
        }
    }

    private static int move(int[][] field, Direction direction, int[] line) {
        int score = 0;

        int k;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                k = 0;
                switch (direction) {
                    case LEFT:
                    case RIGHT:
                        if(field[i][j] != EMPTY) {
                            line[k] = field[i][j];
                            k++;
                        }
                        break;
                    case DOWN:
                    case UP:
                        if(field[j][i] != EMPTY) {
                            line[k] = field[j][i];
                            k++;
                        }
                }
                calculate(line, direction);
//                for (int l = 0; l < SIZE; l++) {
//                    field
//                }
            }
        }

        return score;
    }

    private static void calculate(int[] line, Direction direction) {

        switch (direction) {
            case RIGHT:
            case DOWN:
                reverseLine(line);
        }

        int prev = line[0]; // get first value

        if(prev == 0) {
            return;
        }

        for (int i = 1; i < line.length; i++) {
            if (line[i] == 0) {
                break;
            }
            if (prev == 0) {
                line[i - 1] = line[i];
                line[i] = 0;
            }
            if (prev == line[i] && prev != 0) {
                line[i - 1] *= 2;
                line[i] = 0;
            }
            prev = line[i];
        }

        switch (direction) {
            case RIGHT:
            case DOWN:
                reverseLine(line);
        }
    }


    private static void draw(int[][] field, int score) {
        System.out.printf("Score: %4d%n%n", score);

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("  %4d", field[i][j]);
            }
            System.out.println();
            System.out.println();
        }
    }

    /**
     * @return 2 (chance 90 %), otherwise 4
     */
    private static int getNextValue() {
        return ThreadLocalRandom.current().nextInt(10) > 0 ? 2 : 4;
    }

    private static void reverseLine(int[] line) {
        for (int i = 0; i < line.length / 2; i++) {
            int temp = line[i];
            line[i] = line[line.length - i - 1];
            line[line.length - i - 1] = temp;
        }
    }
}

enum Direction {
    RIGHT, LEFT, UP, DOWN;
}