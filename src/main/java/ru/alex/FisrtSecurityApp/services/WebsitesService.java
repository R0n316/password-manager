package ru.alex.FisrtSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.FisrtSecurityApp.models.Person;
import ru.alex.FisrtSecurityApp.models.Website;
import ru.alex.FisrtSecurityApp.repositories.WebsitesRepository;

import java.util.List;

@Service
public class WebsitesService {
    private final WebsitesRepository websitesRepository;
    private final PersonDetailsService personDetailsService;
    @Autowired
    public WebsitesService(WebsitesRepository websitesRepository, PersonDetailsService personDetailsService) {
        this.websitesRepository = websitesRepository;
        this.personDetailsService = personDetailsService;
    }

    public List<Website> findByPerson(Person person){
        return websitesRepository.findByPerson(person);
    }

    public Website findById(int websiteId){
        return websitesRepository.findByWebsiteId(websiteId);
    }
    public void update(int websiteId, Website website){
        website.setWebsiteId(websiteId);
        website.setPerson(personDetailsService.getAuthenticatedPerson());
        websitesRepository.save(website);
    }
    public void save(Website website){
        website.setPerson(personDetailsService.getAuthenticatedPerson());
        websitesRepository.save(website);
    }
    public void delete(int websiteId){
        websitesRepository.deleteById(websiteId);
    }
}
