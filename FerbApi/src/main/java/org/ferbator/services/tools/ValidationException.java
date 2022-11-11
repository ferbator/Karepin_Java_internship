package org.ferbator.services.tools;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ValidationException extends Exception {

    @Getter
    private final List<Violation> violations;
}
