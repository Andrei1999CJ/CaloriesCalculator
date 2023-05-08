package coj.and.CaloriesCalculator;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "UserAccounts")
@Table(name = "user_accounts", uniqueConstraints = {
        @UniqueConstraint(name = "unique_email_constraint", columnNames = "email")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserAccounts {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "UUID", nullable = false, columnDefinition = "UUID")
    private UUID uuid;
    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
    private String firstName;
    @Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
    private String lastName;
    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;
    @Column(name = "email", nullable = false, columnDefinition = "TEXT")
    private String email;
    @Column(name = "gender", nullable = false, columnDefinition = "TEXT CHECK(gender IN('MALE', 'FEMALE'))")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToOne(mappedBy = "userAccounts", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserStats userStats;
    @OneToMany(mappedBy = "userAccounts", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserAliments> userAliments = new ArrayList<UserAliments>();

    public void addUserAliment(UserAliments userAliment) {
        if (!userAliments.contains(userAliment)) {
            userAliments.add(userAliment);
        }
    }

    public void removeUserAliment(UserAliments userAliment) {
        userAliments.remove(userAliment);
    }
}
