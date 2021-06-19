package ru.gb.karachev.homework_2.lesson_2;

public class MyArraySizeException extends RuntimeException {

    public MyArraySizeException(int size) {
        super(String.format("The size of a two-dimensional array must be %d by %d.", size, size));
    }
}
