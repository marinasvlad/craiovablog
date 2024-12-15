package com.Proiect.Proiect.repositories;

import com.Proiect.Proiect.entities.Articol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticolRepository extends JpaRepository<Articol, Long> {
    List<Articol> findAllByUserId(Long userId);
}
