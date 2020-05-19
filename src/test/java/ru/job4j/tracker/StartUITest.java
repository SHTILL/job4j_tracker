package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {
    @Test
    public void whenExit() {
        StubInput input = new StubInput(
                new String[] {"0"}
        );
        StubAction action = new StubAction();
        new StartUI().init(input, new Tracker(), new UserAction[] { action });
        assertThat(action.isCall(), is(true));
    }

    @Test
    public void whenCallAction() {
        Tracker t = new Tracker();
        StartUI su = new StartUI();

        StubInput choose0 = new StubInput(
                new String[] {"0"}
        );
        StubAction action0 = new StubAction();
        StubAction action1 = new StubAction();

        su.init(choose0, t, new UserAction[] { action0, action1 });
        assertThat(action0.isCall(), is(true));
        assertThat(action1.isCall(), is(false));

        StubInput choose1 = new StubInput(
                new String[] {"1"}
        );
        action0 = new StubAction();
        action1 = new StubAction();
        su.init(choose1, t, new UserAction[] { action0, action1 });
        assertThat(action0.isCall(), is(false));
        assertThat(action1.isCall(), is(true));
    }
}