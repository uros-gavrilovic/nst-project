CREATE TABLE tbl_member(
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    academic_title VARCHAR(100) NOT NULL,
    education_title VARCHAR(100) NOT NULL,
    scientific_field VARCHAR(100) NOT NULL,
    department_id BIGINT UNSIGNED,

	PRIMARY KEY (id),
    CONSTRAINT department_fk FOREIGN KEY (department_id) REFERENCES tbl_department(id)
)
