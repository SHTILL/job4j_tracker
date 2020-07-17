package ru.job4j.stream;

import java.util.List;
import java.util.stream.Collectors;

public class Profiles {
    static public List<Address> collect(List<Profile> profiles) {
        return profiles.stream().map(Profile::getAddress).collect(Collectors.toList());
    }

    static public List<Address> collectUniqueAndSorted(List<Profile> profiles) {
        return profiles.stream().map(Profile::getAddress).sorted(Address::compareTo).distinct().collect(Collectors.toList());
    }
}
