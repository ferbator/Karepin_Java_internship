package org.ferbator.controllers;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.ferbator.dto.InputDTO;
import org.ferbator.dto.OutputDTO;
import org.ferbator.services.ApplicationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fer.com")
class ApplicationController {
    private final ApplicationService service;

    public ApplicationController(ApplicationService service) {
        this.service = service;
    }

    @GetMapping(value = "/user.getUserStatus")
    public String getUserStatus(@RequestParam String user_id, @RequestParam String token) throws ClientException, ApiException {
        return service.getUserStatus(user_id, token);
    }

    @PostMapping(value = "/user.getFIOAndGroupMembership")
    public OutputDTO getFIOAndMembership(@RequestParam String token, @RequestBody InputDTO inputDTO) throws ClientException, ApiException {
        return service.getFIOAndMembership(token, inputDTO);
    }
}
