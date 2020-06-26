package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ItemTest {
    @Test
    public void sortByName() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("b"));
        items.add(new Item("c"));
        items.add(new Item("a"));
        items.sort(new SortItemsByName());
        List<Item> expect = Arrays.asList(
                new Item("a"), new Item("b"), new Item("c")
        );
        assertThat(items, is(expect));
    }

    @Test
    public void sortByNameReverse() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("b"));
        items.add(new Item("c"));
        items.add(new Item("a"));
        items.sort(new SortItemsByNameReverseOrder());
        List<Item> expect = Arrays.asList(
                new Item("c"), new Item("b"), new Item("a")
        );
        assertThat(items, is(expect));
    }
}