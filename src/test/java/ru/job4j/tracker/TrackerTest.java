package ru.job4j.tracker;

import org.junit.Assert;
import org.junit.Test;

import static java.util.function.Predicate.not;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.Arrays;

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertNotNull(result);
        assertThat(result.getName(), is(item.getName()));
        assertThat(result.getId(), is(item.getId()));
    }

    @Test
    public void whenNotFoundById() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId()+"5");
        assertNull(result);
    }

    @Test
    public void whenRequestAll() {
        Tracker tracker = new Tracker();
        Item[] addedItems = new Item[3];
        String[] itemsNames = new String[3];

        for (int i = 0; i < addedItems.length; i++) {
            String name = "test" + i;
            itemsNames[i] = name;
            addedItems[i] = new Item(name);
            tracker.add(addedItems[i]);
        }

        Item[] result = tracker.findAll();
        assertEquals(result.length, addedItems.length);

        for (int i = 0; i < itemsNames.length; i++) {
            int j = 0;
            for (j = 0; j < result.length; j++) {
                if (result[j].getName().equals(itemsNames[i])) {
                    break;
                }
            }
            if (j == result.length) {
                Assert.fail();
            }
        }
    }

    @Test
    public void whenRequestByName() {
        Tracker tracker = new Tracker();
        Item[] addedItems = new Item[3];
        addedItems[0] = new Item("test0");
        addedItems[1] = new Item("test1");
        addedItems[2] = new Item("test0");

        for (int i = 0; i < addedItems.length; i++) {
            tracker.add(addedItems[i]);
        }
        Item[] result = tracker.findByName("test0");
        assertEquals(2, result.length);
        assertEquals("test0", result[0].getName());
        assertEquals("test0", result[1].getName());
    }

    @Test
    public void whenNotFoundByName() {
        Tracker tracker = new Tracker();
        Item[] addedItems = new Item[3];
        addedItems[0] = new Item("test0");
        addedItems[1] = new Item("test1");
        addedItems[2] = new Item("test2");

        for (int i = 0; i < addedItems.length; i++) {
            tracker.add(addedItems[i]);
        }
        Item[] result = tracker.findByName("test3");
        assertEquals(0, result.length);
    }
}