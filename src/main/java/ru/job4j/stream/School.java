package ru.job4j.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class School {
    public List<Student> collect(List<Student> students, Predicate<Student> predict) {
        List<Student> rsl = new ArrayList<>();
        for (Student s: students) {
            if (predict.test(s)) {
                rsl.add(s);
            }
        }
        return rsl;
    }
}
