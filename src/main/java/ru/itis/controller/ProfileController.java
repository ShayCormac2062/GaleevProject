package ru.itis.controller;

import com.sun.deploy.net.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.models.User;
import ru.itis.services.UsersService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


@Controller
public class ProfileController {

    @Autowired
    @Qualifier("usersService")
    private UsersService usersService;

    @RequestMapping(method = RequestMethod.GET, value = "/profile")
    public ModelAndView user(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        User user = usersService.findUserByCookieValue(Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("auth"))
                .findAny().get().getValue());
        modelAndView.addObject("user", user);
        return modelAndView;
    }

}
