INSERT INTO tbl_assignment (department_id, member_id, assignedDate)
VALUES
    (
        (SELECT id FROM tbl_department WHERE LOWER(NAME) = 'department-1'),
        (SELECT id FROM tbl_member WHERE LOWER(first_name) = 'john' AND LOWER(last_name) = 'doe'),
        '2023-01-01 08:00:00'
    ),
    (
        (SELECT id FROM tbl_department WHERE LOWER(NAME) = 'department-2'),
        (SELECT id FROM tbl_member WHERE LOWER(first_name) = 'jane' AND LOWER(last_name) = 'smith'),
        '2023-01-02 10:30:00'
    ),
    (
        (SELECT id FROM tbl_department WHERE LOWER(NAME) = 'department-1'),
        (SELECT id FROM tbl_member WHERE LOWER(first_name) = 'alice' AND LOWER(last_name) = 'johnson'),
        '2023-01-03 12:45:00'
    );