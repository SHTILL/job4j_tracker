package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int rightNum = 0;
        int leftNum  = 0;
        String[] leftNumbers = left.split("[.]");
        String[] rightNumbers = right.split("[.]");
        for (int i=0; i<Math.min(leftNumbers.length, rightNumbers.length); i++) {
            try {
                leftNum  = Integer.parseInt(leftNumbers[i]);
            } catch (Exception e) {
                leftNum  = Integer.MAX_VALUE;
            }

            try {
                rightNum  = Integer.parseInt(rightNumbers[i]);
            } catch (Exception e) {
                rightNum = Integer.MAX_VALUE;
            }

            int rsl = Integer.compare(leftNum, rightNum);
            if (rsl != 0) {
                return rsl;
            }
        }
        return 0;
    }
}
