package ru.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ru.itis.models.User;
import ru.itis.repositories.AuthRepository;
import ru.itis.repositories.MessageRepository;
import ru.itis.services.UsersService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
public class PostRideController {

    @Autowired
    @Qualifier("usersService")
    private UsersService usersService;

    @Autowired
    @Qualifier("messageRepository")
    private MessageRepository messageRepository;

    @Autowired
    @Qualifier("authRepository")
    private AuthRepository authRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/post_ride")
    public ModelAndView messages(HttpServletRequest req) {
        ModelAndView modelAndView = new ModelAndView();
        User user = usersService.findUserByCookieValue(Arrays.stream(req.getCookies())
                        .filter(cookie -> cookie.getName().equals("auth"))
                        .findAny().get().getValue());
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
