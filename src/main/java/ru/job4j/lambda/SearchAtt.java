package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SearchAtt {

    private static List<Attachment> filter(List<Attachment> attachments, Function<Attachment, Boolean> func) {
        List<Attachment> rsl = new ArrayList<>();
        for (Attachment a: attachments) {
            if ( func.apply(a) ) {
                rsl.add(a);
            }
        }
        return rsl;
    }

    public static List<Attachment> filterSize(List<Attachment> list) {
        Function<Attachment, Boolean> f = new Function<>() {
            @Override
            public Boolean apply(Attachment a) {
                return (a.getSize() > 100);
            }
        };
        return filter(list, f);
    }

    public static List<Attachment> filterName(List<Attachment> list) {
        Function<Attachment, Boolean> f = new Function<>() {
            @Override
            public Boolean apply(Attachment a) {
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
