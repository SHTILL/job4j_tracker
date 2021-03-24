package ru.job4j.tracker;

public class DeleteAction implements UserAction {
    private Output output;

    public DeleteAction() {
        output = new ConsoleOutput();
    }

    public DeleteAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "=== Delete Item ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        String id = input.askStr("Enter id: ");
        if (tracker.delete(id)) {
            output.println("Item was deleted successfully");
        } else {
            output.println("Error. Probably wrong id was entered.");
        }
        return true;
    }
}
