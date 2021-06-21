package ru.gb.karachev.homework_2.lesson_3;

import java.util.*;

import static java.util.stream.Stream.*;
import static java.util.stream.Collectors.*;

public class Phonebook {

    private static final Map<String, Set<String>> book = new HashMap<>();

    public static Set<String> get(String lastName) {
        return book.get(lastName);
    }

    public static void add(String lastName, String phone) {
        book.merge(lastName, Set.of(phone),
                (set1, set2) -> concat(set1.stream(), of(phone)).collect(toSet()));
    }
}
