package ru.alex.FisrtSecurityApp.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alex.FisrtSecurityApp.models.Person;
import ru.alex.FisrtSecurityApp.models.Website;
import ru.alex.FisrtSecurityApp.services.PersonDetailsService;
import ru.alex.FisrtSecurityApp.services.WebsitesService;

@Controller
@RequestMapping("/web-info")
public class WebsiteController {
    private final WebsitesService websitesService;

    private final PersonDetailsService personDetailsService;

    @Autowired
    public WebsiteController(WebsitesService websitesService, PersonDetailsService personDetailsService) {
        this.websitesService = websitesService;
        this.personDetailsService = personDetailsService;
    }


    @GetMapping
    public String index(Model model){

        model.addAttribute("websites",websitesService.findByPerson
                (personDetailsService.getAuthenticatedPerson()));
        return "web-info/index";
    }

    @Transactional
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int websiteId, Model model){
        Website website = websitesService.findById(websiteId);
        Person person = personDetailsService.getAuthenticatedPerson();
        if(website.getPerson().getPersonId()!=person.getPersonId()){
            return "redirect:/error/403";
        }
        model.addAttribute("website",websitesService.findById(websiteId));
        return "web-info/show";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int websiteId,@ModelAttribute("website") @Valid Website website,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "web-info/show";
        }
        websitesService.update(websiteId,website);
        return "redirect:/web-info";
    }


    @GetMapping("/new")
    public String addWebsite(@ModelAttribute("website") Website website){
        return "web-info/new";
    }

    @PostMapping
    public String save(@ModelAttribute @Valid Website website, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "web-info/new";
        }
        websitesService.save(website);
        return "redirect:/web-info";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int websiteId){
        websitesService.delete(websiteId);
        return "redirect:/web-info";
    }
}
