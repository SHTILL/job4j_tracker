package ru.job4j.tracker;

public class StubOutput implements Output {
    private String buffer = "";

    @Override
    public void print(String s) {
        StringBuilder b = new StringBuilder(buffer);
        b.append(s);
        buffer = b.toString();
    }

    @Override
    public void println(String s) {
        StringBuilder b = new StringBuilder(buffer);
        b.append(s).append(System.lineSeparator());
        buffer = b.toString();
    }

    @Override
    public void println(int i) {
        StringBuilder b = new StringBuilder(buffer);
        b.append(i).append(System.lineSeparator());
        buffer = b.toString();
    }

    @Override
    public void println(Object o) {
        StringBuilder b = new StringBuilder(buffer);
        b.append(o.toString()).append(System.lineSeparator());
        buffer = b.toString();
    }

    @Override
    public String toString() {
        return buffer;
    }
}
