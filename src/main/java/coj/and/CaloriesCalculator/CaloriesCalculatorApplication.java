package coj.and.CaloriesCalculator;


import coj.and.CaloriesCalculator.exception.NotFoundException;
import coj.and.CaloriesCalculator.useraccounts.UserAccounts;
import coj.and.CaloriesCalculator.useraccounts.UserAccountsRepository;
import coj.and.CaloriesCalculator.aliments.Aliments;
import coj.and.CaloriesCalculator.aliments.AlimentsRepository;
import coj.and.CaloriesCalculator.userstats.UserStats;
import coj.and.CaloriesCalculator.userstats.UserStatsRequestDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
@RestController
@RequestMapping(path = "api/v1")
@AllArgsConstructor
public class CaloriesCalculatorApplication {
	private final UserAccountsRepository userAccountsRepository;
	private final AlimentsRepository alimentsRepository;

	public static void main(String[] args) {
		SpringApplication.run(CaloriesCalculatorApplication.class, args);
	}
	@PostMapping(path = "/user")
	public void addUser(@Valid @RequestBody UserAccounts userAccounts) {
		userAccounts.setUserStats(new UserStats(userAccounts, 0.0, 0.0, 0.0, 0.0, 0.0));
		userAccountsRepository.save(userAccounts);
	}
	@PostMapping(path = "/aliment")
	public void addAliment(@RequestBody Aliments aliments) {
		alimentsRepository.save(aliments);
	}
	@GetMapping(path = "/user/{userEmail}")
	public UUID getUser( @PathVariable(name = "userEmail") String email) {
		return userAccountsRepository.getUUIDByEmail(email).orElseThrow(() -> new NotFoundException("Aici e buba"));
	}

	@GetMapping(path = "/userStats/{userEmail}")
	public UserStatsRequestDto getUserStats(@PathVariable(name = "userEmail") String email) {
		UserAccounts userAccounts = userAccountsRepository.getUserByEmail(email).orElse(null);
		return new UserStatsRequestDto(userAccounts.getUserStats().getCalories(),
				userAccounts.getUserStats().getProtein(), userAccounts.getUserStats().getCarbs(),
				userAccounts.getUserStats().getFat(), userAccounts.getUserStats().getFiber());
	}

	@GetMapping(path = "/aliment/{alimentName}")
	public Optional<Aliments> getAlimentByName(@PathVariable(name = "alimentName") String name) {
		return alimentsRepository.getAlimentByName(name);
	}

}
