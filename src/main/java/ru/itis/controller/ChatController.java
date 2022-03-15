package ru.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.models.Message;
import ru.itis.models.User;
import ru.itis.repositories.MessageRepository;

import java.util.Arrays;
import java.util.List;

@Controller
public class ChatController {

    @Autowired
    @Qualifier("messageRepository")
    private MessageRepository messageRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/chat")
    public ModelAndView messages() {
        ModelAndView modelAndView = new ModelAndView();
        List<Message> messages = messageRepository.findAllMessages();
        modelAndView.addObject("messages", messages);
        return modelAndView;
    }
}
