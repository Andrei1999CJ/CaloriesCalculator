package coj.and.CaloriesCalculator.aliments;

import coj.and.CaloriesCalculator.exception.AlreadyExistsException;
import coj.and.CaloriesCalculator.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class AlimentsService {
    private final AlimentsRepository alimentsRepository;

    public void addAliment(AlimentsDto aliment) {
        if (alimentsRepository.count() == 0) {
            Aliments aliments1 = new Aliments(aliment.name(),aliment.calories(), aliment.protein(), aliment.carbs(),
                    aliment.fat(), aliment.fiber());
            alimentsRepository.save(aliments1);
        } else {
            alimentsRepository.findAll().forEach((aliments -> {
                if (aliments.getName().equals(aliment.name())) {
                    throw new AlreadyExistsException("This aliment already exists");
                } else {
                    Aliments aliments1 = new Aliments(aliment.name(), aliment.calories(), aliment.protein(), aliment.carbs(),
                            aliment.fat(), aliment.fiber());
                    alimentsRepository.save(aliments1);
                }
            }));
        }
    }

    public void removeAliment(String alimentName) {
        alimentsRepository.getAlimentByName(alimentName).ifPresentOrElse((aliment) -> {
            alimentsRepository.deleteAlimentByName(aliment.getName());
        }, () -> {
            throw new NotFoundException("This aliment doesn't exist");
        });
    }

    public List<AlimentsDto> getAllAliments() {
        List<AlimentsDto> alimentsDtoList = new ArrayList<AlimentsDto>();
        alimentsRepository.findAll().forEach(aliment -> {
            alimentsDtoList.add(new AlimentsDto(aliment.getName(), aliment.getCalories(), aliment.getProtein(),
            aliment.getCarbs(), aliment.getFat(), aliment.getFiber()));
        });
        return alimentsDtoList;
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
}
