package coj.and.CaloriesCalculator.useraliments;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class UserAlimentsId implements Serializable {

    @Column(name = "user_id")
    private UUID userAccountsId;
    @Column(name = "aliments_id")
    private Long alimentsId;

    public UserAlimentsId(UUID userAccountsId, Long alimentsId) {
        this.userAccountsId = userAccountsId;
        this.alimentsId = alimentsId;
    }
}
