package coj.and.CaloriesCalculator.useraccounts;

import coj.and.CaloriesCalculator.exception.NotFoundException;
import coj.and.CaloriesCalculator.exception.UnauthorizedException;
import coj.and.CaloriesCalculator.userstats.UserStats;
import coj.and.CaloriesCalculator.userstats.UserStatsRequestDto;
import coj.and.CaloriesCalculator.userstats.UserStatsRequestDtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserAccountsService {
    private final UserAccountsRepository userAccountsRepository;
    private final AccountInfoDtoMapper accountInfoDtoMapper;
    private final UserStatsRequestDtoMapper userStatsRequestDtoMapper;

    public void createUser(UserAccountsDto userAccountsDto) throws NoSuchAlgorithmException {
        String hashedPassword = getHashedPassword(userAccountsDto.password());
        UserAccounts userAccounts = new UserAccounts(userAccountsDto.firstName(), userAccountsDto.lastName(),
                hashedPassword, userAccountsDto.email(), userAccountsDto.gender());

        userAccounts.setUserStats(new UserStats(userAccounts,
                BigDecimal.valueOf(0.0) ,
                BigDecimal.valueOf(0.0) ,
                BigDecimal.valueOf(0.0) ,
                BigDecimal.valueOf(0.0),
                BigDecimal.valueOf(0.0)));
        userAccountsRepository.save(userAccounts);
    }

    public AccountInfoDto logInUser(UserAccountsLogInRequestDto userAccountsLogInRequestDto) throws NoSuchAlgorithmException {
        String hashedPassword = getHashedPassword(userAccountsLogInRequestDto.password());
        return userAccountsRepository.findAll().stream()
                .filter(account -> account.getEmail().equals(userAccountsLogInRequestDto.email()) &&
                account.getPassword().equals(hashedPassword))
                .map(accountInfoDtoMapper)
                .findAny().orElseThrow(() -> new UnauthorizedException("User doesn't exists or Wrong credentials"));

    }

    public UserAccounts getUserByUserEmail(String email) {
        return userAccountsRepository.getUserByEmail(email).orElseThrow(() -> {
            throw new NotFoundException("Email doesn't exist");
        });
    }

    public UUID getUserUUIDByUserEmail(String email) {
        return userAccountsRepository.getUUIDByEmail(email).orElseThrow(() -> {
            throw new NotFoundException("Email doesn't exist");
        });
    }

    public UserStatsRequestDto getUserStats(String email) {
        return userAccountsRepository.getUserByEmail(email)
                .map(userStatsRequestDtoMapper)
                .orElseThrow(() -> new NotFoundException("This user doesn't exist"));
    }

    private static String getHashedPassword(String password) throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        MessageDigest md = MessageDigest.getInstance("SHA-512");
       // md.update(salt);
        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hashedPassword);
    }
}
