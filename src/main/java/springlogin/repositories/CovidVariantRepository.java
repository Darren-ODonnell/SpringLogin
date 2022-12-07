package springlogin.repositories;

import springlogin.models.CovidVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CovidVariantRepository extends JpaRepository<CovidVariant, Long> {
    Optional<CovidVariant> findById(Long id);
    Optional<CovidVariant> findByName(String name);
    Boolean existsByName(String name);

}