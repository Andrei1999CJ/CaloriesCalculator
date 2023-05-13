package coj.and.CaloriesCalculator.useraccounts;

import coj.and.CaloriesCalculator.exception.NotFoundException;
import coj.and.CaloriesCalculator.useraliments.UserAliments;
import coj.and.CaloriesCalculator.userstats.UserStats;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    public void createUser(UserAccountsDto userAccountsDto) throws NoSuchAlgorithmException {
        String hashedPassword = getHashedPassword(userAccountsDto.password());
        UserAccounts userAccounts = new UserAccounts(userAccountsDto.firstName(), userAccountsDto.lastName(),
                hashedPassword, userAccountsDto.email(), userAccountsDto.gender());

        userAccounts.setUserStats(new UserStats(userAccounts, 0.0 ,0.0 ,0.0 ,0.0, 0.0));
        userAccountsRepository.save(userAccounts);
    }

    public AccountInfoDto logInUser(UserAccountsLogInRequestDto userAccountsLogInRequestDto) throws NoSuchAlgorithmException {
        String hashedPassword = getHashedPassword(userAccountsLogInRequestDto.password());
        UserAccounts userAccounts = userAccountsRepository.getUserByEmail(userAccountsLogInRequestDto.email())
                .orElseThrow(() -> {
                    throw new NotFoundException("User doesn't exist");
                });
        if (userAccounts.getPassword().equals(hashedPassword)) {
            return new AccountInfoDto(userAccounts.getEmail(), userAccounts.getGender());
        } else {
            throw new NotFoundException("Log in failed");
        }

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

    private static String getHashedPassword(String password) throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        MessageDigest md = MessageDigest.getInstance("SHA-512");
       // md.update(salt);
        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        String passwordHash = Base64.getEncoder().encodeToString(hashedPassword);
        return passwordHash;
    }
}
