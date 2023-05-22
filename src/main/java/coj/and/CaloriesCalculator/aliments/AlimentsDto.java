package coj.and.CaloriesCalculator.aliments;

import java.math.BigDecimal;

public record AlimentsDto(String name, BigDecimal calories, BigDecimal protein, BigDecimal carbs, BigDecimal fat, BigDecimal fiber) {
}
