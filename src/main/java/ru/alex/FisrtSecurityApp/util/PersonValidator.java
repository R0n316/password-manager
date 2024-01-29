package ru.alex.FisrtSecurityApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alex.FisrtSecurityApp.models.Person;
import ru.alex.FisrtSecurityApp.services.PeopleService;

import java.util.Optional;


@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Person.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        Optional<Person> result = peopleService.findByUserName(person.getUsername());
        if(result.isPresent()){
            errors.rejectValue("username","","Человек с таким именем пользователя уже существует");
        }
    }
}
