package gorbachev.spring.controllers;

import gorbachev.spring.dao.PersonDAO;
import gorbachev.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {                           //показать всех людей
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {  //показать конкретного человека по id
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {   //id 0, name null

        return "people/new";                                    //html форма для создания нового человека
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person) {      //человек с данными из формы
        personDAO.save(person);
        return "redirect:/people";                             //переход на страницу при успешном добавлении в БД

    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {   //возвращает форму для изменения пользователя
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}