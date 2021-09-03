package gorbachev.spring.dao;

import gorbachev.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

@Component
public class PersonDAO {             //для инкапсуляции Person
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    //    private List<Person> people;                 // старый упрощённый вариант БД
//
//    {
//        people = new ArrayList<>();
//        people.add(new Person(++peopleCount, "Evgen", 25, "gorbachev.zhenya@gmail.com"));
//        people.add(new Person(++peopleCount, "Ivan", 18, "tiktokdvizhenie@yahoo.com"));
//        people.add(new Person(++peopleCount, "Alex", 14, "zxcghouldeadinside@gmail.com"));
//        people.add(new Person(++peopleCount, "Liza", 32, "dsflo21@gmail.com"));
//        people.add(new Person(++peopleCount, "Zuko", 72, "sdfs@gmail.com"));
//    }

    public List<Person> index() { //возвращаем список people
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {     //получить человека с нужным id или вернуть null
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {   //сохраняем человека в БД
        jdbcTemplate.update("INSERT INTO person(name, age, email) VALUES (?,?,?)", person.getName(), person.getAge(), person.getEmail());

    }

    public void update(int id, Person updatedPerson) { //заменяем текущие данные человека новыми
        jdbcTemplate.update("UPDATE person SET name=?, age=?, email=? WHERE id=?", updatedPerson.getName(),
                updatedPerson.getAge(), updatedPerson.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }
}
