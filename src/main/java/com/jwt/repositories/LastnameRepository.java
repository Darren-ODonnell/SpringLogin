package com.jwt.repositories;

import com.jwt.models.Lastname;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LastnameRepository extends JpaRepository<Lastname, Long> {
    Optional<Lastname> findById(Long id);
    List<Lastname> findByLastname(String lastname);
    List<Lastname> findByLastnameIrish(String lastnameIrish);
    boolean existsByLastname(String lastname);
    boolean existsByLastnameIrish(String lastname);

}
