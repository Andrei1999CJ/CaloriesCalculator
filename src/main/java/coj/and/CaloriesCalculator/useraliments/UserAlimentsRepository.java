package coj.and.CaloriesCalculator.useraliments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface UserAlimentsRepository extends JpaRepository<UserAliments, UserAlimentsId> {
    @Query(value = "SELECT * FROM user_aliments WHERE user_id = ?1", nativeQuery = true)
    List<UserAliments> getAllUserAlimentsByUserId(UUID uuid);

    @Query(value = "DELETE FROM user_aliments WHERE user_id = ?1", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteAllUserAlimentsByUserId(UUID uuid);
    @Query(value = "SELECT quantity FROM user_aliments WHERE user_id = ?1 AND aliments_id = ?2", nativeQuery = true)
    Optional<BigDecimal> getQuantityByUserIdAndAlimentId(UUID userId, Long alimentId);

    @Query(value = "SELECT * FROM user_aliments WHERE user_id = ?1 AND aliments_id = ?2", nativeQuery = true)
    Optional<UserAliments> getUserAlimentByUserIdAndAlimentId(UUID userId, Long alimentId);

}
