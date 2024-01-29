package ru.alex.FisrtSecurityApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alex.FisrtSecurityApp.models.Person;

import java.util.Optional;

public interface PeopleRepository extends JpaRepository<Person,Integer> {
    Optional<Person> findByUsername(String userName);
}
