package com.haskellfusion.lens;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class Person {
    private final String name;
    private final Address address;

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }
}

class Address {
    private final String city;

    public Address(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}


class Lenses {
    public static final Lens<Person, Address> personAddress = new Lens<>(Person::getAddress,
            (person, address) -> new Person(person.getName(), address));

    public static final Lens<Address, String> addressCity = new Lens<>(Address::getCity,
            (address, city) -> new Address(city));

    public static final Lens<Person, String> personCity = personAddress.compose(addressCity);
}


class LensTest {
    @Test
    void testLensGet() {
        Person person = new Person("John Doe", new Address("New York"));
        assertEquals("New York", Lenses.personCity.get(person));
    }

    @Test
    void testLensSet() {
        Person person = new Person("John Doe", new Address("New York"));
        Person updatedPerson = Lenses.personCity.set(person, "San Francisco");
        assertEquals("San Francisco", updatedPerson.getAddress().getCity());
    }
}