package spring.app.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import spring.app.security.PersonDetails;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/getUserInfo")
    public String info() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        System.out.println(personDetails.getPerson());
        return "hello";
    }
}
