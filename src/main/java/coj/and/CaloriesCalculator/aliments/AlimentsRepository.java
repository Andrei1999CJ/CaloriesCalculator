package coj.and.CaloriesCalculator.aliments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AlimentsRepository extends JpaRepository<Aliments, Long> {
    @Query(value = "SELECT u FROM Aliments u WHERE u.name = ?1")
    Optional<Aliments> getAlimentByName(String name);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM aliments WHERE name = ?1", nativeQuery = true)
    void deleteAlimentByName(String name);

    @Query(value = "SELECT u.id FROM Aliments u WHERE u.name = ?1")
    Optional<Long> getAlimentIdByName(String name);
}
