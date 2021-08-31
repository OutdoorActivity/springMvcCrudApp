package gorbachev.spring.dao;

import gorbachev.spring.models.Person;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {   //для инкапсуляции Person
    private static int peopleCount;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++peopleCount, "Evgen"));
        people.add(new Person(++peopleCount, "Ivan"));
        people.add(new Person(++peopleCount, "Alex"));
        people.add(new Person(++peopleCount, "Liza"));
        people.add(new Person(++peopleCount, "Чмоникс"));
    }

    public List<Person> index() { //возвращаем список people
        return people;
    }

    public Person show(int id) { //получить человека с нужным id или вернуть null
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }
}
