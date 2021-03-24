package ru.job4j.tracker;

import java.util.List;

public class FindByNameAction implements UserAction {
    private Output output;

    public FindByNameAction() {
        output = new ConsoleOutput();
    }

    public FindByNameAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "=== Find Items by Name ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        String name = input.askStr("Enter name: ");
        List<Item> found = tracker.findByName(name);
        if (found.size() == 0) {
            output.println("No items with name \"" + name + "\" was found");
        } else {
            for (int i = 0; i < found.size(); i++) {
                output.print("Item" + i + ": ");
                output.println(found.get(i));
            }
        }
        return true;
    }
}
