package coj.and.CaloriesCalculator.aliments;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/aliment")
@AllArgsConstructor
public class AlimentsController {
    private final AlimentsService alimentsService;
    @PostMapping
    public void addAliment(@Valid @RequestBody AlimentsDto aliments) {
        alimentsService.addAliment(aliments);
    }
    @DeleteMapping(path = "/{alimentName}")
    public void deleteAliment(@Valid @PathVariable(name = "alimentName") String name) {
        alimentsService.removeAliment(name);
    }
    @GetMapping(path = "/all")
    public List<AlimentsDto> getAllAliments() {
        return alimentsService.getAllAliments();
    }
}
