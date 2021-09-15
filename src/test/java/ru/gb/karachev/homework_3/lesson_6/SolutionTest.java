package ru.gb.karachev.homework_3.lesson_6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Simple testing")
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
    void test2(int[] input) {
        assertThrows(RuntimeException.class, () -> {
            solution.getArrayAfterLastNumberFour(input);
        });
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
}