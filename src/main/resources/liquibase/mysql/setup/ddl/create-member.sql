CREATE TABLE tbl_member
(
    id                  BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    first_name          VARCHAR(100)    NOT NULL,
    last_name           VARCHAR(100)    NOT NULL,

    academic_title_id   BIGINT UNSIGNED,
    education_title_id  BIGINT UNSIGNED,
    scientific_field_id BIGINT UNSIGNED,
    department_id       BIGINT UNSIGNED,

    PRIMARY KEY (id),
    CONSTRAINT academic_title_fk FOREIGN KEY (academic_title_id) REFERENCES tbl_academic_title (id),
    CONSTRAINT education_title_fk FOREIGN KEY (education_title_id) REFERENCES tbl_education_title (id),
    CONSTRAINT scientific_field_fk FOREIGN KEY (scientific_field_id) REFERENCES tbl_scientific_field (id)
#     CONSTRAINT department_fk FOREIGN KEY (department_id) REFERENCES tbl_department (id)
);
