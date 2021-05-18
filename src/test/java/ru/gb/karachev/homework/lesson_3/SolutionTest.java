package ru.gb.karachev.homework.lesson_3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Simple testing")
public class SolutionTest {

    private final Solution solution = new Solution();

    @Test
    public void testInverseZeroOne() {
        int[] input    = {1, 1, 0, 0, 1, 0, 1, 1};
        int[] expected = {0, 0, 1, 1, 0, 1, 0, 0};
        solution.inverseZeroOne(input);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testCheckBalance() {
        int[] input = {2, 2, 2, 1, 2, 2, 10, 1};
        assertTrue(solution.checkBalance(input));

        int[] input2 = {8, 69, 32, 66, 24, 99, 32, 68};
        assertTrue(solution.checkBalance(input2));

        int[] input3 = {65, 75, 26, 64, 72, 16, 70, 87, 50, 32, 79};
        assertTrue(solution.checkBalance(input3));

        int[] input4 = {1, -3, 0, 0, 1, 5};
        assertFalse(solution.checkBalance(input4));
    }

    @Test
    public void testOffset() {
        int offset = 1;
        int[] input    = {1, 0};
        int[] expected = {0, 1};
        solution.offset(input, offset);
        assertArrayEquals(expected, input);

        offset = 2;
        int[] input2    = {1, 2, 3};
        int[] expected2 = {2, 3, 1};
        solution.offset(input2, offset);
        assertArrayEquals(expected2, input2);

        offset = -2;
        int[] input3    = {3, 5, 6, 1, 0};
        int[] expected3 = {6, 1, 0, 3, 5};
        solution.offset(input3, offset);
        assertArrayEquals(expected3, input3);

        offset = -10;
        int[] input4    = {3, 5, 6, 1, 0};
        int[] expected4 = {3, 5, 6, 1, 0};
        solution.offset(input3, offset);
        assertArrayEquals(expected3, input3);

        offset = -13;
        int[] input5    = {3, 5, 6, 1};
        int[] expected5 = {5, 6, 1, 3};
        solution.offset(input5, offset);
        assertArrayEquals(expected5, input5);

        offset = 3;
        int[] input6    = {3, 5, 6, 1};
        int[] expected6 = {6, 1, 3, 5};
        solution.offset(input6, offset);
        assertNotEquals(expected6, input6);
    }

}
