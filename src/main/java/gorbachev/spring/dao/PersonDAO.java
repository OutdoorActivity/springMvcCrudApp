package gorbachev.spring.dao;

import gorbachev.spring.models.Person;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {             //для инкапсуляции Person
    private static int peopleCount;  //для уникального id пользователя
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

    public Person show(int id) {    //получить человека с нужным id или вернуть null
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {   //сохраняем человека в БД
        person.setId(++peopleCount);
        people.add(person);
    }

    public void update(int id, Person updatedPerson) { //заменяем текущие данные человека новыми
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(updatedPerson.getName());
    }

    public void delete(int id) {
        people.removeIf(person -> person.getId() == id); //if id true then delete from people
    }
}
