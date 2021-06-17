package ru.gb.karachev.homework_2.lesson_1;

import ru.gb.karachev.homework_2.lesson_1.runners.Runner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Team {
    private final String name;
    private final Runner[] runners;
    private final Map<Runner, Boolean> results;

    public Team(String name, Runner... runners) {
        this.name = name;
        this.runners = runners;
        this.results = new HashMap<>();
    }

    public void printPassedRunners() {
        if(!results.isEmpty()) {
            System.out.printf("Team - \"%s\" - winners:%n", name);
            results.entrySet()
                    .stream()
                    .filter(Map.Entry::getValue)
                    .forEach(entry -> System.out.println(entry.getKey()));
        } else {
            System.out.printf("Team - \"%s\" - all runners failed:%n", name);
        }
    }

    public void printAllRunners() {
        System.out.printf("Team \"%s\" - all runners list:%n", name);
        Arrays.stream(runners).forEach(System.out::println);
    }

    public String getName() {
        return name;
    }

    public Runner[] getRunners() {
        return runners;
    }

    public Map<Runner, Boolean> getResults() {
        return results;
    }

    public void resetResults() {
        results.clear();
    }
}
