package gorbachev.spring.dao;

import gorbachev.spring.models.Person;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

@Component
public class PersonDAO {             //для инкапсуляции Person
    private static int peopleCount;  //для уникального id пользователя
    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "huitaslonika1204ssdKSDFJI";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver"); //c помощью рефлексии загружается драйвер для работы с БД
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = getConnection(URL, USERNAME, PASSWORD); //инициализация подключения к БД
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
//    private List<Person> people;                 // старый упрощённый вариант БД
//
//    {
//        people = new ArrayList<>();
//        people.add(new Person(++peopleCount, "Evgen", 25, "gorbachev.zhenya@gmail.com"));
//        people.add(new Person(++peopleCount, "Ivan", 18, "tiktokdvizhenie@yahoo.com"));
//        people.add(new Person(++peopleCount, "Alex", 14, "zxcghouldeadinside@gmail.com"));
//        people.add(new Person(++peopleCount, "Liza", 32, "dsflo21@gmail.com"));
//        people.add(new Person(++peopleCount, "Чмоникс", 45, "sdfs@gmail.com"));
//    }

    public List<Person> index() { //возвращаем список people
        List<Person> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();  //объект, который содержит в себе sql запросы к БД
            String SQL = "SELECT * FROM person";
            ResultSet resultSet = statement.executeQuery(SQL);   //объект, инкапсулирующий результат выполнения запроса к БД
            while (resultSet.next()) {                  //без Hibernate вручную добавляю поля из таблицы и преобразую в объект
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                person.setAge(resultSet.getInt("age"));
                people.add(person);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return people;
    }

    public Person show(int id) {    //получить человека с нужным id или вернуть null
//        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
        return null;
    }

    public void save(Person person) {   //сохраняем человека в БД
        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO person VALUES(" + 1 + ",'" + person.getName() + "'," + person.getAge() + ",'" + person.getEmail() + "')"; // ручное неэффективное добавление INSERT INTO person VALUES(1, 'Evgen', 25, 'gorbachev.evgen@gmail.com')
            statement.executeUpdate(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//        person.setId(++peopleCount);
//        people.add(person);
    }

    public void update(int id, Person updatedPerson) { //заменяем текущие данные человека новыми
//        Person personToBeUpdated = show(id);
//        personToBeUpdated.setName(updatedPerson.getName());
//        personToBeUpdated.setAge(updatedPerson.getAge());
//        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
//        people.removeIf(person -> person.getId() == id); //if id true then delete from people
    }
}
