package coj.and.CaloriesCalculator.useraliments;

import coj.and.CaloriesCalculator.useraccounts.UserAccounts;
import coj.and.CaloriesCalculator.aliments.Aliments;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "UserAliments")
@Table(name = "user_aliments")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserAliments {
    @EmbeddedId
    private UserAlimentsId userAlimentsId;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("userAccountsId")
    @JoinColumn(name = "user_id",
            referencedColumnName = "UUID",
            foreignKey = @ForeignKey(name = "user_id_fk")
    )
    private UserAccounts userAccounts;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("alimentsId")
    @JoinColumn(name = "aliments_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "aliments_id_fk")
    )
    private Aliments aliments;
    @Min(value = 0)
    @Column(name = "quantity", nullable = false, columnDefinition = "REAL")
    private BigDecimal quantity;

    public UserAliments(UserAlimentsId userAlimentsId, BigDecimal quantity) {
        this.userAlimentsId = userAlimentsId;
        this.quantity = quantity;
    }

    public UserAliments(UserAlimentsId userAlimentsId) {
        this.userAlimentsId = userAlimentsId;
    }

    public UserAliments(UserAccounts userAccounts, Aliments aliments) {
        this.userAccounts = userAccounts;
        this.aliments = aliments;
    }

    public UserAliments(UserAccounts userAccounts, Aliments aliments, BigDecimal quantity) {
        this.userAccounts = userAccounts;
        this.aliments = aliments;
        this.quantity = quantity;
    }

    public UserAliments(UserAlimentsId userAlimentsId, UserAccounts userAccounts, Aliments aliments, BigDecimal quantity) {
        this.userAlimentsId = userAlimentsId;
        this.userAccounts = userAccounts;
        this.aliments = aliments;
        this.quantity = quantity;
    }
}
