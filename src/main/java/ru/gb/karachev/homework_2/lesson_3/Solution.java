package ru.gb.karachev.homework_2.lesson_3;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) {

        /* 1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
        Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
        Посчитать, сколько раз встречается каждое слово.
         */

        String[] words = {"apple", "banana", "pear", "cherry",
                "melon", "apple", "orange", "peach",
                "pear", "passion fruit", "mango", "potato", "apple"};

        Map<String, Integer> uniqueWords = new TreeMap<>();

        Arrays.stream(words).forEach(word -> uniqueWords.merge(word, 1, Integer::sum));

        uniqueWords.forEach((k, v) -> System.out.println(k + ": " + v));


        System.out.println();
        System.out.println();

        /* 2. Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и телефонных номеров.
        В этот телефонный справочник с помощью метода add() можно добавлять записи,
        а с помощью метода get() искать номер телефона по фамилии.
        Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
        тогда при запросе такой фамилии должны выводиться все телефоны.
        Желательно не добавлять лишний функционал (дополнительные поля (имя, отчество, адрес),
        взаимодействие с пользователем через консоль и т.д).
        Консоль использовать только для вывода результатов проверки телефонного справочника.
         */

        Phonebook.add("Karachev", "9991234567");
        Phonebook.add("Karachev", "9991234567");
        Phonebook.add("Karachev", "9999876543");

        Phonebook.add("Pupkin", "9210001122");
        Phonebook.add("Pupkin", "9211231122");

        Phonebook.add("Vovochkin", "9520001010");

        System.out.println("Search by \"Karachev\"");
        Phonebook.get("Karachev").forEach(System.out::println);
        System.out.println();

        System.out.println("Search by \"Pupkin\"");
        Phonebook.get("Pupkin").forEach(System.out::println);
        System.out.println();

        System.out.println("Search by \"Vovochkin\"");
        Phonebook.get("Vovochkin").forEach(System.out::println);
    }
}
