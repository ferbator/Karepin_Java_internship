package org.ferbator.services;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.ferbator.data.InputData;
import org.ferbator.services.tools.ValidationException;
import org.ferbator.services.tools.Violation;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ValidationService {
    private final Validator validator;

    public boolean isValidInput(InputData inputData) throws ValidationException {
        Set<ConstraintViolation<InputData>> constraintViolations = validator.validate(inputData);

        if (CollectionUtils.isNotEmpty(constraintViolations)) {
            throw new ValidationException(buildViolationsList(constraintViolations));
        }
        return true;
    }

    private <T> List<Violation> buildViolationsList(Set<ConstraintViolation<T>> constraintViolations) {
        return constraintViolations.stream()
                .map(violation -> new Violation(
                        violation.getPropertyPath().toString(),
                        violation.getMessage()))
                .toList();
    }
}
