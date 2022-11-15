package org.ferbator.services;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.services.tools.VkApiException;
import org.ferbator.data.InputData;
import org.ferbator.data.OutputData;
import org.ferbator.services.tools.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationServiceTest {

    ApplicationService service;

    public InputData inputData1;

    @BeforeEach
    void setUp() {
        service = new ApplicationService(new ValidationService(new Validator() {
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
        }));
        inputData1 = new InputData();
        inputData1.setUser_id("78385");
        inputData1.setGroup_id("93559769");
    }

    @Test
    void getUserStatus() throws VkApiException {
        assertEquals("Медвежий агрегат", service.getUserStatus("186870993", "be9e33d1be9e33d1be9e33d13dbd8f62e4bbe9ebe9e33d1ddfb032e8a7aafbcb99c3160"));
    }

    @Test
    void getFIOAndMembership() throws ValidationException, ClientException, ApiException {
        assertEquals(new OutputData("Valery", "Akintsev", null, true),
                service.getFIOAndMembership("be9e33d1be9e33d1be9e33d13dbd8f62e4bbe9ebe9e33d1ddfb032e8a7aafbcb99c3160",
                        inputData1));

    }
}