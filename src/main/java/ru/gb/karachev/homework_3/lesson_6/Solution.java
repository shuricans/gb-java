package ru.gb.karachev.homework_3.lesson_6;

import java.util.Arrays;

public class Solution {

    /**
     * Return new array after last number four.
     * Array {@code a} must contain at least one number four.
     *
     * @param a one-dimensional array of integers.
     * @return array after last number four.
     * @throws RuntimeException if array {@code a} doesn't contain at least one number four.
     */
    public int[] getArrayAfterLastNumberFour(int[] a) {

        int fromIndex = -1;

        for (int i = a.length - 1; i >= 0; i--) {
            if (a[i] == 4) {
                fromIndex = i + 1;
                break;
            }
        }

        if (fromIndex == -1) {
            throw new RuntimeException("Array must contain at least one number four");
        }

        int[] result = new int[a.length - fromIndex];

        System.arraycopy(a, fromIndex, result, 0, result.length);

        return result;
    }

    /**
     * Checks if the array {@code a} contains at least the number 1 or 4
     *
     * @param a one-dimensional array of integers.
     * @return {@code true} if we found numbers 1 or 4.
     */
    public boolean isContainsNumbersOneOrFour(int[] a) {
        return Arrays.stream(a).anyMatch(x -> x == 1 || x == 4);
    }
}
