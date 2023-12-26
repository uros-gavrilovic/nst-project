INSERT INTO tbl_member (first_name, last_name, academic_title_id, education_title_id, scientific_field_id, department_id)
VALUES
    ('John', 'Doe',
     (SELECT id FROM tbl_academic_title WHERE LOWER(academic_title) = 'professor'),
     (SELECT id FROM tbl_education_title WHERE LOWER(education_title) = 'doctor'),
     (SELECT id FROM tbl_scientific_field WHERE LOWER(scientific_field) = 'physics')
     (SELECT id FROM tbl_department WHERE LOWER(name) = 'department-1')),

    ('Jane', 'Smith',
     (SELECT id FROM tbl_academic_title WHERE LOWER(academic_title) = 'associate_professor'),
     (SELECT id FROM tbl_education_title WHERE LOWER(education_title) = 'master'),
     (SELECT id FROM tbl_scientific_field WHERE LOWER(scientific_field) = 'computer_science')
     (SELECT id FROM tbl_department WHERE LOWER(name) = 'department-2'))),

    ('Alice', 'Johnson',
     (SELECT id FROM tbl_academic_title WHERE LOWER(academic_title) = 'assistant_professor'),
     (SELECT id FROM tbl_education_title WHERE LOWER(education_title) = 'doctor'),
     (SELECT id FROM tbl_scientific_field WHERE LOWER(scientific_field) = 'mathematics')
     (SELECT id FROM tbl_department WHERE LOWER(name) = 'department-3')));
