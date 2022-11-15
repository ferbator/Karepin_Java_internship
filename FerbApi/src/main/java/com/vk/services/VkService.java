package com.vk.services;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.groups.responses.IsMemberUserIdsResponse;
import com.vk.api.sdk.objects.users.Fields;
import com.vk.api.sdk.objects.users.responses.GetResponse;
import com.vk.api.sdk.queries.users.UsersGetQuery;
import com.vk.services.tools.VkApiException;
import org.ferbator.data.InputData;
import org.ferbator.data.OutputData;

public class VkService {
    private final VkApiClient vk;
    private final int APP_ID; //ID приложения

    public VkService(int app_id) {
        APP_ID = app_id;
        this.vk = new VkApiClient(new HttpTransportClient());
    }


    public String getUserStatus(String userid, String token) throws VkApiException {
        ServiceActor actor = new ServiceActor(APP_ID, token);
        UsersGetQuery usersGetQuery;
        usersGetQuery = vk.users().get(actor).userIds(userid);
        try {
        return usersGetQuery.fields(Fields.STATUS)
                .execute()
                .get(0)
                .getStatus();
        } catch (ApiException e) {
            throw new VkApiException(e.getMessage(), e.getCause());
        } catch (ClientException e) {
            // Transport layer error
        }
        return userid;
    }

    public OutputData getFIOAndMembership(String token, InputData inputData) throws ClientException, ApiException {
        ServiceActor actor = new ServiceActor(APP_ID, token);
        OutputData outputData;
        GetResponse getResponse;
        IsMemberUserIdsResponse isMemberUserIdsResponse;

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

        return outputData;
    }
}
