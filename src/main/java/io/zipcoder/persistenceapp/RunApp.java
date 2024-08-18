package io.zipcoder.persistenceapp;

import io.zipcoder.persistenceapp.Models.Person;
import io.zipcoder.persistenceapp.Services.PersonService;

public class RunApp {
    public static void main(String[] args) {
        PersonService person = new PersonService();

        Person person1 = person.findByID(2);
        System.out.println(person1);
    }
}
