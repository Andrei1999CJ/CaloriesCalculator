package coj.and.CaloriesCalculator.useraccounts;

import lombok.Builder;

@Builder
public record AccountInfoDto(String email, String token) {
}
