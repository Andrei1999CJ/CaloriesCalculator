package coj.and.CaloriesCalculator;


import coj.and.CaloriesCalculator.aliments.AlimentsDto;
import coj.and.CaloriesCalculator.aliments.AlimentsService;
import coj.and.CaloriesCalculator.useraccounts.*;
import coj.and.CaloriesCalculator.useraliments.UserAlimentsDeleteRequestDto;
import coj.and.CaloriesCalculator.useraliments.UserAlimentsDto;
import coj.and.CaloriesCalculator.useraliments.UserAlimentsRequestDto;
import coj.and.CaloriesCalculator.useraliments.UserAlimentsService;
import coj.and.CaloriesCalculator.userstats.UserStatsRequestDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping(path = "api/v1")
@AllArgsConstructor
public class CaloriesCalculatorApplication {
	private final UserAccountsRepository userAccountsRepository;
	private final AlimentsService alimentsService;
	private final UserAccountsService userAccountsService;

	private final UserAlimentsService userAlimentsService;

	public static void main(String[] args) {
		SpringApplication.run(CaloriesCalculatorApplication.class, args);
	}
	@PostMapping(path = "/user")
	public void addUser(@Valid @RequestBody UserAccountsDto userAccountsDto) throws NoSuchAlgorithmException {
		userAccountsService.createUser(userAccountsDto);
	}
	@GetMapping(path = "user/login")
	public AccountInfoDto logInUser(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) throws NoSuchAlgorithmException {
		return userAccountsService.logInUser(new UserAccountsLogInRequestDto(email, password));
	}
	@PostMapping(path = "/aliment")
	public void addAliment(@RequestBody AlimentsDto aliments) {
		alimentsService.addAliment(aliments);
	}
	@DeleteMapping(path = "/aliment/{alimentName}")
	public void deleteAliment(@PathVariable(name = "alimentName") String name) {
		alimentsService.removeAliment(name);
	}
	@GetMapping(path = "/aliment/all")
	public List<AlimentsDto> getAllAliments() {
		return alimentsService.getAllAliments();
	}

	@GetMapping(path = "/userStats/{userEmail}")
	public UserStatsRequestDto getUserStats(@PathVariable(name = "userEmail") String email) {
		UserAccounts userAccounts = userAccountsRepository.getUserByEmail(email).orElse(null);
		return new UserStatsRequestDto(userAccounts.getUserStats().getCalories(),
				userAccounts.getUserStats().getProtein(), userAccounts.getUserStats().getCarbs(),
				userAccounts.getUserStats().getFat(), userAccounts.getUserStats().getFiber());
	}

	@GetMapping(path = "/userAliment/all/{email}")
	public List<UserAlimentsDto> GetAllUserAliments(@PathVariable(name = "email") String email) {
		return userAlimentsService.getUserAlimentsByUserEmail(email);
	}

	@PostMapping(path = "/userAliment")
	public void addUserAliment(@RequestBody UserAlimentsRequestDto userAlimentsRequestDto) {
		userAlimentsService.addUserAlimentByUserEmailAndAlimentName(userAlimentsRequestDto);
	}

	@DeleteMapping(path = "/userAliment/all/{email}")
	public void DeleteAllUserAliments(@PathVariable(name = "email") String email) {
		userAlimentsService.removeUserAlimentsByUserEmail(email);
	}

	@DeleteMapping(path = "/userAliment")
	public void DeleteUserAliment(@RequestParam(name = "email") String email, @RequestParam(name = "alimentName") String alimentName) {
		userAlimentsService.removeUserAlimentByUserEmailAndAlimentName(new UserAlimentsDeleteRequestDto(alimentName, email));
	}


}
