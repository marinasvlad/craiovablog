package com.Proiect.Proiect.repositories;

import com.Proiect.Proiect.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByNameId(String nameId);
}
