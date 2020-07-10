package ru.job4j.collection;

import java.util.*;

public class Departments {
    public static List<String> fillGaps(List<String> deps) {
        HashSet<String> tmp = new HashSet<>();
        for (String depsStr : deps) {
            String[] depNames = depsStr.split("/");
            String start = depNames[0];
            tmp.add(start);
            for (int i = 1; i < depNames.length; i++) {
                start += ("/" + depNames[i]);
                tmp.add(start);
            }
        }
        return new ArrayList<>(tmp);
    }

    public static void sortAsc(List<String> orgs) {
        Comparator<String> c = new DepAscComp();
        orgs.sort(c);
    }

    public static void sortDesc(List<String> orgs) {
        Comparator<String> c = new DepDescComp();
        orgs.sort(c);
    }
}
