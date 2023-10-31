package coj.and.CaloriesCalculator.aliments;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AlimentsRepositoryTest {

    @Autowired
    private AlimentsRepository underTest;

    @Test
    void itShouldGetAllAlimentsLikeKeyword() {
        // given
        Aliments aliment1 = new Aliments("Rosii", BigDecimal.valueOf(0),
                BigDecimal.valueOf(0),
                BigDecimal.valueOf(0),
                BigDecimal.valueOf(0),
                BigDecimal.valueOf(0));
        Aliments aliment2 = new Aliments("Castraveti", BigDecimal.valueOf(0),
                BigDecimal.valueOf(0),
                BigDecimal.valueOf(0),
                BigDecimal.valueOf(0),
                BigDecimal.valueOf(0));
        Aliments aliment3 = new Aliments("Ceapa", BigDecimal.valueOf(0),
                BigDecimal.valueOf(0),
                BigDecimal.valueOf(0),
                BigDecimal.valueOf(0),
                BigDecimal.valueOf(0));
        underTest.saveAll(List.of(aliment1, aliment2, aliment3));

        // when
        List<Aliments> list = underTest.getAllAlimentsLike("rosii");

        //then
        List<Aliments> expected = List.of(aliment1);

        assertThat(expected.get(0).getName()).isEqualTo(list.get(0).getName());

    }
}