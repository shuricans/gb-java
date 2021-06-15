package ru.gb.karachev.homework_2.lesson_2;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {

        String[][] arr = {
                {"1", "1", "1", "1"},
                {"1", "1", "1", "1"},
                {"1", "1", "one", "1"},
                {"1", "1", "1", "1"}};

        try {
            System.out.println("sum = " + getSum(arr));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

        String[][] numbers = {
                {"1", "1", "1", "1"},
                {"1", "1", "1", "1"},
                {"1", "1", "1", "1"},
                {"1", "1", "1", "1"}};

        try {
            System.out.println("sum = " + getSum(numbers));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return sum of all numbers in two-dimensional array of strings representing numbers.
     *
     * @param numbers - two-dimensional array of strings representing numbers.
     * @return sum of all numbers.
     * @throws MyArraySizeException if the size of {@code numbers} not equals {@code size} by {@code size}
     * @throws MyArrayDataException if any of value is not number actually.
     */
    public static int getSum(String[][] numbers) throws MyArraySizeException, MyArrayDataException {

        boolean invalidSize = false;
        int size = 4;
        if (numbers.length == size) {
            for (int i = 0; i < size; i++) {
                if (numbers[i].length != size) {
                    invalidSize = true;
                    break;
                }
            }
        } else {
            invalidSize = true;
        }

        if (invalidSize) {
            throw new MyArraySizeException(size);
        }


        int sum = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                try {
                    sum += Integer.parseInt(numbers[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j, e);
                }
            }
        }
        return sum;
    }
}
