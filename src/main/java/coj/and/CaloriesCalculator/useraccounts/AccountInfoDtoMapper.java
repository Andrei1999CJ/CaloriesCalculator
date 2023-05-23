package coj.and.CaloriesCalculator.useraccounts;

import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class AccountInfoDtoMapper implements Function<UserAccounts, AccountInfoDto> {
    @Override
    public AccountInfoDto apply(UserAccounts userAccounts) {
        return new AccountInfoDto(userAccounts.getEmail(), userAccounts.getGender());
    }
}
