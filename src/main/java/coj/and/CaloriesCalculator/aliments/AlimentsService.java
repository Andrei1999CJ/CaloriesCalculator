package coj.and.CaloriesCalculator.aliments;

import coj.and.CaloriesCalculator.exception.AlreadyExistsException;
import coj.and.CaloriesCalculator.exception.ConflictException;
import coj.and.CaloriesCalculator.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class AlimentsService {
    private final AlimentsRepository alimentsRepository;
    private final AlimentsDtoMapper alimentsDtoMapper;

    public void addAliment(AlimentsDto aliment) {
       alimentsRepository.save(new Aliments(aliment.name(),
               aliment.calories(),
               aliment.protein(),
               aliment.carbs(),
               aliment.fat(),
               aliment.fiber()));
    }

    public void removeAliment(String alimentName) {
        alimentsRepository.getAlimentByName(alimentName).ifPresentOrElse((aliment) -> {
            try {
                alimentsRepository.deleteAlimentByName(aliment.getName());
            } catch(Exception e) {
                throw new ConflictException("You're not allowed to delete this aliment because it's consumed by a person");
            }
        }, () -> {
            throw new NotFoundException("This aliment doesn't exist");
        });
    }

    public List<AlimentsDto> getAllAliments() {
        return alimentsRepository.findAll()
                .stream()
                .map(alimentsDtoMapper)
                .collect(Collectors.toList());
    }

    public Aliments getAlimentByAlimentName(String name) {
        return alimentsRepository.getAlimentByName(name).orElseThrow(() -> {
            throw new NotFoundException("Aliment not found");
        });

    }

    public Long getAlimentIdByAlimentName(String name) {
        return alimentsRepository.getAlimentIdByName(name).orElseThrow(() -> {
            throw new NotFoundException(("Aliment doesn't exist"));
        });
    }

    public List<AlimentsDto> getAlimentsThatContainsKeyWord(String keyword) {
        return alimentsRepository.getAllAlimentsLike(keyword)
                .stream()
                .map(alimentsDtoMapper)
                .collect(Collectors.toList());
    }
}
