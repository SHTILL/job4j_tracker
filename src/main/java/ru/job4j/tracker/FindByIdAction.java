package ru.job4j.tracker;

public class FindByIdAction implements UserAction {
    private Output output;

    public FindByIdAction() {
        output = new ConsoleOutput();
    }

    public FindByIdAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "=== Find Item by Id ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        String id = input.askStr("Enter id: ");
        Item item = tracker.findById(id);
        if (item != null) {
            output.print("Item: ");
            output.println(item);
        } else {
            output.println("Error. Probably wrong id was entered.");
        }
        return true;
    }
}
