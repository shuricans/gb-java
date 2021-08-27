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


    public static void main(String[] args) {

        Box<Apple> appleBox = new Box<>(
                new Apple(),
                new Apple()
        );

        Box<Orange> orangeBox = new Box<>(
                new Orange(),
                new Orange()
        );

        Box<Fruit> fruitBox = new Box<>();
        fruitBox.add(new Apple());
        fruitBox.add(new Orange());


        System.out.println(fruitBox.getWeight());

        System.out.println(appleBox.compare(orangeBox)); // false

        appleBox.add(new Apple());

        System.out.println(appleBox.compare(orangeBox)); // true


        Box<Apple> appleBox2 = new Box<>();
        System.out.println("appleBox weight before transfer = " + appleBox.getWeight());
        System.out.println("appleBox2 weight before transfer = " + appleBox2.getWeight());
        System.out.println();
        appleBox.transfer(appleBox2);
        System.out.println("appleBox weight after transfer = " + appleBox.getWeight());
        System.out.println("appleBox2 weight after transfer = " + appleBox2.getWeight());


        ArrayList<Number> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(3.3f);
        numbers.add(4d);
        numbers.add(1L);

    }
}
