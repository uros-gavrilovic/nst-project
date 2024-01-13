ALTER TABLE tbl_member
    ADD CONSTRAINT department_fk
        FOREIGN KEY (department_id)
            REFERENCES tbl_department (id);

ALTER TABLE tbl_department
    ADD CONSTRAINT supervisor_fk
        FOREIGN KEY (supervisor_id)
            REFERENCES tbl_member (id);

ALTER TABLE tbl_department
    ADD CONSTRAINT secretary_fk
        FOREIGN KEY (secretary_id)
            REFERENCES tbl_member (id);
