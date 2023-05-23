package coj.and.CaloriesCalculator.aliments;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class AlimentsDtoMapper implements Function<Aliments, AlimentsDto> {
    @Override
    public AlimentsDto apply(Aliments aliments) {
        return new AlimentsDto(
                aliments.getName(),
                aliments.getCalories(),
                aliments.getProtein(),
                aliments.getCarbs(),
                aliments.getFat(),
                aliments.getFiber()
        );
    }
}
