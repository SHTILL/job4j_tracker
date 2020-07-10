package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String depNamesLeft, String depNamesRight) {
        int rsl = 0;
        String[] leftNames = depNamesLeft.split("/");
        String[] rightNames = depNamesRight.split("/");

        rsl = rightNames[0].compareTo(leftNames[0]);
        if (rsl != 0) {
            return rsl;
        }

        return depNamesLeft.compareTo(depNamesRight);
    }
}
