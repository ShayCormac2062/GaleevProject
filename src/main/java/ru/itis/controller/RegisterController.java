package ru.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.forms.UserForm;
import ru.itis.models.User;
import ru.itis.services.UsersService;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

@Controller
public class RegisterController {

    @Autowired
    @Qualifier("usersService")
    private UsersService usersService;

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public ModelAndView init() {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "register")
    public void register(UserForm userForm) {

        usersService.register(userForm);
    }
}
