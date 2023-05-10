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
@ToString
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
}
