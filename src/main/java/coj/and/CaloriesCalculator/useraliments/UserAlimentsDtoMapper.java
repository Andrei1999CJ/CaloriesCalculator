package coj.and.CaloriesCalculator.useraliments;

import coj.and.CaloriesCalculator.useraccounts.UserAccounts;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class UserAlimentsDtoMapper implements Function<UserAliments, UserAlimentsDto> {
    @Override
    public UserAlimentsDto apply(UserAliments userAliments) {
        return new UserAlimentsDto(userAliments.getAliments().getName(), userAliments.getQuantity());
    }
}
