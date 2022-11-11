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
import org.ferbator.dto.InputDTO;
import org.ferbator.dto.OutputDTO;
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

    public OutputDTO getFIOAndMembership(String token, InputDTO inputDTO) throws ClientException, ApiException, ValidationException {
        ServiceActor actor = new ServiceActor(APP_ID, token);
        OutputDTO outputDTO = null;
        GetResponse getResponse;
        IsMemberUserIdsResponse isMemberUserIdsResponse;

        if (validationService.isValidInput(inputDTO)) {
            getResponse = vk.users()
                    .get(actor)
                    .userIds(inputDTO.user_id)
                    .execute()
                    .get(0);

            isMemberUserIdsResponse = vk.groups()
                    .isMemberWithUserIds(actor, inputDTO.group_id, Integer.valueOf(inputDTO.user_id))
                    .execute()
                    .get(0);

            outputDTO = new OutputDTO(getResponse.getFirstName(),
                    getResponse.getLastName(),
                    getResponse.getNickname(),
                    isMemberUserIdsResponse.isMember());
        }

        return outputDTO;
    }
}
