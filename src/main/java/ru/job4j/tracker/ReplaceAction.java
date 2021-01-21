package ru.job4j.tracker;

public class ReplaceAction implements UserAction {
    @Override
    public String name() {
        return "=== Edit Item ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        String id = input.askStr("Enter id: ");
        String name = input.askStr("Enter new name: ");
        Item item = new Item(name);
        if (tracker.replace(id, item)) {
            System.out.println("Item was edited successfully");
        } else {
            System.out.println("Error. Probably wrong id was entered.");
        }
        return true;
    }
}
