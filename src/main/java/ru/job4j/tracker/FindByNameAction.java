package ru.job4j.tracker;

public class FindByNameAction implements UserAction {
    @Override
    public String name() {
        return "=== Find Items by Name ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String name = input.askStr("Enter name: ");
        Item[] found = tracker.findByName(name);
        if (found.length == 0) {
            System.out.println("No items with name \"" + name + "\" was found");
        } else {
            for (int i = 0; i < found.length; i++) {
                System.out.print("Item" + i + ": ");
                System.out.println(found[i]);
            }
        }
        return true;
    }
}
