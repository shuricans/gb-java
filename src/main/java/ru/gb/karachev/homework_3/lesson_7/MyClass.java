package ru.gb.karachev.homework_3.lesson_7;

import java.util.concurrent.ThreadLocalRandom;

public class MyClass {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("beforeSuite started...");
        randomSleep();
        System.out.println("beforeSuite completed...");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("afterSuite started...");
        randomSleep();
        System.out.println("afterSuite completed...");
    }

    @Test(priority = 1)
    public void method1() {
        System.out.println("method1 started...");
        randomSleep();
        System.out.println("method1 completed...");
    }

    @Test(priority = 2)
    public void method2() {
        System.out.println("method2 started...");
        randomSleep();
        System.out.println("method2 completed...");
    }

    @Test(priority = 2)
    public static void methodStatic2() {
        System.out.println("methodStatic2 started...");
        randomSleep();
        System.out.println("methodStatic2 completed...");
    }

    @Test(priority = 10)
    public void method10() {
        System.out.println("method10 started...");
        randomSleep();
        System.out.println("method10 completed...");
    }

    private static void randomSleep() {
        int millis = ThreadLocalRandom.current().nextInt(500, 1001);
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
