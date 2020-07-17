package ru.job4j.stream;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class SchoolTest {
    List<Student> students;

    @Before public void initialize() {
        students = new ArrayList<>();
        students.add(new Student("Petrov", 15));
        students.add(new Student("CleverGuy", 75));
        students.add(new Student("Zamyatin", 30));
        students.add(new Student("Lucky", 50));
        students.add(new Student("Unlucky", 49));
    }

    @Test
    public void whenClassA() {
        List<Student> classA = School.collect(students, x -> x.getScore() >= 70);
        List<Student> expected = List.of(new Student("CleverGuy", 75));
        assertTrue(classA.containsAll(expected));
    }

    @Test
    public void whenClassB() {
        List<Student> classB = School.collect(students, x -> x.getScore() >= 50 && x.getScore() < 70);
        List<Student> expected = List.of(new Student("Lucky", 50));
        assertTrue(classB.containsAll(expected));
    }

    @Test
    public void whenClassV() {
        List<Student> classV = School.collect(students, x -> x.getScore() < 50);
        List<Student> expected = List.of(   new Student("Petrov", 15),
                                            new Student("Zamyatin", 30),
                                            new Student("Unlucky", 49));
        assertTrue(classV.containsAll(expected));
    }
}