package ru.job4j.tracker;

import java.util.Scanner;

public class StartUI {
    private static void createItem(Input input, Tracker tracker) {
        System.out.println("=== Create a new Item ====");
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        tracker.add(item);
    }

    private static void createShowStoredItems(Input input, Tracker tracker) {
        System.out.println("=== Stored Items ====");
        Item[] stored = tracker.findAll();
        for (int i = 0; i < stored.length; i++) {
            System.out.print("Item" + i + ": ");
            System.out.println(stored[i]);
        }
    }

    private static void replaceItem(Input input, Tracker tracker) {
        System.out.println("=== Edit Item ====");
        String id = input.askStr("Enter id: ");
        String name = input.askStr("Enter new name: ");
        Item item = new Item(name);
        if (tracker.replace(id, item)) {
            System.out.println("Item was edited successfully");
        } else {
            System.out.println("Error. Probably wrong id was entered.");
        }
    }

    private static void deleteItem(Input input, Tracker tracker) {
        System.out.println("=== Delete Item ====");
        String id = input.askStr("Enter id: ");
        if (tracker.delete(id)) {
            System.out.println("Item was deleted successfully");
        } else {
            System.out.println("Error. Probably wrong id was entered.");
        }
    }

    private static void findItemById(Input input, Tracker tracker) {
        System.out.println("=== Find Item by Id====");
        String id = input.askStr("Enter id: ");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.print("Item: ");
            System.out.println(item);
        } else {
            System.out.println("Error. Probably wrong id was entered.");
        }
    }

    private static void findItemByName(Input input, Tracker tracker) {
        System.out.println("=== Find Items by Name====");
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
    }

    public void init(Input input, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            int select = 0;
            try {
                select = input.askInt("Select: ");
            } catch (Exception e) {
                System.out.println("Please enter number in the range from 0 to 6");
                continue;
            }
            if (select == 0) {
                StartUI.createItem(input, tracker);
            } else if (select == 1) {
                StartUI.createShowStoredItems(input, tracker);
            } else if (select == 2) {
                StartUI.replaceItem(input, tracker);
            } else if (select == 3) {
                StartUI.deleteItem(input, tracker);
            } else if (select == 4) {
                StartUI.findItemById(input, tracker);
            } else if (select == 5) {
                StartUI.findItemByName(input, tracker);
            } else if (select == 6) {
                run = false;
            }
        }
    }

    private void showMenu() {
        System.out.println("Menu.");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        new StartUI().init(input, tracker);
    }
}
