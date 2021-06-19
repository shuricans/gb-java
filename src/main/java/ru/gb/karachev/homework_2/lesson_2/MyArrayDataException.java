package ru.gb.karachev.homework_2.lesson_2;

public class MyArrayDataException extends RuntimeException {

    public MyArrayDataException(int i, int j, Exception exception) {
        super(String.format("The array[%d][%d] cell has invalid data - [%s]", i, j, exception.getMessage()));
    }
}
