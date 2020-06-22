package ru.job4j.collection;

import java.util.HashMap;
import java.util.Map;

public class UsageMap {
    public static void main(String[] args) {
        Map<String, String> dict = new HashMap<>();
        dict.put("vasya@mail.ru", "Vasya Koryakin");
        dict.put("fedya@mail.ru", "Fedya Kozlov");
        for (String mail: dict.keySet()) {
            System.out.println(mail + ": " + dict.get(mail));
        }
    }
}
