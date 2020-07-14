package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class SearchAtt {

    private static List<Attachment> filter(List<Attachment> attachments, Predicate<Attachment> func) {
        List<Attachment> rsl = new ArrayList<>();
        for (Attachment a: attachments) {
            if ( func.test(a) ) {
                rsl.add(a);
            }
        }
        return rsl;
    }

    public static List<Attachment> filterSize(List<Attachment> list) {
        Predicate<Attachment> f = new Predicate<>() {
            @Override
            public boolean test(Attachment a) {
                return (a.getSize() > 100);
            }
        };
        return filter(list, f);
    }

    public static List<Attachment> filterName(List<Attachment> list) {
        Predicate<Attachment> f = new Predicate<>() {
            @Override
            public boolean test(Attachment a) {
                return (a.getName().contains("bug"));
            }
        };
        return filter(list, f);
    }

    public static void main(String[] args) {
        List<Attachment> l = new ArrayList<>();
        l.add(new Attachment("a", 500));
        l.add(new Attachment("bug", 10));
        l.add(new Attachment("bug", 500));
        l.add(new Attachment("b", 30));
        List<Attachment> rsl = filterName(l);
        System.out.println(rsl);
        rsl = filterSize(l);
        System.out.println(rsl);
    }
}
