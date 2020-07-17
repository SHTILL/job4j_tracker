package ru.job4j.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProfileTest {
    @Test
    public void whenGetAddresses (){
        List<Profile> profiles = new ArrayList<>();
        profiles.add(new Profile(new Address("SPb", "Basseinaya",13, 5)));
        profiles.add(new Profile(new Address("Moskow", "Chernaya",666, 13)));
        List<Address> addresses = Profile.collect(profiles);
        addresses.forEach(System.out::println);
    }

}