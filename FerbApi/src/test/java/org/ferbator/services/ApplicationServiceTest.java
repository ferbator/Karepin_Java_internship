package org.ferbator.services;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.services.tools.VkApiException;
import org.ferbator.data.InputData;
import org.ferbator.data.OutputData;
import org.ferbator.services.tools.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationServiceTest {

    ApplicationService service;

    public InputData inputData1;

    @BeforeEach
    void setUp() {
        service = new ApplicationService(new ValidationService());
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