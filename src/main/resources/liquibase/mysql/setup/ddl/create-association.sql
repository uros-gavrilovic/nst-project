CREATE TABLE tbl_association
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    department_id BIGINT,
    member_id BIGINT,

    FOREIGN KEY (department_id) REFERENCES tbl_department(id),
    FOREIGN KEY (member_id) REFERENCES tbl_member(id),
    UNIQUE KEY unique_department_member (department_id, member_id)
);