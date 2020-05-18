package ru.job4j.tracker;

public class ShowAllAction implements UserAction {
    @Override
    public String name() {
        return "=== Stored Items ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        Item[] stored = tracker.findAll();
        for (int i = 0; i < stored.length; i++) {
            System.out.print("Item" + i + ": ");
            System.out.println(stored[i]);
        }
        return true;
    }
}
