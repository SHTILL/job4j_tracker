package ru.job4j.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamUsage {
    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();
        l.add(15);
        l.add(0);
        l.add(-19);
        l.add(7);
        Stream<Integer> stream = l.stream();
        List<Integer> sorted = stream.filter(x -> x > 0).collect(Collectors.toList());
        System.out.println(sorted);
    }
}
