package coj.and.CaloriesCalculator;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@AllArgsConstructor
@EqualsAndHashCode
public class UserAlimentsId implements Serializable {

    @Column(name = "user_id")
    private UUID userAccountsId;
    @Column(name = "aliments_id")
    private Long alimentsId;
}
