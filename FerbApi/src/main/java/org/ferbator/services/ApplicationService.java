package org.ferbator.services;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.services.VkService;
import com.vk.services.tools.VkApiException;
import org.ferbator.data.InputData;
import org.ferbator.data.OutputData;
import org.ferbator.services.tools.ValidationException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
    private final VkService vkService;
    private static final Integer APP_ID = 51466549; //ID приложения
    private final ValidationService validationService;

    public ApplicationService(ValidationService validationService) {
        this.validationService = validationService;
        this.vkService = new VkService(APP_ID);
    }

    public String getUserStatus(String userId, String token) throws VkApiException {
        return vkService.getUserStatus(userId, token);
    }

    public OutputData getFIOAndMembership(String token, InputData inputData) throws ClientException, ApiException, ValidationException {
        OutputData outputData = null;

        if (validationService.isValidInput(inputData)) {
            outputData = vkService.getFIOAndMembership(token, inputData);
        }

        return outputData;
    }
}
