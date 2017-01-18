package com.example;

import com.example.birthdays.BirthdaysClient;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.Supplier;

class Person {
    private final String givenName;
    private final String surname;
    private final LocalDate dateOfBirth;
    private final Supplier<LocalDate> currentDateSupplier;

    Person(String givenName, String surname, LocalDate dateOfBirth) {
        this(givenName, surname, dateOfBirth, LocalDate::now, null);
    }

    // Visible for testing
    Person(String givenName, String surname, LocalDate dateOfBirth, Supplier<LocalDate> currentDateSupplier, BirthdaysClient birthdaysClient) {
        this.givenName = givenName;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.currentDateSupplier = currentDateSupplier;
    }

    String getDisplayName() {
        return surname + ", " + givenName;
    }

    long getAge() {
        return ChronoUnit.YEARS.between(dateOfBirth, currentDateSupplier.get());
    }

    void publishAge() {
    }

    public static void main(String... args) {
        Person person = new Person("Joey", "Doe", LocalDate.parse("2013-01-12"));
        System.out.println(person.getDisplayName() + ": " + person.getAge() + " years");
        // Doe, Joey: 4 years
    }
}