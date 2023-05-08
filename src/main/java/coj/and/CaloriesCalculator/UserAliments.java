package coj.and.CaloriesCalculator;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "UserAliments")
@Table(name = "user_aliments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserAliments {
    @EmbeddedId
    private UserAlimentsId userAlimentsId;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userAccountsId")
    @JoinColumn(name = "user_id",
            referencedColumnName = "UUID",
            foreignKey = @ForeignKey(name = "user_id_fk")
    )
    private UserAccounts userAccounts;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("alimentsId")
    @JoinColumn(name = "aliments_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "aliments_id_fk")
    )
    private Aliments aliments;
    @Column(name = "quantity", nullable = false, columnDefinition = "REAL")
    private Double quantity;
}
