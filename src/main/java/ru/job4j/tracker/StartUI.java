package ru.job4j.tracker;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class StartUI {
    public void init(Input input, Store tracker, List<UserAction> actions) {
        boolean run = true;

        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ", actions.size());
            UserAction action = actions.get(select);
            run = action.execute(input, tracker);
        }
    }

    private void showMenu(List<UserAction> actions) {
        System.out.println("Menu.");
        for (UserAction a: actions) {
            System.out.println(actions.indexOf(a) + ". " + a.name());
        }
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Input validate = new ValidateInput(input);
        Store tracker = new HbmTracker();
        UserAction[] actions = {
                new CreateAction(),
                new ShowAllAction(),
                new ReplaceAction(),
                new DeleteAction(),
                new FindByIdAction(),
                new FindByNameAction(),
                new ExitAction()
        };
        List<UserAction> actionsList = new ArrayList<>(Arrays.asList(actions));
        new StartUI().init(validate, tracker, actionsList);
    }
}
