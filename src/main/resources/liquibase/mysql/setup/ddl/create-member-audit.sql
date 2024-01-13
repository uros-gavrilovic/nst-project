CREATE TABLE tbl_member_audit
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT,
    entity_id     BIGINT UNSIGNED NOT NULL,
    field         VARCHAR(255) NOT NULL,
    old_value     VARCHAR(255),
    new_value     VARCHAR(255),
    rev_date_time DATETIME     NOT NULL,

    FOREIGN KEY (entity_id) REFERENCES tbl_member (id)
);