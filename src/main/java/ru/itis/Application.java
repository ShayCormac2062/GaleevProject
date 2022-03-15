package ru.itis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itis.models.Message;
import ru.itis.models.User;
import ru.itis.repositories.MessageRepositoryImpl;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryImpl;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Проверка репозитроия с пользователями
        UsersRepository userRepository = context.getBean(UsersRepository.class);
        User user = User.builder()
                .id(0L)
                .firstName("Михаил")
                .lastName("Абрамов")
                .email("Chlen_Mamonta@gmail.ru")
                .password("mishaloh228")
                .build();
        user = userRepository.save(user);
        System.out.println(user.getId());
        System.out.println(userRepository.findUserById(7L).getFirstName());

        MessageRepositoryImpl messageRepository = context.getBean(MessageRepositoryImpl.class);
        Message message = messageRepository.addMessage(
                user,
                "Я хочу ",
                "быть ",
                "стулом Доры!",
                "");
        System.out.println(message.getText() + ", - c." + message.getTitle());

    }
}
