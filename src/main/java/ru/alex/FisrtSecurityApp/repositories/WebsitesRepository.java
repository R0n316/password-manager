package ru.alex.FisrtSecurityApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.FisrtSecurityApp.models.Person;
import ru.alex.FisrtSecurityApp.models.Website;

import java.util.List;

@Repository
public interface WebsitesRepository extends JpaRepository<Website,Integer> {
    List<Website> findByPerson(Person person);
    Website findByWebsiteId(int websiteId);
}
