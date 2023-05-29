package coj.and.CaloriesCalculator.useraccounts;

import coj.and.CaloriesCalculator.annotations.NoInjection.NoInjection;
import coj.and.CaloriesCalculator.useraliments.UserAliments;
import coj.and.CaloriesCalculator.userstats.UserStats;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
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
@Builder
public class UserAccounts implements UserDetails {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "UUID", nullable = false, columnDefinition = "UUID")
    private UUID uuid;
    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "First name should not be blank")
    @NoInjection(message = "Illegal characters or words")
    private String firstName;
    @Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "Last name should not be blank")
    @NoInjection(message = "Illegal characters or words")
    private String lastName;
    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "Password should not be empty")
    @NoInjection(message = "Illegal characters or words")
    private String password;
    @Column(name = "email", nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "Email should not be empty")
    @Email(message = "This is not an email")
    @NoInjection(message = "Illegal characters or words")
    private String email;
    @Column(name = "gender", nullable = false, columnDefinition = "TEXT CHECK(gender IN('MALE', 'FEMALE'))")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "role", nullable = false, columnDefinition = "TEXT CHECK(gender IN('MALE', 'FEMALE'))")
    @Enumerated(EnumType.STRING)
    private Role role;
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

    public UserAccounts(String firstName, String lastName, String password, String email, Gender gender, UserStats userStats) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.userStats = userStats;
    }

    public UserAccounts(String firstName, String lastName, String password, String email, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "UserAccounts{" +
                "uuid=" + uuid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
