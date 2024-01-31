package th.go.nhso.erm.monk.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.engine.jdbc.cursor.spi.RefCursorSupport;
import th.go.nhso.erm.monk.model.MonkTransModel;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedStoredProcedureQueries(value = {
        @NamedStoredProcedureQuery(
                name = "MonkTrans.querytest",
                procedureName = "ERMMONK.QUERYTEST",
                resultClasses = Integer.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "in_record_status", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "out_count", type = Integer.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "MonkTrans.querytest2o",
                procedureName = "ERMMONK.QUERYTEST2O",
                resultClasses = {Integer.class, Date.class},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "in_record_status", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "out_count", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "out_time", type = Date.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "MonkTrans.querymonk",
                procedureName = "ERMMONK.QUERYMONK",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "in_record_status", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "out_datas", type = ResultSet.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "out_count", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "out_time", type = Date.class)
                }
        )
})
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

    /*
        CREATE OR REPLACE PROCEDURE ERMMONK.QUERYMONK(in_record_status in monk_trans.record_status%type,
                                           out_datas out sys_refcursor,
                                           out_count out number,
                                           out_time out date)
        IS
        BEGIN
            open out_datas
                for select * from monk_trans where record_status = in_record_status;
            select count(*) into out_count from monk_trans where record_status = in_record_status;
            select sysdate into out_time from dual;
        END QUERYMONK;

        CREATE OR REPLACE PROCEDURE ERMMONK.QUERYTEST(
            in_record_status IN VARCHAR,
            out_count OUT NUMBER)
        IS
        BEGIN
            select count(*) into out_count from monk_trans where record_status = in_record_status;
        END QUERYTEST;

        CREATE OR REPLACE PROCEDURE ERMMONK.QUERYTEST2O(
            in_record_status IN VARCHAR,
            out_count OUT NUMBER,
            out_time out date)
        IS
        BEGIN
            select count(*) into out_count from monk_trans where record_status = in_record_status;
            select sysdate into out_time from dual;
        END QUERYTEST2O;
     */
}
