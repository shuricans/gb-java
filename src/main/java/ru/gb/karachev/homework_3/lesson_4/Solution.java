package ru.gb.karachev.homework_3.lesson_4;

import java.util.concurrent.atomic.AtomicInteger;

/* 1.   Создать три потока, каждый из которых выводит определенную букву (A, B и C)
        5 раз (порядок – ABСABСABС). Используйте wait/notify/notifyAll.
 */

public class Solution {
    private static final char[] sequence = {'A', 'B', 'C'};
    private static final AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        new Thread(new PrintLetterFiveTimes(sequence, 0, counter)).start();
        new Thread(new PrintLetterFiveTimes(sequence, 1, counter)).start();
        new Thread(new PrintLetterFiveTimes(sequence, 2, counter)).start();
    }

    static class PrintLetterFiveTimes implements Runnable {

        private final char[] sequence;
        private final AtomicInteger counter;
        private final int index;

        PrintLetterFiveTimes(char[] sequence, int index, AtomicInteger counter) {
            this.sequence = sequence;
            this.index = index;
            this.counter = counter;
        }

        @Override
        public void run() {
            synchronized (sequence) {
                try {
                    for (int i = 0; i < 5; i++) {
                        while (counter.get() != index) {
                            sequence.wait();
                        }
                        System.out.print(sequence[index]);
                        if (counter.get() == sequence.length - 1) {
                            counter.set(0);
                        } else {
                            counter.incrementAndGet();
                        }
                        sequence.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
