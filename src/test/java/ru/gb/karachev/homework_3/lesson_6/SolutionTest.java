package ru.gb.karachev.homework_3.lesson_6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("My simple testing")
class SolutionTest {

    private final Solution solution = new Solution();

    @ParameterizedTest
    @MethodSource("arraysProvider1")
    @DisplayName("getArrayAfterLastNumberFour - expected correct results")
    public void test1(int[] input, int[] expected) {
        assertArrayEquals(expected, solution.getArrayAfterLastNumberFour(input));
    }

    @ParameterizedTest
    @MethodSource("arraysProvider2")
    @DisplayName("getArrayAfterLastNumberFour - expected RuntimeException")
    public void test2(int[] input) {
        assertThrows(
                RuntimeException.class,
                () -> solution.getArrayAfterLastNumberFour(input));
    }

    @ParameterizedTest
    @MethodSource("arraysProvider3")
    @DisplayName("isContainsNumbersOneOrFour - expected true")
    public void test3(int[] input) {
        assertTrue(solution.isContainsNumbersOneOrFour(input));
    }

    @ParameterizedTest
    @MethodSource("arraysProvider4")
    @DisplayName("isContainsNumbersOneOrFour - expected false")
    public void test4(int[] input) {
        assertFalse(solution.isContainsNumbersOneOrFour(input));
    }

    public static Stream<Arguments> arraysProvider1() {
        return Stream.of(
                Arguments.of(
                        new int[]{4, 2, 3, 4, 5, 6},
                        new int[]{5, 6}
                ),
                Arguments.of(
                        new int[]{1, 2, 3, 4, 5},
                        new int[]{5}
                ),
                Arguments.of(
                        new int[]{1, 2, 3, 4},
                        new int[]{}
                ),
                Arguments.of(
                        new int[]{4, 1, 2, 3},
                        new int[]{1, 2, 3}
                ),
                Arguments.of(
                        new int[]{4},
                        new int[]{}
                )
        );
    }

    public static Stream<Arguments> arraysProvider2() {
        return Stream.of(
                Arguments.of((Object) new int[]{1, 2, 3, -4}),
                Arguments.of((Object) new int[]{})
        );
    }

    public static Stream<Arguments> arraysProvider3() {
        return Stream.of(
                Arguments.of((Object) new int[]{1, 2, 3, -4}),
                Arguments.of((Object) new int[]{1, 2, 3, 4}),
                Arguments.of((Object) new int[]{2, 3, 5, 4}),
                Arguments.of((Object) new int[]{1}),
                Arguments.of((Object) new int[]{4})
        );
    }

    public static Stream<Arguments> arraysProvider4() {
        return Stream.of(
                Arguments.of((Object) new int[]{2, 3, 0}),
                Arguments.of((Object) new int[]{-1, 0, -4}),
                Arguments.of((Object) new int[]{})
        );
    }
}