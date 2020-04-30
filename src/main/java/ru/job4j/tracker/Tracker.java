package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

public class Tracker {
    /**
     * Массив для хранения заявок.
     */
    private final Item[] items = new Item[100];
    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

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
        for (int index = 0; index < position; index++) {
            if (items[index].getId().equals(id)) {
                rsl = index;
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
        items[position++] = item;
        return item;
    }

    /**
     * Method return all non-null items(all items before this.position) from the internal storage
     * @return arrays of items from the storage before this.position
     */
    public Item[] findAll() {
        return  Arrays.copyOf(this.items, this.position);
    }

    /**
     * Method search for the item with a given id
     * @param id to be found
     * @return found item if exists, null otherwise
     */
    public Item findById(String id) {
        int index = indexOf(id);
        return index != -1 ? items[index] : null;
    }

    /**
     * Method search for all items with a given name
     * @param key to be found
     * @return array of found items, if no items found return zero length array
     */
    public Item[] findByName(String key) {
        Item[] itemsWithName = new Item[this.position];
        int size = 0;
        for (int i = 0; i < this.position; i++ ) {
            Item current = this.items[i];
            if (current != null && (current.getName().equals(key))) {
                itemsWithName[size] = current;
                size++;
            }
        }
        itemsWithName = Arrays.copyOf(itemsWithName, size);
        return  itemsWithName;
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
        items[index] = item;
        return true;
    }
}