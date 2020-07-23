package ru.job4j.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class DepartmentsTest {
    @Test
    public void whenMissed() {
        List<String> input = List.of("k1/sk1");
        List<String> expect = List.of("k1", "k1/sk1");
        List<String> result = Departments.fillGaps(input);
        assertTrue(result.containsAll(expect));
    }

    @Test
    public void whenNonChange() {
        List<String> input = List.of("k1", "k1/sk1");
        List<String> expect = List.of("k1", "k1/sk1");
        List<String> result = Departments.fillGaps(input);
        assertTrue(result.containsAll(expect));
    }

    @Test
    public void whenAscCompare() {
        List<String> input = List.of("K2/SK1/SSK1",
                "K1/SK1/SSK2",
                "K1/SK1/SSK1",
                "K2/SK1",
                "K1/SK2",
                "K1/SK1",
                "K1",
                "K2/SK1/SSK2");

        List<String> expect = List.of("K1",
                "K1/SK1",
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K1/SK2",
                "K2",
                "K2/SK1",
                "K2/SK1/SSK1",
                "K2/SK1/SSK2");

        input = Departments.fillGaps(input);
        Departments.sortAsc(input);
        assertThat(input, is(expect));
    }

    @Test
    public void whenDescCompare() {
        List<String> input = List.of("K2/SK1/SSK1",
                "K1/SK1/SSK2",
                "K1/SK1/SSK1",
                "K2/SK1",
                "K1/SK2",
                "K1/SK1",
                "K1",
                "K2/SK1/SSK2");

        List<String> expect = List.of("K2",
                "K2/SK1",
                "K2/SK1/SSK1",
                "K2/SK1/SSK2",
                "K1",
                "K1/SK1",
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K1/SK2");

        input = Departments.fillGaps(input);
        Departments.sortDesc(input);
        assertThat(input, is(expect));
    }
}