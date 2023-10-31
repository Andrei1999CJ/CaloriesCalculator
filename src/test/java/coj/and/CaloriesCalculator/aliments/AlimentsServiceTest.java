package coj.and.CaloriesCalculator.aliments;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
class AlimentsServiceTest {
    @Mock
    private AlimentsRepository alimentsRepository;
    private AlimentsDtoMapper alimentsDtoMapper;
    private AlimentsService underTest;

    @BeforeEach
    void setUp() {
        alimentsDtoMapper = new AlimentsDtoMapper();
        underTest = new AlimentsService(alimentsRepository, alimentsDtoMapper);
    }

    @Test
    void canGetAllAlimentsThatContainsKeyWord() {
        // given
        Aliments aliment = new Aliments("Rosii", BigDecimal.valueOf(0), BigDecimal.valueOf(0),
                BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        // when
        given(alimentsRepository.getAllAlimentsLike("rosii")).willReturn(List.of(aliment));
        List<AlimentsDto> list = underTest.getAlimentsThatContainsKeyWord("rosii");
        // then
        verify(alimentsRepository).getAllAlimentsLike("rosii");
        List<AlimentsDto> expected = List.of(new AlimentsDto("Rosii", BigDecimal.valueOf(0), BigDecimal.valueOf(0),
                BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0)));
        assertThat(list.get(0).name()).isEqualTo(expected.get(0).name());
    }
}