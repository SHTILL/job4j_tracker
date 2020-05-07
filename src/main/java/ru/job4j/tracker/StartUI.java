package ru.job4j.tracker;

import java.util.Scanner;

public class StartUI {
    public void init(Scanner scanner, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            System.out.print("Select: ");
            int select = 0;
            try {
                select = Integer.valueOf(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Please enter number in the range from 0 to 6");
                continue;
            }
            if (select == 0) {
                System.out.println("=== Create a new Item ====");
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                Item item = new Item(name);
                tracker.add(item);
            } else if (select == 1) {
                System.out.println("=== Stored Items ====");
                Item[] stored = tracker.findAll();
                for (int i = 0; i < stored.length; i++) {
                    System.out.println("Item" + i + ": \"" + stored[i].getName() + "\" id(" +  stored[i].getId() + ")");
                }
            } else if (select == 2) {
                System.out.println("=== Edit Item ====");
                System.out.print("Enter id: ");
                String id = scanner.nextLine();
                System.out.print("Enter new name: ");
                String name = scanner.nextLine();
                Item item = new Item(name);
                if ( tracker.replace(id, item) == true ) {
                    System.out.print("Item was edited successfully");
                } else {
                    System.out.print("Error. Probably wrong id was entered.");
                }
            } else if (select == 3) {
                System.out.println("=== Delete Item ====");
                System.out.print("Enter id: ");
                String id = scanner.nextLine();
                if ( tracker.delete(id) == true ) {
                    System.out.print("Item was deleted successfully");
                } else {
                    System.out.print("Error. Probably wrong id was entered.");
                }
            } else if (select == 4) {
                System.out.println("=== Find Item by Id====");
                System.out.print("Enter id: ");
                String id = scanner.nextLine();
                Item item = tracker.findById(id);
                if ( item != null ) {
                    System.out.println("Item" + ": \"" + item.getName() + "\" id(" +  item.getId() + ")");
                } else {
                    System.out.print("Error. Probably wrong id was entered.");
                }
            } else if (select == 5) {
                System.out.println("=== Find Items by Name====");
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                Item[] found = tracker.findByName(name);
                for (int i = 0; i < found.length; i++) {
                    System.out.println("Item" + i + ": \"" + found[i].getName() + "\" id(" +  found[i].getId() + ")");
                }
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
        Scanner scanner = new Scanner(System.in);
        Tracker tracker = new Tracker();
        new StartUI().init(scanner, tracker);
    }
}
