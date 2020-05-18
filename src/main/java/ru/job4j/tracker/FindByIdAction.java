package ru.job4j.tracker;

public class FindByIdAction implements UserAction {
    @Override
    public String name() {
        return "=== Find Item by Id ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String id = input.askStr("Enter id: ");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.print("Item: ");
            System.out.println(item);
        } else {
            System.out.println("Error. Probably wrong id was entered.");
        }
        return true;
    }
}
