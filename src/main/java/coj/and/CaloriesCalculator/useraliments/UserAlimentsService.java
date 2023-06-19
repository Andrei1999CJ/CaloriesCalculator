package coj.and.CaloriesCalculator.useraliments;

import coj.and.CaloriesCalculator.aliments.Aliments;
import coj.and.CaloriesCalculator.aliments.AlimentsService;
import coj.and.CaloriesCalculator.exception.NotFoundException;
import coj.and.CaloriesCalculator.useraccounts.UserAccounts;
import coj.and.CaloriesCalculator.useraccounts.UserAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserAlimentsService {
    private final UserAlimentsRepository userAlimentsRepository;
    private final UserAccountsService userAccountsService;
    private final AlimentsService alimentsService;
    private final UserAlimentsDtoMapper userAlimentsDtoMapper;

    public List<UserAlimentsDto> getUserAlimentsByUserEmail(String email) {
        return userAlimentsRepository.getAllUserAlimentsByUserId(userAccountsService.getUserUUIDByUserEmail(email))
                .stream()
                .map(userAlimentsDtoMapper)
                .collect(Collectors.toList());
    }

    public void addUserAlimentByUserEmailAndAlimentName(UserAlimentsRequestDto userAlimentsRequestDto) {
        UserAccounts userAccounts = userAccountsService.getUserByUserEmail(userAlimentsRequestDto.email());
        Aliments aliments = alimentsService.getAlimentByAlimentName(userAlimentsRequestDto.alimentName());
        addCaloriesAndMacros(userAlimentsRequestDto.quantity(), userAccounts, aliments);
        UserAlimentsId userAlimentsId = new UserAlimentsId(userAccounts.getUuid(), aliments.getId());
        UserAliments userAliments = new UserAliments(userAlimentsId,userAccounts, aliments, userAlimentsRequestDto.quantity());
        userAlimentsRepository.save(userAliments);

    }

    public void addUserAlimentQuantityByUserEmailAndAlimentName(UserAlimentsRequestDto userAlimentsRequestDto) {
        UserAliments userAliment = userAlimentsRepository.getUserAlimentByUserIdAndAlimentId(
                userAccountsService.getUserUUIDByUserEmail(userAlimentsRequestDto.email()),
                alimentsService.getAlimentIdByAlimentName(userAlimentsRequestDto.alimentName()))
                .orElseThrow(() -> {
                    throw new NotFoundException("This aliment is not consumed");
        });
        addCaloriesAndMacros(userAlimentsRequestDto.quantity(), userAliment.getUserAccounts(), userAliment.getAliments());
        userAliment.setQuantity(userAlimentsRequestDto.quantity().add(userAliment.getQuantity()));
        userAlimentsRepository.save(userAliment);
    }

    public void removeUserAlimentByUserEmailAndAlimentName(UserAlimentsDeleteRequestDto userAlimentsDeleteRequestDto) {
        UserAccounts userAccounts = userAccountsService.getUserByUserEmail(userAlimentsDeleteRequestDto.email());
        Aliments aliments = alimentsService.getAlimentByAlimentName(userAlimentsDeleteRequestDto.alimentName());
        subtractCaloriesAndMacros(userAlimentsRepository.getQuantityByUserIdAndAlimentId(userAccounts.getUuid(), aliments.getId()).orElseThrow(() -> {
            throw new NotFoundException("This user aliment doesn't exist");
                }),
                userAccounts, aliments);
        userAlimentsRepository.deleteById(new UserAlimentsId(userAccounts.getUuid(), aliments.getId()));
    }

    public void removeUserAlimentsByUserEmail(String email) {
        UserAccounts userAccounts = userAccountsService.getUserByUserEmail(email);
        userAccounts.getUserStats().setCalories(BigDecimal.valueOf(0.0));
        userAccounts.getUserStats().setProtein(BigDecimal.valueOf(0.0));
        userAccounts.getUserStats().setCarbs(BigDecimal.valueOf(0.0));
        userAccounts.getUserStats().setFat(BigDecimal.valueOf(0.0));
        userAccounts.getUserStats().setFiber(BigDecimal.valueOf(0.0));
        userAlimentsRepository.deleteAllUserAlimentsByUserId(userAccounts.getUuid());
    }

    private static void addCaloriesAndMacros(BigDecimal quantity, UserAccounts userAccounts, Aliments aliments) {
        BigDecimal newCalories = quantity.multiply(aliments.getCalories()).add(userAccounts.getUserStats().getCalories());
        BigDecimal newProtein = quantity.multiply(aliments.getProtein()).add(userAccounts.getUserStats().getProtein());
        BigDecimal newCarbs = quantity.multiply(aliments.getCarbs()).add(userAccounts.getUserStats().getCarbs());
        BigDecimal newFat = quantity.multiply(aliments.getFat()).add(userAccounts.getUserStats().getFat());
        BigDecimal newFiber = quantity.multiply(aliments.getFiber()).add(userAccounts.getUserStats().getFiber());
        userAccounts.getUserStats().setCalories(newCalories);
        userAccounts.getUserStats().setProtein(newProtein);
        userAccounts.getUserStats().setCarbs(newCarbs);
        userAccounts.getUserStats().setFat(newFat);
        userAccounts.getUserStats().setFiber(newFiber);
    }

    private static void subtractCaloriesAndMacros(BigDecimal quantity, UserAccounts userAccounts, Aliments aliments) {
        BigDecimal newCalories = userAccounts.getUserStats().getCalories().subtract(quantity.multiply(aliments.getCalories()));
        BigDecimal newProtein = userAccounts.getUserStats().getProtein().subtract(quantity.multiply(aliments.getProtein()));
        BigDecimal newCarbs = userAccounts.getUserStats().getCarbs().subtract(quantity.multiply(aliments.getCarbs()));
        BigDecimal newFat = userAccounts.getUserStats().getFat().subtract(quantity.multiply(aliments.getFat()));
        BigDecimal newFiber = userAccounts.getUserStats().getFiber().subtract(quantity.multiply(aliments.getFiber()));
        userAccounts.getUserStats().setCalories(newCalories);
        userAccounts.getUserStats().setProtein(newProtein);
        userAccounts.getUserStats().setCarbs(newCarbs);
        userAccounts.getUserStats().setFat(newFat);
        userAccounts.getUserStats().setFiber(newFiber);
    }

}
