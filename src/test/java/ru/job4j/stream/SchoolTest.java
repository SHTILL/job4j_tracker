package ru.job4j.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class SchoolTest {
    @Test
    public void formClasses(){
        List<Student> students = new ArrayList<>();
        students.add(new Student("Petrov", 15));
        students.add(new Student("CleverGuy", 75));
        students.add(new Student("Zamyatin", 30));
        students.add(new Student("Lucky", 50));
        students.add(new Student("Unlucky", 49));

        List<Student> classA = students.stream().filter(x -> x.getScore() >= 70).collect(Collectors.toList());
        System.out.println("class A:");
        classA.stream().map(Student::getSurname).forEach(System.out::println);

        List<Student> classB = students.stream().filter(x -> x.getScore() >= 50 && x.getScore() < 70).collect(Collectors.toList());
        System.out.println("class B:");
        classB.stream().map(Student::getSurname).forEach(System.out::println);

        List<Student> classV = students.stream().filter(x -> x.getScore() < 50).collect(Collectors.toList());
        System.out.println("class V:");
        classV.stream().map(Student::getSurname).forEach(System.out::println);

    }
}