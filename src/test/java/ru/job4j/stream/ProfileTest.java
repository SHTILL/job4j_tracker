package ru.job4j.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ProfileTest {
    @Test
    public void whenGetAddresses (){
        List<Profile> profiles = new ArrayList<>();
        profiles.add(new Profile(new Address("SPb", "Basseinaya",13, 5)));
        profiles.add(new Profile(new Address("Moskow", "Chernaya",666, 13)));
        List<Address> addresses = Profiles.collect(profiles);
        List<Address> expected = List.of(   new Address("SPb", "Basseinaya",13, 5),
                                            new Address("Moskow", "Chernaya",666, 13));
        assertThat(addresses, is(expected));
    }

    @Test
    public void whenGetUniqueAddresses (){
        List<Profile> profiles = new ArrayList<>();
        profiles.add(new Profile(new Address("SPb", "Basseinaya",13, 5)));
        profiles.add(new Profile(new Address("Moskow", "Chernaya",666, 13)));
        profiles.add(new Profile(new Address("SPb", "Basseinaya",13, 5)));

        List<Address> addresses = Profiles.collectUniqueAndSorted(profiles);
        List<Address> expected = List.of(   new Address("Moskow", "Chernaya",666, 13),
                                            new Address("SPb", "Basseinaya",13, 5));
        assertThat(addresses, is(expected));
    }

}