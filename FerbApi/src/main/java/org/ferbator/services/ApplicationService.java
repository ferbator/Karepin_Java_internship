package org.ferbator.services;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.groups.responses.IsMemberUserIdsResponse;
import com.vk.api.sdk.objects.users.Fields;
import com.vk.api.sdk.objects.users.responses.GetResponse;
import com.vk.api.sdk.queries.users.UsersGetQuery;
import org.ferbator.data.InputData;
import org.ferbator.data.OutputData;
import org.ferbator.services.tools.ValidationException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
    private final VkApiClient vk;
    private static final Integer APP_ID = 51466549; //ID приложения
    private final ValidationService validationService;

    public ApplicationService(ValidationService validationService) {
        this.validationService = validationService;
        this.vk = new VkApiClient(new HttpTransportClient());
    }

    public String getUserStatus(String userid, String token) throws ClientException, ApiException {
        ServiceActor actor = new ServiceActor(APP_ID, token);
        UsersGetQuery usersGetQuery;
        usersGetQuery = vk.users().get(actor).userIds(userid);
        return usersGetQuery.fields(Fields.STATUS)
                .execute()
                .get(0)
                .getStatus();
    }

    public OutputData getFIOAndMembership(String token, InputData inputData) throws ClientException, ApiException, ValidationException {
        ServiceActor actor = new ServiceActor(APP_ID, token);
        OutputData outputData = null;
        GetResponse getResponse;
        IsMemberUserIdsResponse isMemberUserIdsResponse;

        if (validationService.isValidInput(inputData)) {
            getResponse = vk.users()
                    .get(actor)
                    .userIds(inputData.user_id)
                    .execute()
                    .get(0);

            isMemberUserIdsResponse = vk.groups()
                    .isMemberWithUserIds(actor, inputData.group_id, Integer.valueOf(inputData.user_id))
                    .execute()
                    .get(0);

            outputData = new OutputData(getResponse.getFirstName(),
                    getResponse.getLastName(),
                    getResponse.getNickname(),
                    isMemberUserIdsResponse.isMember());
        }

        return outputData;
    }
}
