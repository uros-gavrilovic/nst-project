CREATE TABLE tbl_member_audit
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    entity_id  BIGINT,
    field      VARCHAR(255),
    old_value  VARCHAR(255),
    new_value  VARCHAR(255),
    rev        BIGINT,
    rev_type   SMALLINT,

    FOREIGN KEY (entity_id) REFERENCES tbl_member (id)
);