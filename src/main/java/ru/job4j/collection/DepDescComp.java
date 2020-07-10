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

        for (int i=1; i<Math.min(leftNames.length, rightNames.length); i++) {
            rsl = leftNames[i].compareTo(rightNames[i]);
            if (rsl != 0) {
                return rsl;
            }
        }
        return leftNames.length - rightNames.length;
    }
}
