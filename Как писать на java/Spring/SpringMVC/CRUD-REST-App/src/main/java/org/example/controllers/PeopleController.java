package org.example.controllers;

import org.example.models.Person;
import org.example.models.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    PersonDAO dao;

    @Autowired
    public PeopleController(PersonDAO dao) {
        this.dao = dao;
    }

    @GetMapping()
    public String readAll(Model model){
        model.addAttribute("people", dao.getPeople());
        return "people_list";
    }

    @GetMapping("/{id}")
    public String read(@PathVariable("id") int id, Model model){
        model.addAttribute("person", dao.getPerson(id));
        return "person";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model){
        model.addAttribute(new Person()); //чтобы thymeleaf динамечески создал форму
        return "create_person";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person){
        dao.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") int id, Model model){
        model.addAttribute(dao.getPerson(id));
        return "edit_person";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id){
        dao.edit(id, person);
        return "redirect:/people/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        dao.delete(id);
        return "redirect:/people";
    }
}
