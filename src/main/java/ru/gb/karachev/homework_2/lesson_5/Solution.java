package ru.gb.karachev.homework_2.lesson_5;

import java.util.Arrays;
import java.util.concurrent.*;

public class Solution {

    private static final int SIZE = 10_000_000;

    public static void main(String[] args) {
        System.out.println("calc1 (nanoseconds) = " + calc1());
        System.out.println("calc2 (nanoseconds) = " + calc2());
    }

    public static long calc1() {
        float[] arr = getFilledArray(SIZE);

        final long startTime = System.nanoTime();

        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5f) * Math.cos(0.2f + i / 5f) * Math.cos(0.4f + i / 2f));
        }

        return System.nanoTime() - startTime;
    }

    public static long calc2() {

        float[] arr = getFilledArray(SIZE);

        final long startTime = System.nanoTime();

        final int h = SIZE / 2;
        float[] a1 = new float[h];
        float[] a2 = new float[h];

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        final int countThreads = 2;
        final ExecutorService executor = Executors.newFixedThreadPool(countThreads);
        final CountDownLatch countDownLatch = new CountDownLatch(countThreads);

        executor.submit(new Calc(a1, countDownLatch));
        executor.submit(new Calc(a2, countDownLatch));

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }


        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        return System.nanoTime() - startTime;
    }

    private static float[] getFilledArray(int size) {
        float[] arr = new float[size];
        Arrays.fill(arr, 1f);
        return arr;
    }

    static class Calc implements Runnable {

        private final float[] arr;
        private final CountDownLatch countDownLatch;

        public Calc(float[] arr, CountDownLatch countDownLatch) {
            this.arr = arr;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5f) * Math.cos(0.2f + i / 5f) * Math.cos(0.4f + i / 2f));
            }
            countDownLatch.countDown();
        }
    }

}
