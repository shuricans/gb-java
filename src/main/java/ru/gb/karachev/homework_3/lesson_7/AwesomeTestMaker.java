package ru.gb.karachev.homework_3.lesson_7;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.TreeMap;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.concat;
import static java.util.stream.Stream.of;

public class AwesomeTestMaker {

    private final static List<Class<? extends Annotation>> ANNOTATION_CLASS_LIST
            = List.of(Test.class, BeforeSuite.class, AfterSuite.class);

    /**
     * See {@link #test(Class)}
     *
     * @param clazz the {@code Class} object.
     */
    public static void start(Class<?> clazz) {
        test(clazz);
    }

    /**
     * See {@link #test(Class)}
     *
     * @param className the fully qualified name of the desired class.
     */
    public static void start(String className) {
        try {
            test(Class.forName(className));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * First, it calls the method annotated {@code @BeforeSuite}, if any.
     * Second, it calls all the method annotated {@code @Test} in priority order from 1 to 10.
     * Last, it calls the method annotated {@code @AfterSuite}, if any.
     *
     * @param clazz the {@code Class} object.
     * @throws RuntimeException if failed to create an instance of {@code clazz}.
     *                          <p>
     *                          If method have more than one annotation
     *                          declared in {@code ANNOTATION_CLASS_LIST}
     *                          (See {@link #checkInvalidCombinationOfAnnotations(Annotation[])}).
     *                          <p>
     *                          If we have more than one methods
     *                          with annotations {@code @AfterSuite} or {@code @BeforeSuite}.
     */
    private static void test(Class<?> clazz) {

        final TreeMap<Integer, List<Method>> testMethods = new TreeMap<>();

        Method beforeSuite = null;
        Method afterSuite = null;

        for (Method method : clazz.getDeclaredMethods()) {

            final Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
            checkInvalidCombinationOfAnnotations(declaredAnnotations);

            // try get Test annotation
            final Test testAnnotation = method.getAnnotation(Test.class);
            if (testAnnotation != null) {
                int priority = testAnnotation.priority();
                // check range 1..10
                if (priority < 1) {
                    priority = 1;
                } else if (priority > 10) {
                    priority = 10;
                }
                testMethods.merge(priority, List.of(method),
                        (l1, l2) -> concat(l1.stream(), of(method)).collect(toList()));

                continue;
            }

            // try get BeforeSuite annotation
            final BeforeSuite beforeSuiteAnnotation = method.getAnnotation(BeforeSuite.class);
            if (beforeSuiteAnnotation != null) {
                if (beforeSuite == null) {
                    beforeSuite = method;
                } else {
                    throw new RuntimeException(
                            String.format(
                                    "Annotation - [%s] can be used no more than once.%n",
                                    BeforeSuite.class.getSimpleName())
                    );
                }
                continue;
            }

            // try get AfterSuite annotation
            final AfterSuite afterSuiteAnnotation = method.getAnnotation(AfterSuite.class);
            if (afterSuiteAnnotation != null) {
                if (afterSuite == null) {
                    afterSuite = method;
                } else {
                    throw new RuntimeException(
                            String.format(
                                    "Annotation - [%s] can be used no more than once.%n",
                                    AfterSuite.class.getSimpleName())
                    );
                }
            }
        }

        // create instance and invoke all methods
        try {
            final Object object = clazz.getDeclaredConstructor().newInstance();

            if (beforeSuite != null) {
                beforeSuite.setAccessible(true);
                beforeSuite.invoke(object);
            }

            testMethods.values()
                    .forEach(methods -> methods.forEach(method -> {
                        try {
                            method.setAccessible(true);
                            method.invoke(object);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }));

            if (afterSuite != null) {
                afterSuite.setAccessible(true);
                afterSuite.invoke(object);
            }

        } catch (InstantiationException | IllegalAccessException |
                InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException("Can`t create instance of class " + clazz.getSimpleName());
        }
    }

    /**
     * All fine when array contains zero or one annotation from {@code ANNOTATION_CLASS_LIST},
     * otherwise get a {@code RuntimeException}.
     *
     * @param annotations array with annotations.
     * @throws RuntimeException when the array contains more than one annotation
     *                          declared in {@code ANNOTATION_CLASS_LIST}, also creates a message with an invalid combination.
     */
    private static void checkInvalidCombinationOfAnnotations(Annotation[] annotations) {

        if (annotations.length <= 1) {
            return;
        }

        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (Annotation a : annotations) {
            final Class<? extends Annotation> aClass = a.annotationType();
            if (ANNOTATION_CLASS_LIST.contains(aClass)) {
                count++;
                sb.append(aClass.getSimpleName());
                if (count > 1) {
                    throw new RuntimeException(String.format("Invalid combination of annotations [%s]", sb));
                } else {
                    sb.append(", ");
                }
            }
        }
    }

}
