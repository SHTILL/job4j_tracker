package ru.job4j.tracker;

public class ConsoleOutput implements Output {
    @Override
    public void print(String s) {
        System.out.print(s);
    }

    @Override
    public void println(int i) {
        System.out.println(i);
    }

    @Override
    public void println(String s) {
        System.out.println(s);
    }

    @Override
    public void println(Object o) {
        System.out.println(o);
    }
}
