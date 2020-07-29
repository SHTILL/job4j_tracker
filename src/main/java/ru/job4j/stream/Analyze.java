package ru.job4j.stream;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Analyze {
    static class SubjectScore {
        public double scoreSum;
        public int num;

        public SubjectScore(double scoreSum, int num) {
            this.scoreSum = scoreSum;
            this.num = num;
        }

        @Override
        public String toString() {
            return "Score:" + scoreSum + " Num:" + num + System.lineSeparator();
        }
    }

    public static double averageScore(Stream<Pupil> stream) {
        return stream.flatMapToInt(e -> e.getSubjects()
                .stream()
                .mapToInt(Subject::getScore))
                .average()
                .orElse(0D);
    }

    private static Stream<Map.Entry<String, SubjectScore>> getSubjectScore(Stream<Pupil> stream) {
            return stream.flatMap(e ->e.getSubjects().stream())
                    .collect(Collectors.toMap(Subject::getName,
                    s -> new SubjectScore(s.getScore(),1),
                    (s1,s2)->new SubjectScore(s1.scoreSum+s2.scoreSum, s1.num+s2.num)))
                    .entrySet()
                    .stream()
                    .peek((e) -> e.getValue().scoreSum = e.getValue().scoreSum / e.getValue().num);
    }

    public static double averageScoreBySubject(Stream<Pupil> stream) {
        return Analyze.getSubjectScore(stream)
                .mapToDouble(e -> (e.getValue().scoreSum))
                .average().orElse(0D);
    }

    public static double averageScoreByPupil(Stream<Pupil> stream) {
        return stream.mapToDouble(e -> e.getSubjects()
                .stream()
                .mapToInt(Subject::getScore)
                .average().orElse(0D))
                .average()
                .orElse(0D);
    }

    public static Pupil bestStudent(Stream<Pupil> stream) {
        return stream.max(Comparator.comparingDouble(p -> p.getSubjects()
                                                            .stream()
                                                            .mapToInt(Subject::getScore)
                                                            .average()
                                                            .orElse(0D)))
                .orElse(null);
    }

    public static Subject bestSubject(Stream<Pupil> stream) {
        return Analyze.getSubjectScore(stream)
                .max(Comparator.comparingDouble(e -> e.getValue().scoreSum))
                .map(e -> new Subject(e.getKey(), 0))
                .orElse(null);
    }
}