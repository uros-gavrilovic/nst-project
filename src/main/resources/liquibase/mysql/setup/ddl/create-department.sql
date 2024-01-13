CREATE TABLE tbl_department
(
    id            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    name          VARCHAR(255) NOT NULL,

    supervisor_id BIGINT UNSIGNED,
    secretary_id  BIGINT UNSIGNED,

    PRIMARY KEY (id)
#     CONSTRAINT supervisor_fk FOREIGN KEY (supervisor_id) REFERENCES tbl_member (id),
#     CONSTRAINT secretary_fk FOREIGN KEY (secretary_id) REFERENCES tbl_member (id),
) 
