package com.Proiect.Proiect.services;

import com.Proiect.Proiect.entities.User;
import com.Proiect.Proiect.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder {
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        if (userRepository.count() == 0) {
            User user1 = new User();
            user1.setName("Vlad Marinas");
            user1.setEmail("marinasvlad@gmail.com");
            user1.setPassword("Parola123");
            user1.setNameId("marinasvlad");

            User user2 = new User();
            user2.setName("Ion Ionescu");
            user2.setEmail("ionescu@test.com");
            user2.setPassword("Parola123");
            user2.setNameId("ionionescu");

            User user3 = new User();
            user3.setName("Radu Popescu");
            user3.setEmail("radupopescu@test.com");
            user3.setPassword("Parola123");
            user3.setNameId("radupopescu");

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
        }
    }
}
