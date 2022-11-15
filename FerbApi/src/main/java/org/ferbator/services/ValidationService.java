package org.ferbator.services;

import org.apache.commons.collections4.CollectionUtils;
import org.ferbator.data.InputData;
import org.ferbator.services.tools.ValidationException;
import org.ferbator.services.tools.Violation;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;
import java.util.List;
import java.util.Set;

@Service
//@RequiredArgsConstructor
public class ValidationService {
    private final Validator validator;

    public ValidationService() {
        this.validator = new Validator() {
            @Override
            public <T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups) {
                return null;
            }

            @Override
            public <T> Set<ConstraintViolation<T>> validateProperty(T object, String propertyName, Class<?>... groups) {
                return null;
            }

            @Override
            public <T> Set<ConstraintViolation<T>> validateValue(Class<T> beanType, String propertyName, Object value, Class<?>... groups) {
                return null;
            }

            @Override
            public BeanDescriptor getConstraintsForClass(Class<?> clazz) {
                return null;
            }

            @Override
            public <T> T unwrap(Class<T> type) {
                return null;
            }

            @Override
            public ExecutableValidator forExecutables() {
                return null;
            }
        };
    }

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
