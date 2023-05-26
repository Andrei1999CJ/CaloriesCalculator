package coj.and.CaloriesCalculator.annotations.NoInjection;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Locale;

public class NoInjectionImpl implements ConstraintValidator<NoInjection, String> {

    @Override
    public boolean isValid(String word, ConstraintValidatorContext constraintValidatorContext) {
        return verifyStringIfOk(word.toLowerCase());
    }

    private boolean verifyStringIfOk(String word) {
        if (word.contains("<") || word.contains(">") || word.contains(" select ") || word.contains(" where ")
            || word.contains( " like ") || word.contains(";") || word.contains("//") || word.contains("%")
        ) {
            return false;
        }
        return true;
    }
}
