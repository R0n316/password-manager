package ru.alex.FisrtSecurityApp.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorsController implements ErrorController {
    @GetMapping//("/403")
    public String error403Page(HttpServletRequest request){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if(status!=null){
            int statusCode = Integer.parseInt(status.toString());
            if(statusCode == HttpStatus.FORBIDDEN.value()){
                return "errors/error403";
            }
            else if(statusCode == HttpStatus.NOT_FOUND.value()){
                return "errors/error404";
            }
        }
        return "web-info/index";
    }
}
