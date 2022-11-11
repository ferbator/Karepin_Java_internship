package org.ferbator.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@ToString
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class InputDTO {
    @NotEmpty(message = "User Id can't be null")
    @Pattern(message = "Attempt to enter a numeric value in the user id", regexp = "[0-9]+")
    public String user_id;
    @NotEmpty(message = "Group Id can't be null")
    @Pattern(message = "Attempt to enter a numeric value in the group id", regexp = "[0-9]+")
    public String group_id;

}
