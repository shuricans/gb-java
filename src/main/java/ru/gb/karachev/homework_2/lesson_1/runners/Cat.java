package ru.gb.karachev.homework_2.lesson_1.runners;

public class Cat implements Runner {

    private final String name;
    private final int limRun;
    private final int limJump;

    public Cat(String name, int limRun, int limJump) {
        this.name = name;
        this.limRun = limRun;
        this.limJump = limJump;
    }

    @Override
    public boolean run(int barrier) {
        if (limRun >= barrier) {
            System.out.println(this + " run successfully.");
            return true;
        }
        System.out.println(this + " run fail.");
        return false;
    }

    @Override
    public boolean jump(int barrier) {
        if (limJump >= barrier) {
            System.out.println(this + " jumped successfully.");
            return true;
        }
        System.out.println(this + " jumped fail.");
        return false;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                '}';
    }
}
