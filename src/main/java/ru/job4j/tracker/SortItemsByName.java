package ru.job4j.tracker;

import java.util.Comparator;

public class SortItemsByName implements Comparator<Item> {
    @Override
    public int compare(Item one, Item two) {
        return one.getName().compareTo(two.getName());
    }
}
