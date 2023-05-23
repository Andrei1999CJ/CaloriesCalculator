package coj.and.CaloriesCalculator.userstats;

import coj.and.CaloriesCalculator.useraccounts.UserAccounts;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class UserStatsRequestDtoMapper implements Function<UserAccounts, UserStatsRequestDto> {
    @Override
    public UserStatsRequestDto apply(UserAccounts userAccounts) {
        return new UserStatsRequestDto(
                userAccounts.getUserStats().getCalories(),
                userAccounts.getUserStats().getProtein(),
                userAccounts.getUserStats().getCarbs(),
                userAccounts.getUserStats().getFat(),
                userAccounts.getUserStats().getFiber()
        );
    }
}
