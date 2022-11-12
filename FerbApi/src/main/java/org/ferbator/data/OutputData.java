package org.ferbator.data;

import lombok.*;

@ToString
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class OutputData {
    public String first_name;
    public String last_name;
    public String middle_name;
    public Boolean membership;

    public OutputData(String first_name, String last_name, String middle_name, Boolean membership) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.membership = membership;
    }
}
