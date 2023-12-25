INSERT INTO tbl_association (department_id, member_id)
VALUES
    (
        (SELECT id FROM tbl_department WHERE LOWER(NAME) = 'department-1'),
        (SELECT id FROM tbl_member WHERE LOWER(first_name) = 'john' AND LOWER(last_name) = 'doe')
    ),
    (
        (SELECT id FROM tbl_department WHERE LOWER(NAME) = 'department-2'),
        (SELECT id FROM tbl_member WHERE LOWER(first_name) = 'jane' AND LOWER(last_name) = 'smith')
    ),
    (
        (SELECT id FROM tbl_department WHERE LOWER(NAME) = 'department-1'),
        (SELECT id FROM tbl_member WHERE LOWER(first_name) = 'alice' AND LOWER(last_name) = 'johnson')
    );