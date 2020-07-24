package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список подощедщих пользователей.
     */
    public ArrayList<Person> find(String key) {
        Predicate<Person> name = (p) -> p.getName().contains(key);
        Predicate<Person> surname = (p) -> p.getSurname().contains(key);
        Predicate<Person> address = (p) -> p.getAddress().contains(key);
        Predicate<Person> phone = (p) -> p.getPhone().contains(key);
        Predicate<Person> combine = (p) -> name.or(surname.or(address.or(phone))).test(p);
        var result = new ArrayList<Person>();
        for (var p: this.persons) {
            if ( combine.test(p) ) {
                result.add(p);
            }
        }
        return result;
    }
}
