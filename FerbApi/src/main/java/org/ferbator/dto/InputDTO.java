package org.ferbator.dto;

import lombok.*;

@ToString
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class InputDTO {
    public String user_id;
    public String group_id;

}
