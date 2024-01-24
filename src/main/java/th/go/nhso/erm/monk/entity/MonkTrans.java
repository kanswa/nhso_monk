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
        this.refId = monk.getRefId();
        this.firstName = monk.getFirstName();
        this.lastName = monk.getLastName();
    }

    public MonkTrans initData() {
        LocalDateTime now = LocalDateTime.now();
        this.createdBy = "SYSTEM";
        this.createdDt = now;
        this.recordStatus = "A";
        this.updatedBy = "SYSTEM";
        this.updatedDt = now;
        return this;
    }

    public MonkTrans editData() {
        this.updatedBy = "SYSTEM";
        this.updatedDt = LocalDateTime.now();
        return this;
    }

    /*
     * sql for create table
     *
        CREATE TABLE ERMMONK.MONK_TRANS (
            ID NUMBER(38,0) NOT NULL,
            REF_ID VARCHAR2(30) NOT NULL,
            FIRST_NAME VARCHAR2(100) NULL,
            LAST_NAME VARCHAR2(100) NULL,
            CREATED_BY VARCHAR2(50) NOT NULL,
            CREATED_DT TIMESTAMP NOT NULL,
            RECORD_STATUS VARCHAR2(1) NULL,
            UPDATED_BY VARCHAR2(50) NULL,
            UPDATED_DT TIMESTAMP NULL,
            CONSTRAINT MONK_TRANS_PK PRIMARY KEY (ID),
            CONSTRAINT "MONK_TRANS_REF_ID_UNIQUE" UNIQUE ("REF_ID")
        )

        CREATE UNIQUE INDEX "ERMMONK"."MONK_TRANS_PK" ON "ERMMONK"."MONK_TRANS" ("ID");
        CREATE UNIQUE INDEX "ERMMONK"."MONK_TRANS_REF_ID_UNIQUE" ON "ERMMONK"."MONK_TRANS" ("REF_ID");

        CREATE SEQUENCE ERMMONK.SQ_MONK_TRANS INCREMENT BY 1 MINVALUE 0 NOCYCLE NOCACHE NOORDER ;
     */
}
