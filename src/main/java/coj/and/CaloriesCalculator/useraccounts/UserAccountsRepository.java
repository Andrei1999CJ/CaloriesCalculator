package coj.and.CaloriesCalculator.useraccounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface UserAccountsRepository extends JpaRepository<UserAccounts, UUID> {
     @Query(value = "SELECT u FROM UserAccounts u WHERE u.email = ?1")
     Optional<UserAccounts> getUserByEmail(String email);
     @Query(value = "SELECT u.uuid FROM UserAccounts u WHERE u.email = ?1")
     Optional<UUID> getUUIDByEmail(String email);
}
