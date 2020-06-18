package ru.job4j.tracker;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Tracker {
    /**
     * Массив для хранения заявок.
     */
    private final List<Item> items = new ArrayList<>();

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }

    /**
     * Method search for the item with a given id and return its index
     * @param id to be found
     * @return index of found item if item exists, -1 otherwise
     */
    private int indexOf(String id) {
        int rsl = -1;
        for (Item i: items) {
            if (i.getId().equals(id)) {
                rsl = items.indexOf(i);
                break;
            }
        }
        return rsl;
    }

    /**
     * Method store item into internal storage
     * @param item item to store
     * @return item that was stored
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        items.add(item);
        return item;
    }

    /**
     * Method return all non-null items(all items before this.position) from the internal storage
     * @return List of items from the storage before this.position
     */
    public List<Item> findAll() {
        return items;
    }

    /**
     * Method search for the item with a given id
     * @param id to be found
     * @return found item if exists, null otherwise
     */
    public Item findById(String id) {
        int index = indexOf(id);
        return index != -1 ? items.get(index) : null;
    }

    /**
     * Method search for all items with a given name
     * @param key to be found
     * @return List of found items, if no items found return zero length array
     */
    public List<Item> findByName(String key) {
        List<Item> itemsWithName = new ArrayList<>();
        for (Item current: items) {
            if (current != null && (current.getName().equals(key))) {
                itemsWithName.add(current);
            }
        }
        return itemsWithName;
    }

    /**
     * Replace item with given id with item passed in parameters. Keep existing id.
     * @param id of item to be replaced
     * @param item item to replace saved item
     * @return true if item was replaces, false is item was not found
     */
    public boolean replace(String id, Item item) {
        int index = indexOf(id);
        if (index == -1) {
            return false;
        }
        item.setId(id);
        items.set(index, item);
        return true;
    }

    /**
     * Delete item with given id
     * @param id of item to be deleted
     * @return true if item was deleted, false is item was not found
     */
    public boolean delete(String id) {
        int index = indexOf(id);
        if (index == -1) {
            return false;
        }
        items.remove(index);
        return true;
    }
}