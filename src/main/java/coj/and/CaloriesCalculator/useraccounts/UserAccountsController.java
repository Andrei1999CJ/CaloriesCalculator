package coj.and.CaloriesCalculator.useraccounts;

import coj.and.CaloriesCalculator.userstats.UserStatsRequestDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(path = "/api/v1")
@AllArgsConstructor
public class UserAccountsController {
    private final UserAccountsService userAccountsService;
    @PostMapping(path = "/user")
    public void addUser(@Valid @RequestBody UserAccountsDto userAccountsDto) throws NoSuchAlgorithmException {
        userAccountsService.createUser(userAccountsDto);
    }
    @GetMapping(path = "user/login")
    public AccountInfoDto logInUser(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) throws NoSuchAlgorithmException {
        return userAccountsService.logInUser( new UserAccountsLogInRequestDto(email, password));
    }


    @GetMapping(path = "/userStats/{userEmail}")
    public UserStatsRequestDto getUserStats(@PathVariable(name = "userEmail") String email) {
        return userAccountsService.getUserStats(email);
    }
}
