package ru.levelp.java.junior.haw;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String tryLogin() {
        return "login";
    }
}
