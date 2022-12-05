package com.jwt.repositories;

import com.jwt.models.Teamsheet;
import com.jwt.models.TeamsheetId;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamsheetRepository extends JpaRepository<Teamsheet, TeamsheetId> {
    List<Teamsheet> findAll();
    boolean existsByFixtureId(Long id);
    boolean existsById(TeamsheetId id);

    List<Teamsheet> findByFixtureId(Long id);
    Optional<Teamsheet> findById(TeamsheetId id);
    List<Teamsheet> findByPlayerId(Long id);






    void deleteById(TeamsheetId id);
}