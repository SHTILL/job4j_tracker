package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int rightNum = 0;
        int leftNum  = 0;
        String[] leftNumbers = left.split("[.]");
        String[] rightNumbers = right.split("[.]");
        try {
            leftNum  = Integer.parseInt(leftNumbers[0]);
        } catch (Exception e) {
            leftNum  = Integer.MIN_VALUE;
        }

        try {
            rightNum  = Integer.parseInt(rightNumbers[0]);
        } catch (Exception e) {
            rightNum = Integer.MIN_VALUE;
        }

        return Integer.compare(leftNum, rightNum);
    }
}
