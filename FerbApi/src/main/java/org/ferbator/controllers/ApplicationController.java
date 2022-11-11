package org.ferbator.controllers;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.ferbator.dto.InputDTO;
import org.ferbator.dto.OutputDTO;
import org.ferbator.services.ApplicationService;
import org.ferbator.services.tools.ValidationException;
import org.ferbator.services.tools.Violation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fer.com")
class ApplicationController {
    private final ApplicationService service;

    public ApplicationController(ApplicationService service) {
        this.service = service;
    }

    @GetMapping(value = "/user.getUserStatus")
    public String getUserStatus(@RequestParam String user_id, @RequestParam String token)
            throws ClientException, ApiException {
        return service.getUserStatus(user_id, token);
    }

    @PostMapping(value = "/user.getFIOAndGroupMembership")
    public ResponseEntity<OutputDTO> getFIOAndMembership(@RequestParam String token, @RequestBody InputDTO inputDTO)
            throws ValidationException, ClientException, ApiException {

        return ResponseEntity.ok(service.getFIOAndMembership(token, inputDTO));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<List<Violation>> handleException(ValidationException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getViolations());
    }
}
