package coj.and.CaloriesCalculator.useraliments;

import coj.and.CaloriesCalculator.aliments.Aliments;
import coj.and.CaloriesCalculator.aliments.AlimentsService;
import coj.and.CaloriesCalculator.exception.NotFoundException;
import coj.and.CaloriesCalculator.useraccounts.UserAccounts;
import coj.and.CaloriesCalculator.useraccounts.UserAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserAlimentsService {
    private final UserAlimentsRepository userAlimentsRepository;
    private final UserAccountsService userAccountsService;
    private final AlimentsService alimentsService;

    public List<UserAlimentsDto> getUserAlimentsByUserEmail(String email) {
        List<UserAlimentsDto> userAlimentsDtos = new ArrayList<>();
        userAlimentsRepository.getAllUserAlimentsByUserId(userAccountsService.getUserUUIDByUserEmail(email))
                .forEach((userAliment -> userAlimentsDtos.add(new UserAlimentsDto(userAliment.getAliments().getName(), userAliment.getQuantity()))));
        return userAlimentsDtos;
    }

    public void addUserAlimentByUserEmailAndAlimentName(UserAlimentsRequestDto userAlimentsRequestDto) {
        UserAccounts userAccounts = userAccountsService.getUserByUserEmail(userAlimentsRequestDto.email());
        Aliments aliments = alimentsService.getAlimentByAlimentName(userAlimentsRequestDto.alimentName());
        addCaloriesAndMacros(userAlimentsRequestDto, userAccounts, aliments);
        UserAlimentsId userAlimentsId = new UserAlimentsId(userAccounts.getUuid(), aliments.getId());
        UserAliments userAliments = new UserAliments(userAlimentsId,userAccounts, aliments, userAlimentsRequestDto.quantity());
        userAlimentsRepository.save(userAliments);

    }

    public void removeUserAlimentByUserEmailAndAlimentName(UserAlimentsDeleteRequestDto userAlimentsDeleteRequestDto) {
        UserAccounts userAccounts = userAccountsService.getUserByUserEmail(userAlimentsDeleteRequestDto.email());
        Aliments aliments = alimentsService.getAlimentByAlimentName(userAlimentsDeleteRequestDto.alimentName());
        subtractCaloriesAndMacros(userAlimentsRepository.getQuantityByUserEmailAndAlimentName(userAccounts.getUuid(), aliments.getId()).orElseThrow(() -> {
            throw new NotFoundException("This user aliment doesn't exist");
                }),
                userAccounts, aliments);
        userAlimentsRepository.deleteById(new UserAlimentsId(userAccounts.getUuid(), aliments.getId()));
    }

    public void removeUserAlimentsByUserEmail(String email) {
        UserAccounts userAccounts = userAccountsService.getUserByUserEmail(email);
        userAccounts.getUserStats().setCalories(0.0);
        userAccounts.getUserStats().setProtein(0.0);
        userAccounts.getUserStats().setCarbs(0.0);
        userAccounts.getUserStats().setFat(0.0);
        userAccounts.getUserStats().setFiber(0.0);
        userAlimentsRepository.deleteAllUserAlimentsByUserId(userAccounts.getUuid());
    }

    private static void addCaloriesAndMacros(UserAlimentsRequestDto userAlimentsRequestDto, UserAccounts userAccounts, Aliments aliments) {
        double newCalories = userAccounts.getUserStats().getCalories() + userAlimentsRequestDto.quantity() * aliments.getCalories();
        double newProtein = userAccounts.getUserStats().getProtein() + userAlimentsRequestDto.quantity() * aliments.getProtein();
        double newCarbs = userAccounts.getUserStats().getCarbs() + userAlimentsRequestDto.quantity() * aliments.getCarbs();
        double newFat = userAccounts.getUserStats().getFat() + userAlimentsRequestDto.quantity() * aliments.getFat();
        double newFiber = userAccounts.getUserStats().getFiber() + userAlimentsRequestDto.quantity() * aliments.getFiber();
        userAccounts.getUserStats().setCalories(newCalories);
        userAccounts.getUserStats().setProtein(newProtein);
        userAccounts.getUserStats().setCarbs(newCarbs);
        userAccounts.getUserStats().setFat(newFat);
        userAccounts.getUserStats().setFiber(newFiber);
    }

    private static void subtractCaloriesAndMacros(Double quantity, UserAccounts userAccounts, Aliments aliments) {
        double newCalories = userAccounts.getUserStats().getCalories() - quantity * aliments.getCalories();
        double newProtein = userAccounts.getUserStats().getProtein() - quantity * aliments.getProtein();
        double newCarbs = userAccounts.getUserStats().getCarbs() - quantity * aliments.getCarbs();
        double newFat = userAccounts.getUserStats().getFat() - quantity * aliments.getFat();
        double newFiber = userAccounts.getUserStats().getFiber() - quantity * aliments.getFiber();
        userAccounts.getUserStats().setCalories(newCalories);
        userAccounts.getUserStats().setProtein(newProtein);
        userAccounts.getUserStats().setCarbs(newCarbs);
        userAccounts.getUserStats().setFat(newFat);
        userAccounts.getUserStats().setFiber(newFiber);
    }

}
