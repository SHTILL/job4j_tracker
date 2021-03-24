package ru.job4j.tracker;

public class ReplaceAction implements UserAction {
    private Output output;

    public ReplaceAction() {
        output = new ConsoleOutput();
    }

    public ReplaceAction(Output output) {
        this.output = output;
    }

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
            output.println("Item was edited successfully");
        } else {
            output.println("Error. Probably wrong id was entered.");
        }
        return true;
    }
}
