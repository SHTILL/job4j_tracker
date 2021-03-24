package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ReplaceActionTest {
    @Test
    public void whenReplaceSucceed() {
        MemTracker tracker = new MemTracker();
        Output out = new StubOutput();
        Item item = new Item("Replaced item");
        tracker.add(item);
        String replacedName = "New item name";
        UserAction action = new ReplaceAction(out);

        Input input = mock(Input.class);

        when(input.askStr("Enter id: ")).thenReturn(item.getId());
        when(input.askStr("Enter new name: ")).thenReturn(replacedName);

        action.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("Item was edited successfully" + ln));
        assertThat(tracker.findAll().get(0).getName(), is(replacedName));
    }

    @Test
    public void whenReplaceFailed() {
        MemTracker tracker = new MemTracker();
        Output out = new StubOutput();
        Item item = new Item("Item name");
        tracker.add(item);
        UserAction action = new ReplaceAction(out);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn("0");

        action.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("Error. Probably wrong id was entered." + ln));
    }
}