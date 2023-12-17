insert into tbl_department(name) values ("department-9");

insert into tbl_subject(name,espb,department_id)
values ("subject-1", 10, 8);

insert into tbl_subject(name,espb,department_id)
values ("subject-2", 10, (select (id) from tbl_department WHERE name="department-9"));

insert into tbl_subject(name,espb,department_id)
values ("subject-3", 5, (select (id) from tbl_department WHERE name="department-1"));
insert into tbl_subject(name,espb,department_id)
values ("subject-4", 5, (select (id) from tbl_department WHERE name="department-2"));
insert into tbl_subject(name,espb,department_id)
values ("subject-5", 5, (select (id) from tbl_department WHERE name="department-1"));
