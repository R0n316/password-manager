package ru.alex.FisrtSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.alex.FisrtSecurityApp.models.Person;
import ru.alex.FisrtSecurityApp.repositories.PeopleRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Person> findByUserName(String userName){
        return peopleRepository.findByUsername(userName);
    }
    public List<Person> findAll(){
        return peopleRepository.findAll();
    }
    public Person findById(int personId){
        Optional<Person> optional = peopleRepository.findById(personId);
        return optional.orElseThrow(NoSuchElementException::new);
    }

    public void update(int personId, Person updatedPerson){
        updatedPerson.setPersonId(personId);
        String encodedPassword = passwordEncoder.encode(updatedPerson.getPassword());
        updatedPerson.setPassword(encodedPassword);
        peopleRepository.save(updatedPerson);
    }
    public void delete(int personId){
        peopleRepository.deleteById(personId);
    }
}
