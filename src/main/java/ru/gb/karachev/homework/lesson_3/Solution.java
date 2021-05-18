package ru.gb.karachev.homework.lesson_3;

import java.util.Arrays;
import java.util.Random;

public class Solution {

    final static Random random = new Random();

    public static void main(String[] args) {

        Solution solution = new Solution();

        /* 1. Задать целочисленный массив, состоящий из элементов 0 и 1.
        Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
        С помощью цикла и условия заменить 0 на 1, 1 на 0;
         */
        System.out.println("1. task");
        int[] a = solution.getRandomArray(10, 2);
        System.out.println("Before:\t" + Arrays.toString(a));
        solution.inverseZeroOne(a);
        System.out.println("After:\t" + Arrays.toString(a));
        System.out.println();

        /* 2. Задать пустой целочисленный массив длиной 100.
        С помощью цикла заполнить его значениями 1 2 3 4 5 6 7 8 … 100;
         */
        System.out.println("2. task");
        int[] b = new int[100];

        for (short i = 0; i < 100; i++) {
            b[i] = i + 1;
        }

        System.out.println(Arrays.toString(b));
        System.out.println();

        /* 3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ]
        пройти по нему циклом, и числа меньшие 6 умножить на 2;
         */
        System.out.println("3. task");
        int[] c = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        System.out.println("Before:\t" + Arrays.toString(c));

        for (int i = 0; i < c.length; i++) {
            if(c[i] < 6) {
                c[i] *= 2;
            }
        }

        System.out.println("After:\t" + Arrays.toString(c));
        System.out.println();

        /* 4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
        и с помощью цикла(-ов) заполнить его диагональные элементы единицами (можно только одну из диагоналей,
        если обе сложно). Определить элементы одной из диагоналей можно по следующему принципу:
        индексы таких элементов равны, то есть [0][0], [1][1], [2][2], …, [n][n];
         */
        System.out.println("4. task");
        int size = 10;
        int[][] matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(i == j) {
                    matrix[i][j] = 1;
                    matrix[size - (i + 1)][j] = 1;
                }
            }
        }

        solution.printMatrix(matrix);
        System.out.println();

        /* 5. Написать метод, принимающий на вход два аргумента: len и initialValue,
        и возвращающий одномерный массив типа int длиной len, каждая ячейка которого равна initialValue;
         */
        System.out.println("5. task");
        int[] d = solution.getFilledArray(5,5);
        System.out.println(Arrays.toString(d));
        System.out.println();

        /* 6. Задать одномерный массив и найти в нем минимальный и максимальный элементы
         */
        System.out.println("6. task");
        int[] e = solution.getRandomArray(10, 100);
        int min = e[0], max = e[0];

        for (int i = 1; i < e.length; i++) {
            if(e[i] < min) {
                min = e[i];
            } else if(e[i] > max) {
                max = e[i];
            }
        }

        System.out.println(Arrays.toString(e));
        System.out.printf("max = %d%nmin = %d%n", max, min);
        System.out.println();

        /* 7. Написать метод, в который передается не пустой одномерный целочисленный массив,
        метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны.
        Примеры:
        checkBalance([2, 2, 2, 1, 2, 2, ||| 10, 1]) → true, т.е. 2 + 2 + 2 + 1 + 2 + 2 = 10 + 1
        checkBalance([1, 1, 1, ||| 2, 1]) → true, т.е. 1 + 1 + 1 = 2 + 1
        граница показана символами |||, эти символы в массив не входят и не имеют никакого отношения к ИЛИ.
         */
        System.out.println("7. task");
        int[] f = {2, 2, 2, 1, 2, 2, 10, 1};
        System.out.println("Is balanced array?: " + Arrays.toString(f));
        System.out.println(solution.checkBalance(f));
        System.out.println();

        /* 8. Написать метод, которому на вход подается
        одномерный массив и число n (может быть положительным, или отрицательным),
        при этом метод должен сместить все элементы массива на n позиций.
        Элементы смещаются циклично.
        Для усложнения задачи нельзя пользоваться вспомогательными массивами.
        Примеры: [ 1, 2, 3 ] при n = 1 (на один вправо) -> [ 3, 1, 2 ];
        [ 3, 5, 6, 1] при n = -2 (на два влево) -> [ 6, 1, 3, 5 ].
        При каком n в какую сторону сдвиг можете выбирать сами.
         */
        System.out.println("8. task");
        int offset = 2;
        int[] input2    = {1, 2, 3};
        System.out.println(Arrays.toString(input2));
        solution.offset(input2, offset); // [2, 3, 1] expected
        System.out.println(Arrays.toString(input2));

    }

    public int[] getRandomArray(int length, int bound) {
        if(length < 0) {
            throw new IllegalArgumentException();
        }

        final int[] result = new int[length];

        for(int i = 0; i < result.length; i++) {
            result[i] = random.nextInt(bound);
        }

        return result;
    }

    public void inverseZeroOne(int[] binaryArray) {
        for (int i = 0; i < binaryArray.length; i++) {
            binaryArray[i] = binaryArray[i] == 1 ? 0 : 1;
        }
    }

    public void printMatrix(int[][] a) {
        for (int[] ints : a) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.printf("%d  ", ints[j]);
            }
            System.out.println();
        }
    }

    public int[] getFilledArray(int len, int initialValue) {
        int[] a = new int[len];
        Arrays.fill(a, initialValue);
        return a;
    }

    public boolean checkBalance(int[] a) {
        if(a.length < 2) return false;
        int sumL = 0, sumR = 0;
        for (int i = 0; i < a.length; i++) {
            sumL += a[i];
            if(i + 1 < a.length) {
                for (int j = i + 1; j < a.length; j++) {
                    sumR += a[j];
                    if(sumL == sumR && j + 1 == a.length) {
                        return true;
                    }
                }
                sumR = 0;
            }
        }
        return false;
    }

    public void offset(int[] a, int offset) {

        /* remove redundant offset if absolute value of offset more or equal array length
        1) length = 3 and offset = (+/-)3 > offset = 0 > it is a given array
        2) length = 4 and offset = (+/-)5 > offset = (+/-)1
        3) positive offset = length + negative offset
         */
        int length = a.length;

        // remove loops
        if(Math.abs(offset) > length) {
            offset %= length;
        }

        // get equivalent positive offset
        if(offset < 0) {
            offset += length;
        }

        // return if offset is unnecessary
        if(length <= 1 || offset == length || offset == 0) {
            return;
        }

        int j = 0;
        int temp = a[0];
        for (int i = 0; i < length; i++) {
            if(j + offset < length) {
                j += offset;
            } else {
                j += offset - length;
            }
            a[j] = temp + a[j];
            temp = a[j] - temp;
            a[j] = a[j] - temp;
        }
    }
}
