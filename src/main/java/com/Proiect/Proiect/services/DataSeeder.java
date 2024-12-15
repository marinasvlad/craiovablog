package com.Proiect.Proiect.services;

import com.Proiect.Proiect.entities.Articol;
import com.Proiect.Proiect.entities.User;
import com.Proiect.Proiect.repositories.ArticolRepository;
import com.Proiect.Proiect.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class DataSeeder {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticolRepository articolRepository;

    @PostConstruct
    public void init() {
        if (userRepository.count() == 0) {
            User user1 = new User();
            user1.setName("Vlad Marinas");
            user1.setEmail("marinasvlad@gmail.com");
            user1.setPassword("{noop}Parola123");
            user1.setNameId("marinasvlad");
            user1.setRole("ROLE_USER");
            user1.setStatus(true);
            User user2 = new User();
            user2.setName("Ion Ionescu");
            user2.setEmail("ionescu@test.com");
            user2.setPassword("{noop}Parola123");
            user2.setNameId("ionionescu");
            user2.setRole("ROLE_USER");
            user2.setStatus(true);
            User user3 = new User();
            user3.setName("Radu Popescu");
            user3.setEmail("radupopescu@test.com");
            user3.setPassword("{noop}Parola123");
            user3.setNameId("radupopescu");
            user3.setRole("ROLE_USER");
            user3.setStatus(true);
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

            for(int index = 1; index <= 10; index++)
            {
                Articol articol = new Articol();
                articol.setData(new Date());
                articol.setTitlu("Excemplu " + index);
                articol.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam lobortis neque augue, in porttitor dui hendrerit nec. Suspendisse nec feugiat quam. Maecenas hendrerit quam turpis, vel commodo quam fermentum ut. Nullam dapibus mollis purus, in pharetra mi auctor sit amet. Donec pellentesque aliquet purus nec egestas. Sed tristique, nunc vestibulum volutpat euismod, metus velit eleifend mi, id pharetra dolor lectus in est. Nam mollis eget lorem at gravida. Nulla vel elit finibus, aliquam nisl ac, cursus ligula. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; ");
                articol.setUserId(1L);
                articolRepository.save(articol);
            }
        }
    }
}
