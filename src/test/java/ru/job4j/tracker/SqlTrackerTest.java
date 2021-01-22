package ru.job4j.tracker;

import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SqlTrackerTest {
    public Connection init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void createItem() throws Exception {
        try (Store tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("desc"));
            assertThat(tracker.findByName("desc").size(), is(1));
        }
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() throws Exception {
        try (Store tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = new Item("test1");
            tracker.add(item);
            Item result = tracker.findById(item.getId());
            assertNotNull(result);
            assertThat(result.getName(), is(item.getName()));
            assertThat(result.getId(), is(item.getId()));
        }
    }

    @Test
    public void whenNotFoundById() throws Exception {
        try (Store tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = new Item("test1");
            tracker.add(item);
            Item result = tracker.findById(Integer.toString(Integer.parseInt(item.getId()) + 5));
            assertNull(result);
        }
    }

    @Test
    public void whenRequestAll() throws Exception {
        try (Store tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
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

            for (String itemsName : itemsNames) {
                int j = 0;
                for (j = 0; j < result.size(); j++) {
                    if (result.get(j).getName().equals(itemsName)) {
                        break;
                    }
                }
                if (j == result.size()) {
                    Assert.fail();
                }
            }
        }
    }

    @Test
    public void whenRequestByName() throws Exception {
        try (Store tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item[] addedItems = new Item[3];
            addedItems[0] = new Item("test0");
            addedItems[1] = new Item("test1");
            addedItems[2] = new Item("test0");

            for (Item addedItem : addedItems) {
                tracker.add(addedItem);
            }
            List<Item> result = tracker.findByName("test0");
            assertEquals(2, result.size());
            assertEquals("test0", result.get(0).getName());
            assertEquals("test0", result.get(1).getName());
        }
    }

    @Test
    public void whenNotFoundByName() throws Exception {
        try (Store tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item[] addedItems = new Item[3];
            addedItems[0] = new Item("test0");
            addedItems[1] = new Item("test1");
            addedItems[2] = new Item("test2");

            for (Item addedItem : addedItems) {
                tracker.add(addedItem);
            }
            List<Item> result = tracker.findByName("test3");
            assertEquals(0, result.size());
        }
    }

    @Test
    public void whenReplacedWithoutErrors() throws Exception {
        try (Store tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
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
    }

    @Test
    public void whenReplacedNotFound() throws Exception {
        try (Store tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
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
    }

    @Test
    public void whenDelete() throws Exception {
        try (Store tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item bug = new Item("Bug");
            tracker.add(bug);
            String id = bug.getId();
            assertTrue(tracker.delete(id));
            assertNull(tracker.findById(id));
        }
    }
}