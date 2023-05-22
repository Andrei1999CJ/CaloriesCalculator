package coj.and.CaloriesCalculator.aliments;

import coj.and.CaloriesCalculator.useraliments.UserAliments;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Aliments")
@Table(name = "aliments", uniqueConstraints = {
        @UniqueConstraint(name = "name_unique_constraint", columnNames = "name")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Aliments {
    @Id
    @SequenceGenerator(name = "aliments_id_generator", sequenceName = "aliments_id_generator", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aliments_id_generator")
    private Long id;
    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "Name should not be blank")
    private String name;
    @Column(name = "calories", nullable = false, columnDefinition = "REAL")
    @Min(value = 0, message = "Calories should not be negative")
    private BigDecimal calories;
    @Column(name = "protein", nullable = false, columnDefinition = "REAL")
    @Min(value = 0, message = "Protein should not be negative")
    private BigDecimal protein;
    @Column(name = "carbs", nullable = false, columnDefinition = "REAL")
    @Min(value = 0, message = "Carbs should not be negative")
    private BigDecimal carbs;
    @Column(name = "fat", nullable = false, columnDefinition = "REAL")
    @Min(value = 0, message = "Fat should not be negative")
    private BigDecimal fat;
    @Column(name = "fiber", nullable = false, columnDefinition = "REAL")
    @Min(value = 0, message = "Fiber should not be negative")
    private BigDecimal fiber;
    @OneToMany(mappedBy = "aliments", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserAliments> userAliments = new ArrayList<UserAliments>();

    public Aliments(String name, BigDecimal calories, BigDecimal protein, BigDecimal carbs, BigDecimal fat, BigDecimal fiber) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.fiber = fiber;
    }
}
