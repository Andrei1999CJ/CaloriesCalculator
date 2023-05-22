package coj.and.CaloriesCalculator.userstats;

import coj.and.CaloriesCalculator.useraccounts.UserAccounts;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "UserStats")
@Table(name = "user_stats")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserStats {
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "UUID",
            referencedColumnName = "UUID",
            foreignKey = @ForeignKey(name = "UUID_fk")
    )
    private UserAccounts userAccounts;
    @Column(name = "calories", nullable = false, columnDefinition = "REAL")
    private BigDecimal calories;
    @Column(name = "protein", nullable = false, columnDefinition = "REAL")
    private BigDecimal protein;
    @Column(name = "carbs", nullable = false, columnDefinition = "REAL")
    private BigDecimal carbs;
    @Column(name = "fat", nullable = false, columnDefinition = "REAL")
    private BigDecimal fat;
    @Column(name = "fiber", nullable = false, columnDefinition = "REAL")
    private BigDecimal fiber;

    public UserStats(BigDecimal calories, BigDecimal protein, BigDecimal carbs, BigDecimal fat, BigDecimal fiber) {
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.fiber = fiber;
    }

    @Override
    public String toString() {
        return "UserStats{" +
                "userAccounts=" + userAccounts +
                ", calories=" + calories +
                ", protein=" + protein +
                ", carbs=" + carbs +
                ", fat=" + fat +
                ", fiber=" + fiber +
                '}';
    }
}
