CREATE TABLE bucket_template
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    created_date      datetime              NOT NULL,
    updated_date      datetime              NOT NULL,
    bucket_name       VARCHAR(255)          NOT NULL,
    bucket_todo_names VARCHAR(255)          NULL,
    CONSTRAINT pk_bucket_template PRIMARY KEY (id)
);

CREATE TABLE bucket_template_topic
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    created_date       datetime              NOT NULL,
    updated_date       datetime              NOT NULL,
    bucket_template_id BIGINT                NOT NULL,
    topic_id           BIGINT                NOT NULL,
    CONSTRAINT pk_bucket_template_topic PRIMARY KEY (id)
);

CREATE TABLE bucket_todos
(
    bucket_todo_id BIGINT AUTO_INCREMENT            NOT NULL,
    created_date   datetime                         NOT NULL,
    updated_date   datetime                         NOT NULL,
    content        VARCHAR(255)                     NOT NULL,
    todo_status    VARCHAR(255) DEFAULT '''''START' NOT NULL,
    bucket_id      BIGINT                           NULL,
    CONSTRAINT pk_bucket_todos PRIMARY KEY (bucket_todo_id)
);

CREATE TABLE bucket_topics
(
    bucket_topic_id BIGINT AUTO_INCREMENT NOT NULL,
    created_date    datetime              NOT NULL,
    updated_date    datetime              NOT NULL,
    topic_id        BIGINT                NULL,
    bucket_id       BIGINT                NULL,
    CONSTRAINT pk_bucket_topics PRIMARY KEY (bucket_topic_id)
);

CREATE TABLE `buckets`
(
    bucket_id     BIGINT AUTO_INCREMENT            NOT NULL,
    created_date  datetime                         NOT NULL,
    updated_date  datetime                         NOT NULL,
    title         VARCHAR(255)                     NOT NULL,
    bucket_status VARCHAR(255) DEFAULT '''''START' NOT NULL,
    member_id     BIGINT                           NOT NULL,
    goal_date     date                             NOT NULL,
    CONSTRAINT pk_buckets PRIMARY KEY (bucket_id)
);

CREATE TABLE members
(
    member_id      BIGINT AUTO_INCREMENT NOT NULL,
    created_date   datetime              NOT NULL,
    updated_date   datetime              NOT NULL,
    email          VARCHAR(255)          NOT NULL,
    nickname       VARCHAR(255)          NULL,
    image_url      VARCHAR(255)          NULL,
    birthday       date                  NULL,
    member_role    VARCHAR(255)          NOT NULL,
    social_type    VARCHAR(255)          NULL,
    social_id      VARCHAR(255)          NULL,
    discovery_path VARCHAR(255)          NULL,
    refresh_token  VARCHAR(255)          NULL,
    occupation_id  BIGINT                NULL,
    CONSTRAINT pk_members PRIMARY KEY (member_id)
);

CREATE TABLE occupations
(
    occupation_id BIGINT AUTO_INCREMENT NOT NULL,
    created_date  datetime              NOT NULL,
    updated_date  datetime              NOT NULL,
    name          VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_occupations PRIMARY KEY (occupation_id)
);

CREATE TABLE topics
(
    topic_id     BIGINT AUTO_INCREMENT NOT NULL,
    created_date datetime              NOT NULL,
    updated_date datetime              NOT NULL,
    content      VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_topics PRIMARY KEY (topic_id)
);

ALTER TABLE members
    ADD CONSTRAINT uc_members_nickname UNIQUE (nickname);

ALTER TABLE members
    ADD CONSTRAINT uc_members_socialid UNIQUE (social_id);

ALTER TABLE occupations
    ADD CONSTRAINT uc_occupations_name UNIQUE (name);

ALTER TABLE topics
    ADD CONSTRAINT uc_topics_content UNIQUE (content);

ALTER TABLE bucket_template_topic
    ADD CONSTRAINT FK_BUCKET_TEMPLATE_TOPIC_ON_BUCKET_TEMPLATE FOREIGN KEY (bucket_template_id) REFERENCES bucket_template (id);

ALTER TABLE bucket_template_topic
    ADD CONSTRAINT FK_BUCKET_TEMPLATE_TOPIC_ON_TOPIC FOREIGN KEY (topic_id) REFERENCES topics (topic_id);

ALTER TABLE bucket_todos
    ADD CONSTRAINT FK_BUCKET_TODOS_ON_BUCKET FOREIGN KEY (bucket_id) REFERENCES `buckets` (bucket_id);

ALTER TABLE bucket_topics
    ADD CONSTRAINT FK_BUCKET_TOPICS_ON_BUCKET FOREIGN KEY (bucket_id) REFERENCES `buckets` (bucket_id);

ALTER TABLE members
    ADD CONSTRAINT FK_MEMBERS_ON_OCCUPATION FOREIGN KEY (occupation_id) REFERENCES occupations (occupation_id);