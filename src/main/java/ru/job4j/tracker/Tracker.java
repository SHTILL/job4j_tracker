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
     * Method return all non-null values from the internal storage
     * @return arrays of items from the storage cleared from null elements
     */
    public Item[] findAll() {
        Item[] itemsWithoutNull = new Item[this.position];
        int size = 0;
        for (int i = 0; i < this.position; i++ ) {
            if (this.items[i] != null) {
                itemsWithoutNull[size] = this.items[i];
                size++;
            }
        }
        itemsWithoutNull = Arrays.copyOf(itemsWithoutNull, size);
        return  itemsWithoutNull;
    }

    /**
     * Method search for the item with a given id
     * @param id to be found
     * @return found item if exists, null otherwise
     */
    public Item findById(String id) {
        Item rsl = null;
        for (int index = 0; index < position; index++) {
            Item current = items[index];
            if (current.getId().equals(id)) {
                rsl = current;
                break;
            }
        }
        return rsl;
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
            if (this.items[i] != null && (this.items[i].getName().equals(key))) {
                itemsWithName[size] = this.items[i];
                size++;
            }
        }
        itemsWithName = Arrays.copyOf(itemsWithName, size);
        return  itemsWithName;
    }
}