package ru.gb.karachev.homework_3.lesson_1;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    /*
    1. Написать метод, который меняет два элемента массива местами.
    (массив может быть любого ссылочного типа);
     */
    private static <T> void swap(T[] a, int firstIndex, int secondIndex) {
        T tmp = a[firstIndex];
        a[firstIndex] = a[secondIndex];
        a[secondIndex] = tmp;
    }

    /*
    2. Написать метод, который преобразует массив в ArrayList;
     */
    @SafeVarargs
    private static <T> List<T> asArrayList(T... a) {
        List<T> list = new ArrayList<>();
        Collections.addAll(list, a);
        return list;
    }

}
