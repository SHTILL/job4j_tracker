package ru.job4j.tracker;

import java.util.List;

public class FindByNameAction implements UserAction {
    @Override
    public String name() {
        return "=== Find Items by Name ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String name = input.askStr("Enter name: ");
        List<Item> found = tracker.findByName(name);
        if (found.size() == 0) {
            System.out.println("No items with name \"" + name + "\" was found");
        } else {
            for (int i = 0; i < found.size(); i++) {
                System.out.print("Item" + i + ": ");
                System.out.println(found.get(i));
            }
        }
        return true;
    }
}
