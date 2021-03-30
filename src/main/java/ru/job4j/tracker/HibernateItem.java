package ru.job4j.tracker;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "items")
public class HibernateItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public HibernateItem() {
    }

    public HibernateItem(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HibernateItem item = (HibernateItem) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public String toString() {
        return "\"" + this.name + "\" id(" +  this.id + ")";
    }
}