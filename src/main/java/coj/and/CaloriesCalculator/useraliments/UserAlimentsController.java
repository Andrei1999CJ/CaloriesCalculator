package coj.and.CaloriesCalculator.useraliments;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/userAliment")
@AllArgsConstructor
public class UserAlimentsController {
    private final UserAlimentsService userAlimentsService;
    @GetMapping(path = "/all/{email}")
    public List<UserAlimentsDto> GetAllUserAliments(@PathVariable(name = "email") String email) {
        return userAlimentsService.getUserAlimentsByUserEmail(email);
    }

    @PostMapping
    public void addUserAliment(@Valid @RequestBody UserAlimentsRequestDto userAlimentsRequestDto) {
        userAlimentsService.addUserAlimentByUserEmailAndAlimentName(userAlimentsRequestDto);
    }

    @PutMapping
    public void updateUserAliment(@Valid @RequestBody UserAlimentsRequestDto userAlimentsRequestDto) {
        userAlimentsService.addUserAlimentQuantityByUserEmailAndAlimentName(userAlimentsRequestDto);
    }

    @DeleteMapping(path = "/all/{email}")
    public void DeleteAllUserAliments(@PathVariable(name = "email") String email) {
        userAlimentsService.removeUserAlimentsByUserEmail(email);
    }

    @DeleteMapping
    public void DeleteUserAliment(@RequestParam(name = "email") String email, @RequestParam(name = "alimentName") String alimentName) {
        userAlimentsService.removeUserAlimentByUserEmailAndAlimentName(new UserAlimentsDeleteRequestDto(alimentName, email));
    }
}
