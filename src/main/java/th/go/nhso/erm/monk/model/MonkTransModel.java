package th.go.nhso.erm.monk.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
}
