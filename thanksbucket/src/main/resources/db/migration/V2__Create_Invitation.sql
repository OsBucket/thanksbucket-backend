CREATE TABLE invitations
(
    invitation_id    BIGINT AUTO_INCREMENT NOT NULL,
    sender_id        BIGINT                NULL,
    invitation_type  INT                   NULL,
    invitation_who   VARCHAR(255)          NULL,
    invitation_when  VARCHAR(255)          NULL,
    invitation_where VARCHAR(255)          NULL,
    invitation_what  VARCHAR(255)          NULL,
    CONSTRAINT pk_invitations PRIMARY KEY (invitation_id)
);

ALTER TABLE `buckets`
    ALTER bucket_status SET DEFAULT '''START';

ALTER TABLE bucket_todos
    ALTER todo_status SET DEFAULT '''START';