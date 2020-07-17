package ru.job4j.stream;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Profile {
    private Address address;

    public Profile(Address address) {
        this.address = address;
    }

    public Address getAddress () {
        return address;
    }

    static public List<Address> collect(List<Profile> profiles) {
        return profiles.stream().map(Profile::getAddress).collect(Collectors.toList());
    }

    static public List<Address> collectUniqueAndSorted(List<Profile> profiles) {
        return profiles.stream().map(Profile::getAddress).sorted(Address::compareTo).distinct().collect(Collectors.toList());
    }
}