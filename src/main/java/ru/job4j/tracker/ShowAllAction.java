package ru.job4j.tracker;

import java.util.List;

public class ShowAllAction implements UserAction {
    @Override
    public String name() {
        return "=== Stored Items ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        List<Item> stored = tracker.findAll();
        for (int i = 0; i < stored.size(); i++) {
            System.out.print("Item" + i + ": ");
            System.out.println(stored.get(i));
        }
        return true;
    }
}
