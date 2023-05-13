package coj.and.CaloriesCalculator.userstats;

import coj.and.CaloriesCalculator.useraccounts.UserAccounts;
import jakarta.persistence.*;
import lombok.*;

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
    private Double calories;
    @Column(name = "protein", nullable = false, columnDefinition = "REAL")
    private Double protein;
    @Column(name = "carbs", nullable = false, columnDefinition = "REAL")
    private Double carbs;
    @Column(name = "fat", nullable = false, columnDefinition = "REAL")
    private Double fat;
    @Column(name = "fiber", nullable = false, columnDefinition = "REAL")
    private Double fiber;

    public UserStats(Double calories, Double protein, Double carbs, Double fat, Double fiber) {
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
