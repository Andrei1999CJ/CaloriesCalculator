package coj.and.CaloriesCalculator.useraccounts;

import coj.and.CaloriesCalculator.exception.NotFoundException;
import coj.and.CaloriesCalculator.exception.UnauthorizedException;
import coj.and.CaloriesCalculator.security.JwtService;
import coj.and.CaloriesCalculator.userstats.UserStats;
import coj.and.CaloriesCalculator.userstats.UserStatsRequestDto;
import coj.and.CaloriesCalculator.userstats.UserStatsRequestDtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserAccountsService {
    private final UserAccountsRepository userAccountsRepository;
    private final UserStatsRequestDtoMapper userStatsRequestDtoMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public void createUser(UserAccountsDto userAccountsDto) {
        UserAccounts userAccounts = UserAccounts.builder()
                .firstName(userAccountsDto.firstName())
                .lastName(userAccountsDto.lastName())
                .password(passwordEncoder.encode(userAccountsDto.password()))
                .email(userAccountsDto.email())
                .gender(userAccountsDto.gender())
                .role(Role.USER)
                .build();
        userAccounts.setUserStats(
                UserStats.builder()
                        .calories(BigDecimal.valueOf(0.0))
                        .protein(BigDecimal.valueOf(0.0))
                        .carbs(BigDecimal.valueOf(0.0))
                        .fat(BigDecimal.valueOf(0.0))
                        .fiber(BigDecimal.valueOf(0.0))
                        .userAccounts(userAccounts)
                        .build()
        );
        userAccountsRepository.save(userAccounts);
    }

    public AccountInfoDto logInUser(UserAccountsLogInRequestDto userAccountsLogInRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userAccountsLogInRequestDto.email(),
                        userAccountsLogInRequestDto.password()
                )
        );
        var user = userAccountsRepository.getUserByEmail(userAccountsLogInRequestDto.email())
                .orElseThrow(() -> new UnauthorizedException("User doesn't exists or Wrong credentials"));
        var jwtToken = jwtService.generateToken(user);
        return AccountInfoDto.builder()
                .email(userAccountsLogInRequestDto.email())
                .token(jwtToken)
                .build();
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
}
