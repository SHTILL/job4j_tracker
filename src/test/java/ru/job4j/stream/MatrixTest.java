package ru.job4j.stream;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MatrixTest {
    @Test
    public void whenMatrixToList () {
        Integer[][] data = {{1, 2, 3}, {5, 6, 7}};
        List<Integer> l = Matrix.matrixToList(data);
        List<Integer> expected = List.of(1, 2, 3, 5, 6, 7);
        assertThat(l, is(expected));
    }
}