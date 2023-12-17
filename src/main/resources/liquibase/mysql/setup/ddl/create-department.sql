create table tbl_department(
	id bigint unsigned not null AUTO_INCREMENT,
	name varchar(100) not null,
    supervisor_id BIGINT UNSIGNED,
    secretary_id BIGINT UNSIGNED,

	primary key (id),
    constraint supervisor_fk FOREIGN KEY (supervisor_id) REFERENCES tbl_member(id),
    constraint secretary_fk FOREIGN KEY (secretary_id) REFERENCES tbl_member(id)
) 
