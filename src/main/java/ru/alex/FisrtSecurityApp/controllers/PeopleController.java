package ru.alex.FisrtSecurityApp.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alex.FisrtSecurityApp.models.Person;
import ru.alex.FisrtSecurityApp.services.PeopleService;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping
    public String index(Model model){
        List<Person> people = peopleService.findAll();
        model.addAttribute("people",people);
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int personId, Model model){
        model.addAttribute(peopleService.findById(personId));
        return "people/show";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int personId, @ModelAttribute("person") @Valid Person updatedPerson,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "people/show";
        }
        peopleService.update(personId,updatedPerson);
        return "redirect:/people";
        // TODO добавить валидацию пользователя
    }
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int personId){
        peopleService.delete(personId);
        return "redirect:/people";
    }
}
