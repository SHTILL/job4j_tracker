package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindByNameActionTest {
    @Test
    public void whenFound() {
        MemTracker tracker = new MemTracker();
        Output out = new StubOutput();
        Item item = new Item("Item name");
        tracker.add(item);
        UserAction action = new FindByNameAction(out);

        Input input = mock(Input.class);

        when(input.askStr("Enter name: ")).thenReturn(item.getName());

        action.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("Item0: " + item.toString() + ln));
    }

    @Test
    public void whenNotFound() {
        MemTracker tracker = new MemTracker();
        Output out = new StubOutput();
        UserAction action = new FindByNameAction(out);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn("STUB");

        action.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("No items with name \"STUB\" was found" + ln));
    }
}