package ru.job4j.tracker;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.List;

public class MemTrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        MemTracker tracker = new MemTracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertNotNull(result);
        assertThat(result.getName(), is(item.getName()));
        assertThat(result.getId(), is(item.getId()));
    }

    @Test
    public void whenNotFoundById() {
        MemTracker tracker = new MemTracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId()+"5");
        assertNull(result);
    }

    @Test
    public void whenRequestAll() {
        MemTracker tracker = new MemTracker();
        Item[] addedItems = new Item[3];
        String[] itemsNames = new String[3];

        for (int i = 0; i < addedItems.length; i++) {
            String name = "test" + i;
            itemsNames[i] = name;
            addedItems[i] = new Item(name);
            tracker.add(addedItems[i]);
        }

        List<Item> result = tracker.findAll();
        assertEquals(result.size(), addedItems.length);

        for (int i = 0; i < itemsNames.length; i++) {
            int j = 0;
            for (j = 0; j < result.size(); j++) {
                if (result.get(j).getName().equals(itemsNames[i])) {
                    break;
                }
            }
            if (j == result.size()) {
                Assert.fail();
            }
        }
    }

    @Test
    public void whenRequestByName() {
        MemTracker tracker = new MemTracker();
        Item[] addedItems = new Item[3];
        addedItems[0] = new Item("test0");
        addedItems[1] = new Item("test1");
        addedItems[2] = new Item("test0");

        for (int i = 0; i < addedItems.length; i++) {
            tracker.add(addedItems[i]);
        }
        List<Item> result = tracker.findByName("test0");
        assertEquals(2, result.size());
        assertEquals("test0", result.get(0).getName());
        assertEquals("test0", result.get(1).getName());
    }

    @Test
    public void whenNotFoundByName() {
        MemTracker tracker = new MemTracker();
        Item[] addedItems = new Item[3];
        addedItems[0] = new Item("test0");
        addedItems[1] = new Item("test1");
        addedItems[2] = new Item("test2");

        for (int i = 0; i < addedItems.length; i++) {
            tracker.add(addedItems[i]);
        }
        List<Item> result = tracker.findByName("test3");
        assertEquals(0, result.size());
    }

    @Test
    public void whenReplacedWithoutErrors() {
        MemTracker tracker = new MemTracker();
        Item saved = new Item("saved");
        tracker.add(saved);
        String idSaved = saved.getId();
        Item replace = new Item("replaced");
        assertTrue(tracker.replace(idSaved, replace));
        List<Item> result = tracker.findByName("saved");
        assertEquals(0, result.size());
        result = tracker.findByName("replaced");
        assertEquals(1, result.size());
        assertEquals(idSaved, result.get(0).getId());
    }

    @Test
    public void whenReplacedNotFound() {
        MemTracker tracker = new MemTracker();
        Item saved = new Item("saved");
        tracker.add(saved);
        String idSaved = saved.getId();
        String idFake = "123";
        Item replace = new Item("replaced");
        assertFalse(tracker.replace(idFake, replace));
        List<Item> result = tracker.findByName("saved");
        assertEquals(1, result.size());
        assertEquals(idSaved, result.get(0).getId());
        result = tracker.findByName("replaced");
        assertEquals(0, result.size());
    }

    @Test
    public void whenDelete() {
        MemTracker tracker = new MemTracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        String id = bug.getId();
        assertTrue(tracker.delete(id));
        assertNull(tracker.findById(id));
    }
}