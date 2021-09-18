package ru.gb.karachev.homework_3.lesson_5;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;

public class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    private final CyclicBarrier barrier;
    private final Lock winnerLock;
    public Car(Race race, int speed, CyclicBarrier barrier, Lock winnerLock) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.barrier = barrier;
        this.winnerLock = winnerLock;
    }
    @Override
    public void run() {
        boolean isWinnerLockAcquired = false;
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");

            barrier.await(); // waiting when all get ready

            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }

            isWinnerLockAcquired = winnerLock.tryLock(); // thread who acquired lock is winner
            if (isWinnerLockAcquired) {
                System.out.println(this.name + " - WIN");
            }

            barrier.await(); // waiting for everyone
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (isWinnerLockAcquired) {
                winnerLock.unlock();
            }
        }
    }
}
