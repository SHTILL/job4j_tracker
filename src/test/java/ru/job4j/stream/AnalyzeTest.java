package ru.job4j.stream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;

public class AnalyzeTest {
    @Test
    public void whenSinglePupil() {
        double average = Analyze.averageScore(
                List.of(
                        new Pupil("Ivanov", List.of(new Subject("Math", 100)))
                ).stream()
        );
        assertThat(average, is(100D));
    }

    @Test
    public void whenAverageScore() {
        double average = Analyze.averageScore(
                List.of(
                        new Pupil("Ivanov", List.of(new Subject("Math", 10), new Subject("English", 20), new Subject("Physics", 30))),
                        new Pupil("Petrov", List.of(new Subject("Math", 40), new Subject("English", 60)))
                ).stream()
        );
        assertEquals(average, 32D, 0.1);
    }

    @Test
    public void whenAveragePupils() {
        double average = Analyze.averageScoreByPupil(
                List.of(
                        new Pupil("Ivanov", List.of(new Subject("Math", 10), new Subject("English", 20), new Subject("Physics", 30))),
                        new Pupil("Petrov", List.of(new Subject("Math", 40), new Subject("English", 50)))
                ).stream()
        );
        assertThat(average, is(32.5D));
    }

    @Test
    public void whenAverageSubjects() {
        double average = Analyze.averageScoreBySubject(
                List.of(
                        new Pupil("Ivanov", List.of(new Subject("Math", 10), new Subject("English", 20), new Subject("Physics", 30))),
                        new Pupil("Petrov", List.of(new Subject("Math", 40), new Subject("English", 60)))
                ).stream()
        );
        assertEquals(average, 31.66D, 0.1);
    }

    @Test
    public void whenBestPupil() {
        Pupil ivanov = new Pupil("Ivanov", List.of(new Subject("Math", 10), new Subject("English", 20), new Subject("Physics", 30)));
        Pupil petrov = new Pupil("Petrov", List.of(new Subject("Math", 40), new Subject("English", 50)));
        Pupil p = Analyze.bestStudent(List.of(ivanov, petrov).stream());
        assertThat(p, is(petrov));
    }

    @Test
    public void whenBestSubject() {
        Pupil ivanov = new Pupil("Ivanov", List.of(new Subject("Math", 10), new Subject("English", 20), new Subject("Physics", 30)));
        Pupil petrov = new Pupil("Petrov", List.of(new Subject("Math", 40), new Subject("English", 50)));
        Subject s = Analyze.bestSubject(List.of(ivanov, petrov).stream());
        assertThat(s.getName(), is("English"));
    }
}
