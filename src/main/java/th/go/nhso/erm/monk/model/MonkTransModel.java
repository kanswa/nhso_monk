package th.go.nhso.erm.monk.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import th.go.nhso.erm.monk.entity.MonkTrans;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonkTransModel {

    @NotNull
    String refId;

    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    public MonkTransModel(MonkTrans m) {
        this.setRefId(m.getRefId());
        this.setFirstName(m.getFirstName());
        this.setLastName(m.getLastName());
    }
}
