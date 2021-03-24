package ru.job4j.tracker;

import java.util.List;

public class ShowAllAction implements UserAction {
    private Output output;

    public ShowAllAction() {
        output = new ConsoleOutput();
    }

    public ShowAllAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "=== Stored Items ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        List<Item> stored = tracker.findAll();
        for (int i = 0; i < stored.size(); i++) {
            output.print("Item" + i + ": ");
            output.println(stored.get(i));
        }
        return true;
    }
}
