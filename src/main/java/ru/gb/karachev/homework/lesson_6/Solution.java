package ru.gb.karachev.homework.lesson_6;

public class Solution {

    public static void main(String[] args) {

        Animal dogSpike = new Dog("Spike");
        Animal dogPluto = new Dog("Pluto");

        Animal catTom = new Cat("Tom");
        Animal catGarfield = new Cat("Garfield");

        runTest(dogPluto, 159);
        runTest(dogSpike, 600);

        runTest(catTom, 250);
        runTest(catGarfield, 150);

        swimTest(dogSpike, 15);
        swimTest(dogPluto, 9);

        swimTest(catTom, 5);
        swimTest(catGarfield, -5);

        System.out.printf("Amount of animals is %d%n", Animal.getCount());
        System.out.printf("Amount of dogs is %d%n", Dog.getCount());
        System.out.printf("Amount of cats is %d%n", Cat.getCount());

    }

    private static void runTest(Animal animal, int length) {
        String whoIsIt = "";
        if (animal instanceof Cat) {
            whoIsIt = "cat";
        } else if (animal instanceof Dog) {
            whoIsIt = "dog";
        }
        System.out.printf("This is a %s named %s. Can run %d meters.%n",
                whoIsIt, animal.getName(), animal.getLimRun());
        System.out.printf("Given length is %d m.%n", length);
        animal.run(length);
        System.out.println();
    }

    private static void swimTest(Animal animal, int length) {
        String whoIsIt = "";
        if (animal instanceof Cat) {
            whoIsIt = "cat";
        } else if (animal instanceof Dog) {
            whoIsIt = "dog";
        }
        System.out.printf("This is a %s named %s. Can swim %d meters.%n",
                whoIsIt, animal.getName(), animal.getLimSwim());
        System.out.printf("Given length is %d m.%n", length);
        animal.swim(length);
        System.out.println();
    }
}
