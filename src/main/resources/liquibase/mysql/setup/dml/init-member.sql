INSERT INTO tbl_member (first_name, last_name, academic_title, education_title, scientific_field, department_id)
VALUES
    ('John', 'Doe', 'PROFESSOR', 'DOCTOR', 'Computer Science',
     (SELECT id FROM tbl_department WHERE name = 'department-1')),

    ('Jane', 'Smith', 'ASSOCIATE_PROFESSOR', 'MASTER', 'Physics',
     (SELECT id FROM tbl_department WHERE name = 'department-1')),

    ('Alice', 'Johnson', 'ASSISTANT_PROFESSOR', 'DOCTOR', 'Biology',
     (SELECT id FROM tbl_department WHERE name = 'department-2')));
