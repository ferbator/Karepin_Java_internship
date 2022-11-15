package org.ferbator.services;

import org.ferbator.dto.InputDTO;
import org.ferbator.services.tools.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;
import java.util.Set;

import org.junit.jupiter.api.Assertions;

public class ApplicationServiceTest {
    Validator validator;
    ValidationService validationService;
    ApplicationService service;

    private InputDTO inputDTO;

    @BeforeEach
    public void setUp() {
        validator = new Validator() {
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
        validationService = new ValidationService(validator);
        service = new ApplicationService(validationService);
        inputDTO = new InputDTO();
        inputDTO.setUser_id("1ф");
        inputDTO.setGroup_id("197787078");
    }

    @Test
    public void checkValidationException() {
        try {
            service.getFIOAndMembership("be9e33d1be9e33d1be9e33d13dbd8f62e4bbe9ebe9e33d1ddfb032e8a7aafbcb99c3160", inputDTO);
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), ValidationException.class);
        }
    }
}
