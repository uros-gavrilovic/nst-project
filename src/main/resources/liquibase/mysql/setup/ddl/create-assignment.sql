CREATE TABLE tbl_assignment
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    department_id BIGINT,
    member_id BIGINT,
    assignedDate TIMESTAMP,

    FOREIGN KEY (department_id) REFERENCES tbl_department(id),
    FOREIGN KEY (member_id) REFERENCES tbl_member(id)
);