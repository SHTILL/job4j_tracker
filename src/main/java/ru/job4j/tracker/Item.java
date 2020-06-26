package ru.job4j.tracker;

import java.util.Objects;

public class Item {
    private String id;
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        Item i = (Item)obj;
        if (this == i) {
            return true;
        }
        return this.name.equals(i.getName());
    }

    public String toString() {
        return "\"" + this.name + "\" id(" +  this.id + ")";
    }
}