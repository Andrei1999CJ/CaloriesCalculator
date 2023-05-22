package coj.and.CaloriesCalculator.userstats;

import java.math.BigDecimal;

public record UserStatsRequestDto(BigDecimal calories, BigDecimal protein, BigDecimal carbs, BigDecimal fat, BigDecimal fiber) {
}
