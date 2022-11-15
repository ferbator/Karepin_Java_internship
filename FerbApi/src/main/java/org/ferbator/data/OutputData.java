package org.ferbator.data;

import lombok.*;

@ToString
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class OutputData {
    private String first_name;
    private String last_name;
    private String middle_name;
    private Boolean membership;

    public OutputData(String first_name, String last_name, String middle_name, Boolean membership) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.membership = membership;
    }
}
