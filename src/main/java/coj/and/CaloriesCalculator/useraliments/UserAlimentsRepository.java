package coj.and.CaloriesCalculator.useraliments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface UserAlimentsRepository extends JpaRepository<UserAliments, UserAlimentsId> {
    @Query(value = "SELECT * FROM user_aliments WHERE user_id = ?1", nativeQuery = true)
    List<UserAliments> getAllUserAlimentsByUserId(UUID uuid);
}
