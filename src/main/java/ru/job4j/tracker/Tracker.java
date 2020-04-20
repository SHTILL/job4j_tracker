package ru.job4j.tracker;

public class Tracker {
    private final Item[] requests = new Item[100];
    private int id = 1;
    private int size = 0;

    public Item add(Item request) {
        request.setId(generateId());
        requests[size++] = request;
        return request;
    }

    private String generateId() {
        return String.valueOf(id++);
    }

    public Item findById(String id) {
        Item rsl = null;
        for (int index = 0; index < size; index++) {
            Item current = requests[index];
            if (current.getId().equals(id)) {
                rsl = current;
                break;
            }
        }
        return rsl;
    }
}