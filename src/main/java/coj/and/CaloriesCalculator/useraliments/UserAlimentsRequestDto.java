package coj.and.CaloriesCalculator.useraliments;

import java.math.BigDecimal;

public record UserAlimentsRequestDto(String email, String alimentName, BigDecimal quantity) {
}
