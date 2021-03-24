package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteActionTest {
    @Test
    public void whenDeleteSucceed() {
        MemTracker tracker = new MemTracker();
        Output out = new StubOutput();
        Item item = new Item("Item");
        tracker.add(item);
        UserAction action = new DeleteAction(out);

        Input input = mock(Input.class);

        when(input.askStr("Enter id: ")).thenReturn(item.getId());

        action.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("Item was deleted successfully" + ln));
        assertEquals(tracker.findAll().size(), 0);
    }

    @Test
    public void whenDeleteFailed() {
        MemTracker tracker = new MemTracker();
        Output out = new StubOutput();
        UserAction action = new DeleteAction(out);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn("0");

        action.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("Error. Probably wrong id was entered." + ln));
    }
}