package th.go.nhso.erm.monk.entity;

import jakarta.persistence.*;
import lombok.*;
import th.go.nhso.erm.monk.model.MonkTransModel;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MonkTrans {

    @Id
    @SequenceGenerator(name = "SQ_MONK_TRANS", sequenceName = "SQ_MONK_TRANS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SQ_MONK_TRANS")
    Long id;

    String refId;

    String firstName;

    String lastName;

    String createdBy;
    LocalDateTime createdDt;

    String recordStatus;

    String updatedBy;
    LocalDateTime updatedDt;

    public MonkTrans(MonkTransModel monk) {
        LocalDateTime now = LocalDateTime.now();
        this.refId = monk.getRefId();
        this.firstName = monk.getFirstName();
        this.lastName = monk.getLastName();
        this.createdBy = "SYSTEM";
        this.createdDt = now;
        this.recordStatus = "A";
        this.updatedBy = "SYSTEM";
        this.updatedDt = now;
    }

    public MonkTrans editor() {
        this.updatedBy = "SYSTEM";
        this.updatedDt = LocalDateTime.now();
        return this;
    }
}
