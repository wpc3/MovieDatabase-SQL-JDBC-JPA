package io.zipcoder.persistenceapp;

import io.zipcoder.persistenceapp.Models.Person;
import io.zipcoder.persistenceapp.Services.PersonService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class RunApp {
    public static void main(String[] args) {
        PersonService person = new PersonService();

//        Person person1 = (Person) person.findByID(2);
//        System.out.println(person1);
        Date birthday = new Date(1990,12,11);
//        LocalDate birthday = LocalDate.of(1990, 12, 11);
//        Date sqlDate = Date.parse(birthday);
//        LocalDate birthday2 = LocalDate.of(1990, 12, 11);
//        Date sqlDate = Date.valueOf(birthday);
        LocalDate birthday2 = LocalDate.of(1990, 12, 11);
        java.sql.Date sqlDate = java.sql.Date.valueOf(birthday2);

        Person me = new Person(13,"Will","Chapman","899-3434", sqlDate,4);
//        Person person1 = person.create(me);
//        System.out.println(person1);
        Person person1 = person.update(me);
        System.out.println(person1);

    }
}
