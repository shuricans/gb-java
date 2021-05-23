package ru.gb.karachev.homework.lesson_4;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import com.diogonunes.jcolor.*;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;

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

    private static final Attribute[] FIRST_FORMAT =
            new Attribute[]{BLACK_TEXT(), BACK_COLOR(205, 193, 180), BOLD()};
    private static final Attribute[] SECOND_FORMAT =
            new Attribute[]{BLACK_TEXT(), BACK_COLOR(187, 173, 160), BOLD()};

    private static final AnsiFormat INFO_FORMAT = new AnsiFormat(CYAN_TEXT());
    private static final AnsiFormat ERROR_FORMAT = new AnsiFormat(RED_TEXT());
    private static final AnsiFormat WIN_FORMAT = new AnsiFormat(GREEN_TEXT());

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int SIZE = 4;
    private static final int WIN_TILE = 2048;

    private static final int EMPTY = 0;

    public static void main(String[] args) {
        play();
    }

    public static void play() {

        final int[][] freeCells = new int[2][SIZE * SIZE]; // assist array for calc empty cells
        final int[][] field = new int[SIZE][SIZE]; // main field, play-board
        // on start, add two values on field
        addNewValue(field, freeCells);
        addNewValue(field, freeCells);

        final int[][] fieldBeforeMove = new int[SIZE][SIZE]; // assist field
        final int[] line = new int[SIZE]; // assist array

        int score = 0;
        boolean alreadyWin = false;
        Direction direction;

        System.out.printf(colorize("Join the tiles, get to %d!%n", INFO_FORMAT), WIN_TILE);

        do {
            draw(field, score);
            copyCurrentFiledState(field, fieldBeforeMove);

            if (checkWinTile(field) && !alreadyWin) {
                System.out.printf(colorize("Got %d - DAMN, your good!%n", WIN_FORMAT), WIN_TILE);
                System.out.println(colorize("Let's go! We need MORE SCORE!!!", WIN_FORMAT));
                alreadyWin = true;
            }

            direction = askDirection();
            score += move(field, direction, line);

            if (!compareFields(fieldBeforeMove, field)) {
                addNewValue(field, freeCells);
            }
        } while (canMove(field));

        draw(field, score);
        System.out.println(colorize("game over...", ERROR_FORMAT));
    }

    /**
     *
     * @param field - given two dimensional array (play-board).
     * @return <code>true</code> if we found <code>WIN_TILE</code>
     */
    private static boolean checkWinTile(int[][] field) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(field[i][j] == WIN_TILE) {
                    return true;
                }
            }
        }
        return false;
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
     * @param field - given two dimensional array (play-board).
     * @param freeCells - array for storage empty cells coordinates.
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

    /**
     * Move all zeroes to end of array.
     *
     * @param line - actually row or column from main play field.
     */
    private static void pushZerosToEnd(int[] line) {
        int count = 0;  // Count of non-zero elements

        for (int i = 0; i < line.length; i++) {
            if (line[i] != EMPTY) {
                line[count] = line[i];
                count++;
            }
        }

        while (count < line.length) {
            line[count++] = 0;
        }
    }

    /**
     * Just fill two dimensional array by zeros.
     *
     * @param array assist array for calc empty cells
     */
    private static void clearArrayWithFreeCells(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = 0;
            }
        }
    }

    /**
     * Shift all rows or columns in the specified direction
     *
     * @param field - given two dimensional array (play-board).
     * @param direction - direction move
     * @param line - actually row or column from main play field.
     * @return score - all sums after this move
     */
    private static int move(int[][] field, Direction direction, int[] line) {
        int score = 0;
        for (int i = 0; i < SIZE; i++) {
            int k = 0;
            for (int j = 0; j < SIZE; j++) {
                switch (direction) {
                    case LEFT:
                        if (field[i][j] != EMPTY) {
                            line[k] = field[i][j];
                            k++;
                        }
                        break;
                    case RIGHT:
                        if (field[i][SIZE - (j + 1)] != EMPTY) {
                            line[k] = field[i][SIZE - (j + 1)];
                            k++;
                        }
                        break;
                    case UP:
                        if (field[j][i] != EMPTY) {
                            line[k] = field[j][i];
                            k++;
                        }
                        break;
                    case DOWN:
                        if (field[SIZE - (j + 1)][i] != EMPTY) {
                            line[k] = field[SIZE - (j + 1)][i];
                            k++;
                        }
                }
            }
            score += calculate(line, direction);
            for (int j = 0; j < SIZE; j++) {
                switch (direction) {
                    case LEFT:
                    case RIGHT:
                        field[i][j] = line[j];
                        break;
                    case UP:
                    case DOWN:
                        field[j][i] = line[j];
                }
            }
            Arrays.fill(line, 0);
        }
        return score;
    }

    private static int calculate(int[] line, Direction direction) {
        int score = 0;
        score += sumAllPairs(line);
        pushZerosToEnd(line);

        if (direction == Direction.RIGHT || direction == Direction.DOWN) {
            reverseLine(line);
        }

        return score;
    }

    private static int sumAllPairs(int[] line) {
        int score = 0;
        int prev = line[0]; // get first value

        if (prev == EMPTY) { // means empty line;
            return score;
        }

        for (int i = 1; i < line.length; i++) {
            if (line[i] == prev && line[i] != EMPTY) {
                line[i - 1] *= 2;
                score += line[i - 1];
                line[i] = EMPTY;
            }
            prev = line[i];
        }
        return score;
    }

    private static void draw(int[][] field, int score) {
        Attribute[] color;
        System.out.printf("Score: %4d%n", score);

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                color = (j + i) % 2 == 0 ? FIRST_FORMAT : SECOND_FORMAT;
                if (field[i][j] != EMPTY) {
                    System.out.printf(colorize("%4d ", color), field[i][j]);
                } else {
                    System.out.print(colorize("     ", color));
                }
            }
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

    private static boolean compareFields(int[][] a, int[][] b) {
        for (int i = 0; i < SIZE; i++) {
            if (!Arrays.equals(a[i], b[i]))
                return false;
        }
        return true;
    }

    private static void copyCurrentFiledState(int[][] field, int[][] fieldBeforeMove) {
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(field[i], 0, fieldBeforeMove[i], 0, SIZE);
        }
    }
}

enum Direction {
    RIGHT, LEFT, UP, DOWN
}