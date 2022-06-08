package com.jwt.repositories;

import com.jwt.models.Teamsheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamsheetRepository extends JpaRepository<Teamsheet, Long> {
    List<Teamsheet> findAll();
    boolean existsByFixtureId(Long id);
    boolean existsById(Long id);

    Teamsheet findByFixtureId(Long id);
    Optional<Teamsheet> findById(Long id);
    List<Teamsheet> findByPlayerId(Long id);
}