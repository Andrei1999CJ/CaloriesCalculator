package coj.and.CaloriesCalculator;

import jakarta.persistence.*;
import lombok.*;

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
    private String name;
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
    @OneToMany(mappedBy = "aliments", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserAliments> userAliments = new ArrayList<UserAliments>();
}
