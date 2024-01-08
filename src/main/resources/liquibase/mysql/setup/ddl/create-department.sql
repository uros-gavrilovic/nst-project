create table tbl_department
(
    id            bigint unsigned not null AUTO_INCREMENT,
    name          varchar(100) not null,
    supervisor_id bigint unsigned not null,
    secretary_id  bigint unsigned not null,

    CONSTRAINT supervisor_fk FOREIGN KEY (supervisor_id) REFERENCES tbl_member (id),
    CONSTRAINT secretary_fk FOREIGN KEY (secretary_id) REFERENCES tbl_member (id),
    primary key (id)
) 
